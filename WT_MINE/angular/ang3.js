var app=angular.module("myapp" ,[]);
app.controller("myctrl" , function($scope){
    $scope.cart=[{name:'',qty:'',cost:''}];

    $scope.add=function(){
        $scope.cart.push({name:'',qty:'',cost:''});
    }

    $scope.remove=function(ind){
        $scope.cart.splice(ind,1);
    }

    $scope.total=function(){
        var tot=0;

        angular.forEach($scope.cart, function(item){
            tot+=item.qty * item.cost;
        })

        return tot;
    }
})
