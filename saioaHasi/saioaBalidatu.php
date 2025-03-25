<?php
session_start();
require_once("../db.php");

if (isset($_GET["erabiltzailea"]) && isset($_GET["pasahitza"])) {
    $erabiltzailea = $_GET["erabiltzailea"];
    $pasahitza = $_GET["pasahitza"];    

    $conn = konexioaSortu();
    $sql = "SELECT erabiltzailea FROM 3erronka.bezeroa WHERE erabiltzailea=? AND pasahitza=?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ss", $erabiltzailea, $pasahitza);
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows > 0) {
        $_SESSION['erabiltzailea'] = $erabiltzailea; 
       
        echo json_encode(["kopurua" => 1, "redirect" => "sarrera.php"]); 
    } else {
        echo json_encode(["kopurua" => 0]);
    }

}
?>