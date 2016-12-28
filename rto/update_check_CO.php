<?php

	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$vehicle_id = $_POST['vehicle_id'];
		$co_percent = $_POST['co_percent'];
		$threshold = 10.00;
		$currentdate = date('y-m-d');
		$tempdate2 = new DateTime($currentdate); 
		$tempdate2->add(new DateInterval('P7D'));
		$tempdate = $tempdate2->format('Y-m-d');		
		//$jsonData = array();
		require_once('dbConnect.php');
		

		$sql5 = "UPDATE vehicles SET co_percent = '$co_percent' WHERE vehicle_id = '$vehicle_id'";
		$sql2 = "UPDATE vehicles SET flag = 1, deadline_date = '$tempdate' WHERE vehicle_id = '$vehicle_id'";
		$sql3 = "SELECT deadline_date from vehicles WHERE vehicle_id = '$vehicle_id'";
		$sql4 = "SELECT * from vehicles WHERE vehicle_id = '$vehicle_id' ";
		$sql = "SELECT * from vehicles WHERE vehicle_id = '$vehicle_id' ";

		$result = mysqli_query($conn,$sql);
		$ans = mysqli_fetch_array($result, MYSQL_ASSOC);

		if(mysqli_query($conn,$sql))
		{
			if ($co_percent > $threshold AND $ans['flag'] == 0)
			{
					#mysqli_query($conn,$sql5);
					mysqli_query($conn,$sql2);
					$result = mysqli_query($conn,$sql3);
					$dead_warn = mysqli_fetch_array($result, MYSQL_ASSOC);
 					$jsonData = $dead_warn["deadline_date"];
 					echo json_encode(array("result"=>((string)$jsonData)));
			}

		if ($co_percent > $threshold AND $ans['flag'] == 1)
			{
				$result = mysqli_query($conn,$sql4);
				$dead = mysqli_fetch_array($result, MYSQL_ASSOC);
				if($dead["deadline_date"] > date('Y-m-d'))
				{
					echo "No Fine";				
				}
				else
				{
					echo "Fine Rs. 1000";
				}
			}		
					}
		else
		{
			echo "Could not Update";
		}
	}

	else
	{
		echo 'error';
	}