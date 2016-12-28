<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 	$vehicle_id = $_POST['vid'];
	$sensor_id = $_POST['sid'];

 require_once('dbConnect.php');

 
 $sql = "SELECT * FROM vehicles WHERE vehicle_id = '$vehicle_id' AND sensor_id='$sensor_id'";
 
 $result = mysqli_query($conn,$sql);
 
 $check = mysqli_fetch_array($result);

 if(isset($check)){
 
 	echo json_encode(array("result"=>$check));



}
 else{
 	 echo 'failure';
 }

}