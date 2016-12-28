<?php

		$vehicle_id = "GJ1256";
		$co_percent = 15;
		$threshold = 10.00;
		$currentdate = date('Y-m-d');
		$tempdate2 = new DateTime($currentdate); 
		$tempdate2->add(new DateInterval('P7D'));
		$tempdate = $tempdate2->format('Y-m-d');		
		//$jsonData = array();
		#$result = "";
		

		require_once('dbConnect.php');
		
		$sql = "UPDATE vehicles SET co_percent = '$co_percent' WHERE vehicle_id = '$vehicle_id'";
		$sql2 = "UPDATE vehicles SET flag = 1, deadline_date = '$tempdate' WHERE vehicle_id = '$vehicle_id'";
		$sql3 = "SELECT deadline_date from vehicles WHERE vehicle_id = '$vehicle_id' AND flag = 0";
		$sql4 = "SELECT flag, deadline_date from vehicles WHERE vehicle_id = '$vehicle_id' AND flag = 1 AND deadline_date < NOW() ";

		$sql5 = "SELECT * from vehicles WHERE vehicle_id = '$vehicle_id' AND flag = 1 AND deadline_date < NOW()";

		if(mysqli_query($conn,$sql))
		{
			$result = mysqli_query($conn,$sql4);
			$dead = mysqli_fetch_array($result, MYSQL_ASSOC);

			if (is_null($result) );
			{
				echo "Fine Rs. 1000";
			}

			if ($co_percent > $threshold )
			{
					mysqli_query($conn,$sql2);
					$result = mysqli_query($conn,$sql3);
					$dead_warn = mysqli_fetch_array($result, MYSQL_ASSOC);
 					$jsonData = $dead_warn["deadline_date"];

 					#echo json_encode(array("result"=>$jsonData));
			}

			
					

		}
		else
		{
			echo "Could not Update";
		}

?>