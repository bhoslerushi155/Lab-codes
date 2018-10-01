angular.module('calc',[])
.controller('calccontro',function calccontro($scope){
$scope.num1=0;
$scope.num2=0;
$scope.opr1="*";

$scope.result=function(opr1){
switch(opr1){
case("+"):
return parseFloat($scope.num1)+parseFloat($scope.num2);
break;
case("-"):
return parseFloat($scope.num1)-parseFloat($scope.num2);

break;
case("*"):
return parseFloat($scope.num1)*parseFloat($scope.num2);

break;
case("/"):
return parseFloat($scope.num1)/parseFloat($scope.num2);

break;

}
};
});