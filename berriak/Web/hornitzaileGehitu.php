<?php
session_start();
require_once("db.php"); 
$conn = konexioaSortu(); 

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $izena = $_POST['izena'];
    $deskripzioa = $_POST['deskripzioa'];
    $telefonoa = $_POST['telefonoa'] ?? null;
    $email = $_POST['email'];

    function errorea($mezua) {
        echo "<script>
                alert('$mezua');
                window.location.href='hornitzaileForm.php';
              </script>";
        exit();
    }

    if ($stmt = $conn->prepare('SELECT idHornitzailea FROM hornitzailea WHERE izena = ?')) {
        $stmt->bind_param('s', $izena);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            errorea("Hornitzaile hori dagoeneko existitzen da");
        }
        $stmt->close();
    }

    if ($stmt = $conn->prepare('INSERT INTO hornitzailea (Izena, deskripzioa, Telefonoa, Email) VALUES (?, ?, ?, ?)')) {
        $stmt->bind_param('ssss', $izena, $deskripzioa, $telefonoa, $email);
        if ($stmt->execute()) {
            echo "<script>
                    alert('Erregistro Zuzena');
                    window.location.href='berriak.php';
                  </script>";
        } else {
            errorea("Errorea erregistratzean. Saiatu berriro.");
        }
        $stmt->close();
    } else {
        errorea("Errorea konexioan.");
    }
}
?>
