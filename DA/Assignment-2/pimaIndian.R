setwd("~/Desktop")

library(klaR)
library(caret)
library(datasets)

allData <- read.csv("PimaIndiansDiabetes.csv" , header = FALSE)

features <- allData[,-9]
lables <- allData[,9]

trainingData <- createDataPartition(y=lables,p=0.8,list = FALSE)

trainingFeatures <- features[trainingData,]
trainingLables <- lables[trainingData]
testingFeatures <- features[-trainingData,]
testingLables <- lables[-trainingData]

classifierModel <- train(trainingFeatures,trainingLables,'nb',trControl = trainControl(method ='cv',number = 10))
predictedValues <- predict(classifierModel,testingFeatures)
confusionMatrix(predictedValues,testingLables)
