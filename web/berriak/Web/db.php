<?php

function konexioaSortu()
{

    $servername = "localhost:3306";
    $username = "root";
    $password = "1MG2024";
    $dbname = "erronka2";

    $conn = new mysqli($servername, $username, $password, $dbname);


    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    return $conn;
}