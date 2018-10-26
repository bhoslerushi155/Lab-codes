import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import confusion_matrix

def run():
    df=pd.read_csv("TripHistory.csv")
    df=df.drop(['Start date','End date','Start station','End station','Bike number'],axis=1)
    print(df.head())
    members=pd.get_dummies(df['Member type'],drop_first=True)
    print(members.head())
    df=pd.concat([members,df],axis=1)
    print(df.head())
    df=df.drop(['Member type'],axis=1)

    X=df.drop(['Member'],axis=1)
    Y=df['Member']
    x_train,x_test,y_train,y_test=train_test_split(X,Y,test_size=0.2)

    classifier=DecisionTreeClassifier(criterion='entropy')
    classifier.fit(x_train,y_train)
    accurecy=classifier.score(x_test,y_test)
    print("Accurecy :")
    print(accurecy)

    y_predicted=classifier.predict(x_test)
    print("Confusion Matrix :")
    print(confusion_matrix(y_predicted, y_test))

if __name__=="__main__":
    run()
