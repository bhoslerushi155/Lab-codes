library(datasets)

str(iris)
names(iris)

summary(iris)

par('mar')
par(mar=c(2,2,2,2))
hist(iris$Sepal.Length,col = 'blue')
hist(iris$Sepal.Width,col = 'blue')
hist(iris$Petal.Length,col='blue')
hist(iris$Petal.Width,col='blue')

boxplot(iris[,1:4],main="boxplot of attributes of iris",varwidth = TRUE,notch = TRUE,col = 'blue')

