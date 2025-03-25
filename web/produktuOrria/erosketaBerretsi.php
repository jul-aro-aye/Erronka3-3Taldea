<?php
session_start();
require_once("../db.php");

$conn = konexioaSortu();

if (!isset($_SESSION['Bezeroa_idBezeroa'])) {
    echo json_encode(["error" => "Erabiltzailea ez dago saioan."]);
    exit();
}

$data = json_decode(file_get_contents("php://input"), true);
if (!$data || empty($data["karritoa"])) {
    echo json_encode(["error" => "Ez dago produkturik karritoan."]);
    exit();
}

$Bezeroa_idBezeroa = $_SESSION['Bezeroa_idBezeroa']; 
$data = date("Y-m-d H:i:s");


$kopurua = json_encode($data["karritoa"]);

// Insertar todo en una sola fila
$sql = "INSERT INTO eskaera (kopurua,data,Bezeroa_idBezeroa) VALUES (?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param( $kopurua, $data, $Bezeroa_idBezeroa);

if ($stmt->execute()) {
    echo json_encode(["success" => "Erosketa gorde da!", "idBezeroa" => $stmt->insert_id]);
} else {
    echo json_encode(["error" => "Errorea erosketa gordetzean."]);
}

$conn->close();
?>
