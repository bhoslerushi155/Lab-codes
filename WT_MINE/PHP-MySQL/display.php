<!DOCTYPE html>
<html>
<head>
    <title>Display</title>
</head>
<body>

<?php
    include_once("config.php");
    echo"<table border='1'>";
    echo"<tr>";
        echo"<th>Roll</th>";
        echo"<th>Name</th>";
        echo"<th>Marks</th>";
        echo"</tr>";
    $result=mysqli_query($conn,"select * from info;");
    while($one =mysqli_fetch_array($result)){
        echo"<tr>";
        echo"<td>".$one['roll']."</td>";
        echo"<td>".$one['name']."</td>";
        echo"<td>".$one['marks']."</td>";
        echo"</tr>";
    }
    echo"</table>"
?>
</body>
</html>

