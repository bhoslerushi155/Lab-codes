<!DOCTYPE html>
<html>
<head>
    <title>update</title>
</head>
<body>

</body>
</html>
<body>
<?php
include_once("config.php");

if(isset($_POST["sub"])){
    $roll=$_POST["roll"];
    $name=$_POST["name"];
    $marks=$_POST["marks"];


    mysqli_query($conn , "update info set name='$name',marks='$marks' where roll='$roll';");
        echo"Record added successfully<br>";
        echo "<a href='index.html'>go to home</a>";

}else{
    echo "<a href='index.html'>go to home</a>";
}


?>
</body>
</html>
