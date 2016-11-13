<?php 

		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM project_detail WHERE id = (SELECT MAX(id) FROM project_detail)";
		
		$r = mysqli_query($con,$sql);
		
		$res = mysqli_fetch_array($r);
		
		$result = array();
		
		array_push($result,array(
			"id"=>$res['id'],
			"Shape"=>$res['Shape'],
			"Colour"=>$res['Colour_Nu'],
			"Position_X"=>$res['Position_X'],
			"Position_Y"=>$res['Position_Y'],
			"Message"=>$res['Message']
			));
		
		echo json_encode(array("result"=>$result));		
		mysqli_close($con);
		
	
	?>