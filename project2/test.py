import re
import glob
import string

import os

import nltk
import PyPDF2
import numpy as np

from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter, resolve1
from pdfminer.converter import TextConverter
from pdfminer.pdfdocument import PDFDocument
from pdfminer.pdfparser import PDFParser
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from io import StringIO


pdf_paths = glob.glob('downloadable_input_files/*.pdf')


def convert_to_txt(file_path, pages):
    pagenums = set(pages)
    output = StringIO()
    manager = PDFResourceManager()
    converter = TextConverter(manager, output, laparams=LAParams())
    interpreter = PDFPageInterpreter(manager, converter)

    infile = open(file_path, 'rb')
    fname = file_path.split('/')[1]
    for page in PDFPage.get_pages(infile, pagenums):
        interpreter.process_page(page)
    infile.close()
    converter.close()
    text = output.getvalue()
    output.close()

    # write Content to .txt
    text_file = open('temp_out/out_' + fname.replace('.pdf', '.txt'), 'w')
    text = re.sub('\s\s+', ' ', text)
    context = replace_newline(text)
    text_file.write('%s' % context)
    text_file.close()


def replace_newline(text):
    context = text.split(' ')
    i = 0
    for word in context:
        context[i] = word.replace('\n', ' ')
        i = i + 1
    s = ' '.join(str(elem) for elem in context)
    return s.lower()


def get_page_nums(file_path):
    pdf_file = open(file_path, 'rb')
    parser = PDFParser(pdf_file)
    document = PDFDocument(parser)
    num_of_pages = resolve1(document.catalog['Pages'])['Count']
    pdf_file.close()
    return range(0, num_of_pages)


def get_stopword_list():
    stopword_file = open('stopwords.txt', 'r')
    stopword_str = stopword_file.read()
    stopword_list = list(set(stopword_str.split('\n')))
    stopword_file.close()
    return stopword_list

###############################################################################################

stopword_list = get_stopword_list()

for item in pdf_paths:
    print('Processing ' + item + '...')
    convert_to_txt(item, get_page_nums(item))

print('All pdf files converted to text files.')

temp_outputs = glob.glob('temp_out/*.txt')

exclude = list(set(string.punctuation))
exclude.append('∗')
exclude.append('·')
exclude.append('−')
exclude.append('‡')
exclude.append('”')
exclude.append('“')
for item in temp_outputs:
    file = open(item, 'r')
    text = file.read()
    text_splitted = text.split(' ')

    i = 0
    for item_1 in text_splitted:
        text_splitted[i] = ''.join([ch for ch in text_splitted[i] if not ch.isdigit()])
        text_splitted[i] = ''.join(ch for ch in text_splitted[i] if ch not in exclude)
        i = i + 1
    text = ' '.join(text_splitted)
    stopwords_removed = ' '.join([text for text in text.split() if text not in stopword_list])
    file.close()

clear_list = stopwords_removed.split(' ')

i = 0
for item in clear_list:
    # regexp for removing strings which has the length 1
    clear_list[i] = re.sub(r'\b\w{1,1}\b', '', item)
    print(clear_list[i])
    i = i + 1









# removing temporary text files
for path in temp_outputs:
    os.remove(path)