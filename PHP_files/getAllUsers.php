
<?php 
    //Creating a connection
    define('DB_HOST','192.168.43.65');
    define('PORT', 8080);
    define('DB_USERNAME','tdUser');
    define('DB_PASSWORD','wtaI6oRTT8ONvkhY');
    define('DB_NAME', 'parkingdatabase');
	$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
    
    
    if (mysqli_connect_errno())
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    /*Get the id of the last visible item in the RecyclerView from the request and store it in a variable. For 
               the first request id will be zero.*/
	
	$sql= "select * from users";
	
	$result = mysqli_query($con ,$sql);
	
	while ($row = mysqli_fetch_assoc($result)) {
		
		$array[] = $row;
		
	}
	header('Content-Type:Application/json');
	
	echo json_encode($array);
 
    mysqli_free_result($result);
 
    mysqli_close($con);

?>