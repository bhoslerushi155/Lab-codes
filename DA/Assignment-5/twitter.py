import re
import pandas as pd
import textblob

def preprocess(text):
    text=text.replace('user','')
    text=re.sub(r'[^\w\s]' , '' , text)
    return text

def run():
    train = pd.read_csv("train.csv")
    print(train.head())
    test = pd.read_csv("test.csv")
    print(test.head())
    train=train.drop(['label'],axis=1)
    print(train.head())
    combined = train.append(test)
    print(combined.head())

    for text in combined['tweet']:
        text=preprocess(text)
        testimonial=textblob.TextBlob(text)
        if testimonial.sentiment.polarity < -0.9:
            print(text)
            print('hated tweet %f'%testimonial.sentiment.polarity)


if __name__=="__main__":
    run()
