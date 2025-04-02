<?php
session_start();
require_once("../db.php");
 
if ($_POST["akzioa"] == "balorazioaGehitu") {
    $conn = konexioaSortu();
   
    $izarKopurua = isset($_POST["izarKopurua"]) ? $_POST["izarKopurua"] : '';
    $iruzkinak = isset($_POST["zergatia"]) ? $_POST["zergatia"] : '';
    $barrakaIzena = isset($_POST["barrakaIzena"]) ? $_POST["barrakaIzena"] : '';
   
    if (!isset($_SESSION['idBezeroa'])) {
        echo json_encode(["status" => "ko", "error" => "Web gunean saioa hasi behar duzu."]);
        exit();
    }
    $idBezeroa = $_SESSION['idBezeroa'];
   
   
    $stmt = $conn->prepare("SELECT idBarraka FROM barraka WHERE izena = ?");
    $stmt->bind_param("s", $barrakaIzena);
    $stmt->execute();
    $result = $stmt->get_result();
   
    if ($result->num_rows == 0) {
        echo json_encode(["status" => "ko", "error" => "Barraka ez da aurkitu"]);
        exit();
    }
    $row = $result->fetch_assoc();
    $idBarraka = $row['idBarraka'];
   
   
    $stmt = $conn->prepare("INSERT INTO balorazioa (izarKopurua, zergatia, Bezeroa_idBezeroa, Barraka_idBarraka) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("isii", $izarKopurua, $iruzkinak, $idBezeroa, $idBarraka);
    $success = $stmt->execute();
   
    if ($success) {
        echo json_encode(["status" => "ok"]);
    } else {
        echo json_encode(["status" => "ko", "error" => "Datuak ezin izan dira gehitu"]);
    }
}