<?php
session_start();
require_once("db.php"); 
$conn = konexioaSortu(); 

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $izena = $_POST['izena'];
    $abizena = $_POST['abizena'];
    $erabiltzailea = $_POST['erabiltzailea'];
    $pasahitza = $_POST['pasahitza'];
    $pasahitza_confirm = $_POST['pasahitza_confirm'];
    $email = $_POST['email'];
    $telefonoa = $_POST['telefonoa'] ?? null;
    $nan = $_POST['nan'] ?? null;

    function errorea($mezua) {
        echo "<script>
                alert('$mezua');
                window.location.href='registroForm.php';
              </script>";
        exit();
    }

    if ($pasahitza !== $pasahitza_confirm) {
        errorea("Pasahitzak ez datoz bat");
    }

    if ($stmt = $conn->prepare('SELECT idBezeroa FROM bezeroa WHERE Erabiltzailea = ?')) {
        $stmt->bind_param('s', $erabiltzailea);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            errorea("Erabiltzaile hori dagoeneko existitzen da");
        }
        $stmt->close();
    }

    if ($stmt = $conn->prepare('INSERT INTO bezeroa (Izena, Abizenak, Erabiltzailea, Pasahitza, email, telefonoa, Nan) VALUES (?, ?, ?, ?, ?, ?, ?)')) {
        $stmt->bind_param('sssssss', $izena, $abizena, $erabiltzailea, $pasahitza, $email, $telefonoa, $nan);
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
