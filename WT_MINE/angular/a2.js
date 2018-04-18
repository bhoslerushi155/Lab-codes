var app = angular.module('myapp', []);
app.controller('mycontroller', function($scope){

    $scope.technologies= [
        {name:'C++', likes: 0, dislikes:0},
        {name:'JAVA', likes:0, dislikes:0},
        {name:'PYTHON', likes: '0', dislikes:0},
        {name:'C', likes:0, dislikes:0}

    ];

    function compare(a,b){
        if(a.likes > b.likes){
            return -1;
        }
        if(a.likes < b.likes){
            return 1;
        }
        return 0;
    };

    $scope.addlikes=function(tech)
    {
        tech.likes++;
        $scope.technologies.sort(compare);
    }

    $scope.adddislikes=function(tech)
    {
        tech.dislikes++;
    }

});
