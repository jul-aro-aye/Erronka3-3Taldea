<?php

function konexioaSortu()
{

    $zerbitzaria = "localhost";
    $erab = "administratzailea";
    $pasa = "1MG3@2024";
    $dbIzena = "3erronka";

    $conn = new mysqli($zerbitzaria, $erab, $pasa, $dbIzena);


    if ($conn->connect_error) {
        die("Konexio arazoak" . $conn->connect_error);
    }

    return $conn;
}
?>