<!DOCTYPE html>
<html>
<head>
    <title>delete</title>
</head>
<body>

</body>
</html>
<body>
<?php
include_once("config.php");

if(isset($_POST["sub"])){
    $roll=$_POST["roll"];



    mysqli_query($conn , "delete from info where roll='$roll';");
        echo"Record added successfully<br>";
        echo "<a href='index.html'>go to home</a>";

}else{
    echo "<a href='index.html'>go to home</a>";
}


?>
</body>
</html>
