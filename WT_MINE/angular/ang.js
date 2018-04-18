var app=angular.module("myApp",[]);

app.controller("myController" ,function($scope){
    $scope.arr=[];
    $scope.add=function(){
        $scope.arr.push({name:$scope.name , email:$scope.email});
    }
});
