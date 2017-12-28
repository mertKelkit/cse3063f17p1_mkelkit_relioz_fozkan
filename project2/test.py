import string
import glob
import re

from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter, resolve1
from pdfminer.pdfdocument import PDFDocument
from pdfminer.converter import TextConverter
from pdfminer.pdfparser import PDFParser
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from io import StringIO

import nltk
from nltk.corpus import stopwords

import pandas as pd
import numpy as np

from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer

#####################################################################################################################

def extract_text(file_path):
    out = StringIO()
    # Preparing for reading pdf file
    manager = PDFResourceManager()
    converter = TextConverter(manager, out, laparams=LAParams())
    interpreter = PDFPageInterpreter(manager, converter)

    file = open(file_path, 'rb')

    # Getting page numbers as list
    parser = PDFParser(file)
    document = PDFDocument(parser)
    pages = set(range(resolve1(document.catalog['Pages'])['Count']))

    # Getting each page's text
    for page in PDFPage.get_pages(file, pages):
        interpreter.process_page(page)
    file.close()
    converter.close()
    text = out.getvalue()
    out.close()

    # Clearing the string
    text = re.sub('\s\s+', ' ', text)
    text = text.lower()
    text = text.replace('\n', ' ')
    exclude = set(string.punctuation)
    exclude.add('®')
    text = ''.join(ch for ch in text if ch not in exclude)
    return text


def get_stopwords():
    f = open('stopwords.txt', 'r', encoding="utf8")
    text = f.read()
    words = text.split('\n')
    f.close()
    return words


# #########################################                 FUNCTIONS                 #################################


input_paths = glob.glob('input/*.pdf')

extra_stopwords = get_stopwords()

for path in input_paths:
    full_text = extract_text(path)
    tokens = nltk.word_tokenize(full_text)
    # Removing stopwords here
    stops = set(stopwords.words('english')).union(set(extra_stopwords))
    filtered = [word for word in tokens if word not in stops]
    # Removing the words which has the length one or less
    for item in filtered:
        if len(item) < 2:
            filtered.remove(item)
    # Calculating Term Frequency
    vec = CountVectorizer(input='content', binary=False, ngram_range=(1, 1))
    vec_fit = vec.fit_transform(filtered)
    # Getting term frequency matrix and words
    tf_matrix = vec_fit.toarray().sum(axis=0)
    feature_names = vec.get_feature_names()
    # Creating data for DataFrame
    data = {'Words': feature_names,
            'TF': tf_matrix}
    df = pd.DataFrame(data, columns=['Words', 'TF'])
    print(df)