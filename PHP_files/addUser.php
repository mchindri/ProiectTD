<?php
    define('DB_HOST','192.168.43.65');
    define('PORT', 8080);
    define('DB_USERNAME','tdUser');
    define('DB_PASSWORD','wtaI6oRTT8ONvkhY');
    define('DB_NAME', 'parkingdatabase');
    
    // Create connection
    $conn = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    $usr = $_GET['usr'];
    $pass = $_GET['pass'];
    $car = $_GET['car'];
    $bank = $_GET['bank'];
    $sql = "INSERT INTO `users` (`user_id`, `username`, `password`, `bank_account`, `car_number`) VALUES (NULL, '$usr', '$pass', '$bank', '$car')";
    
    if (mysqli_query($conn, $sql)) {
        echo "OK";
    } else {
        echo "FAIL";
    }
    mysqli_close($conn);
?>