<?php
require_once("db.php");

$enpresaIzena = "";
$kokapena = "";
$banatzaileKop = "";
$telefonoa = "";
$emaila = "";

if ($_POST["akzioa"] == "hornitzaileaGehitu") {

    $conn = konexioaSortu();

    if (isset($_POST["enpresaIzena"]) && !empty($_POST["enpresaIzena"])) {
        $enpresaIzena = $_POST["enpresaIzena"];
    }

    if (isset($_POST["kokapena"]) && !empty($_POST["kokapena"])) {
        $kokapena = $_POST["kokapena"];
    }

    if (isset($_POST["banatzaileKop"]) && !empty($_POST["banatzaileKop"])) {
        $banatzaileKop = $_POST["banatzaileKop"];
    }

    if (isset($_POST["telefono"]) && !empty($_POST["telefono"])) {
        $telefonoa = $_POST["telefono"];
    }

    if (isset($_POST["emaila"]) && !empty($_POST["emaila"])) {
        $emaila = $_POST["emaila"];
    }

    echo $enpresaIzena;

    $kontsulta = "INSERT INTO hornitzailea (enpresaIzena, kokapena, banatzaileKop, telefonoa, emaila, egoera) VALUES ('$enpresaIzena', '$kokapena', '$banatzaileKop', '$telefonoa', '$emaila',\"EzOnartuta\")";
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