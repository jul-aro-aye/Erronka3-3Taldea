<?php
require_once("../db.php");
 
if ($_SERVER["REQUEST_METHOD"] === "POST" && isset($_POST["akzioa"]) && $_POST["akzioa"] === "administrazioaGehitu") {
    $conn = konexioaSortu();
 
    $enpresarenIzena = isset($_POST["enpresarenIzena"]) ? trim($_POST["enpresarenIzena"]) : '';
    $proposBarraka = isset($_POST["proposBarraka"]) ? trim($_POST["proposBarraka"]) : '';
    $barrakaMota = isset($_POST["barrakaMota"]) ? trim($_POST["barrakaMota"]) : '';
    $telefonoa = isset($_POST["telefonoa"]) ? trim($_POST["telefonoa"]) : '';
    $emaila = isset($_POST["emaila"]) ? trim($_POST["emaila"]) : '';
 
 
    if (empty($enpresarenIzena) || empty($proposBarraka) || empty($barrakaMota) || empty($telefonoa) || empty($emaila)) {
        echo json_encode(["status" => "ko", "error" => "Eremu guztiak bete behar dira"]);
        exit();
    }
 
    $stmt = $conn->prepare("INSERT INTO hornitzaileeskaintza (enpresaIzena, proposBarraka, mota, telefonoa, emaila) VALUES (?, ?, ?, ?, ?)");
    if (!$stmt) {
        error_log("SQL errorea: " . $conn->error);
        echo json_encode(["status" => "ko", "error" => "SQL errorea"]);
        exit();
    }
 
    $stmt->bind_param("sssss", $enpresarenIzena, $proposBarraka, $barrakaMota, $telefonoa, $emaila);
    $success = $stmt->execute();
 
    if ($success) {
        echo json_encode(["status" => "ok"]);
    } else {
        error_log("Datuak ezin izan dira gehitu: " . $stmt->error);
        echo json_encode(["status" => "ko", "error" => "Datuak ezin izan dira gehitu"]);
    }
 
    $stmt->close();
    $conn->close();
} else {
    echo json_encode(["status" => "ko", "error" => "Eskaera baliogabea"]);
}
?>