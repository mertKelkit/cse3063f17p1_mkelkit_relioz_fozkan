import glob
import nltk

pdf_paths = glob.glob('downloadable_input_files/*.pdf')

for item in pdf_paths:
    print(item)