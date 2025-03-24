<?php
require_once("../db.php");

$izenAbizenak = "";
$erabiltzailea = "";
$pasahitza = "";
$telefonoa = "";
$emaila = "";
$jaio_urtea = "";

if ($_POST["akzioa"] == "erregistroaGehitu") {

    $conn = konexioaSortu();


    if (isset($_POST["izenAbizenak"]) && !empty($_POST["izenAbizenak"])) {
        $izenAbizenak = $_POST["izenAbizenak"];
    }

    if (isset($_POST["erabiltzailea"]) && !empty($_POST["erabiltzailea"])) {
        $erabiltzailea = $_POST["erabiltzailea"];
    }

    if (isset($_POST["pasahitza"]) && !empty($_POST["pasahitza"])) {
        $pasahitza = $_POST["pasahitza"];
    }

    if (isset($_POST["telefono"]) && !empty($_POST["telefono"])) {
        $telefonoa = $_POST["telefono"];
    }

    if (isset($_POST["emaila"]) && !empty($_POST["emaila"])) {
        $emaila = $_POST["emaila"];
    }

    if (isset($_POST["jaio_urtea"]) && !empty($_POST["jaio_urtea"])) {
        $jaio_urtea = $_POST["jaio_urtea"];
    }

    $kontsulta = "INSERT INTO bezeroa (izenAbizenak, erabiltzailea, pasahitza, telefonoa, emaila, jaio_urtea) VALUES ('$izenAbizenak', '$erabiltzailea', '$pasahitza', '$telefonoa', '$emaila','$jaio_urtea')";
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