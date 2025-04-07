<?php
session_start();
require_once("../db.php");

header('Content-Type: application/json');

if (!isset($_SESSION["idBezeroa"])) {
    echo json_encode(["success" => false, "error" => "Bezeroa ez dago saioa hasita"]);
    exit;
}

$row = json_decode(file_get_contents("php://input"), true);

if (!$row || !isset($row["erreserba"]) || !isset($row["eskaeraIzena"])) {
    echo json_encode(["success" => false, "error" => "Datu okerrak jaso dira"]);
    exit;
}

$erreserba = $row["erreserba"];
$eskaeraIzena = $row["eskaeraIzena"];
$idBezeroa = $_SESSION["idBezeroa"];

$conn = konexioaSortu();
$conn->begin_transaction();

try {
    $stmt = $conn->prepare("INSERT INTO eskaera (izena, data, bezeroa_idBezeroa) VALUES (?, NOW(), ?)");
    $stmt->bind_param("si", $eskaeraIzena, $idBezeroa);
    $stmt->execute();
    $idEskaera = $stmt->insert_id;


    $stmtBarraka = $conn->prepare("SELECT idBarraka FROM barraka WHERE izena = ?");
    $stmtInsert = $conn->prepare("INSERT INTO eskatu (barraka_idBarraka, eskaera_idEskaera) VALUES (?, ?)");

    foreach ($erreserba as $elementua) {
        $izena = $elementua["izena"];

        $stmtBarraka->bind_param("s", $izena);
        $stmtBarraka->execute();
        $result = $stmtBarraka->get_result();

        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $idBarraka = $row["idBarraka"];

            $stmtInsert->bind_param("ii", $idBarraka, $idEskaera);
            $stmtInsert->execute();
        } else {
            throw new Exception("Ez da barraka aurkitu: " . $izena);
        }
    }

    $conn->commit();
    echo json_encode(["success" => true]);

} catch (Exception $e) {
    $conn->rollback();
    echo json_encode(["success" => false, "error" => "Errore bat gertatu da: " . $e->getMessage()]);
}
