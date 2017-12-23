import glob
import nltk
import PyPDF2

pdf_paths = glob.glob('downloadable_input_files/*.pdf')

for item in pdf_paths:
    # 25 is the directory name length
    file_name = item.split('/')[1]
    print('Current pdf -> ' + file_name)
    pdf_file = open(item, 'rb')
    pdf_reader = PyPDF2.PdfFileReader(pdf_file)
    num_of_pages = pdf_reader.getNumPages()
    print('Num of pages of %s is %d.' % (file_name, num_of_pages))
    for i in range(num_of_pages):
        page = pdf_reader.getPage(i)
        print(page.extractText())
    pdf_file.close()
