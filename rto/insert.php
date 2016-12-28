<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{

	$vehicle_id = $_POST['vid'];
	$sensor_id = $_POST['sid'];
	$owner_name = $_POST['name'];
	$phone_number = $_POST['phone'];
	$address = $_POST['address'];





require_once('dbConnect.php');




$sql = "INSERT INTO vehicles (vehicle_id,sensor_id,owner_name,address,phone_number) VALUES ('$vehicle_id','$sensor_id','$owner_name','$address','$phone_number')";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Could not register";
}

}

?> 