<?php
require_once("../db.php");

$izena = "";
$abizenak = "";
$erabiltzailea = "";
$pasahitza = "";
$telefonoa = "";
$emaila = "";

if ($_POST["akzioa"] == "erregistroaGehitu") {

    $conn = konexioaSortu();


    if (isset($_POST["izena"]) && !empty($_POST["izena"])) {
        $izena = $_POST["izena"];
    }
    
    if (isset($_POST["abizenak"]) && !empty($_POST["abizenak"])) {
        $abizenak = $_POST["abizenak"];
    }

    if (isset($_POST["erabiltzailea"]) && !empty($_POST["erabiltzailea"])) {
        $erabiltzailea = $_POST["erabiltzailea"];
    }

    if (isset($_POST["pasahitza"]) && !empty($_POST["pasahitza"])) {
        $pasahitza = $_POST["pasahitza"];
    }

    if (isset($_POST["telefonoa"]) && !empty($_POST["telefonoa"])) {
        $telefonoa = $_POST["telefonoa"];
    }

    if (isset($_POST["emaila"]) && !empty($_POST["emaila"])) {
        $emaila = $_POST["emaila"];
    }


    $kontsulta = "INSERT INTO bezeroa (izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila) VALUES ('$izena','$abizenak', '$erabiltzailea', '$pasahitza', '$telefonoa', '$emaila')";
    $result = $conn->query($kontsulta);

    $egoera = [];

    if ($result === TRUE) {
        $egoera["status"] = "ok";
    } else {
        $egoera["status"] = "ko";
    }

    if ($egoera["status"] == "ok") {
        echo json_encode($egoera);
    } else {
        echo json_encode($egoera);
    }

}
?>