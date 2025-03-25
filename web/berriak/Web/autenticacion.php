<?php
session_start();

require_once("db.php");
$conn = konexioaSortu();

if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($stmt = $conn->prepare('SELECT idBezeroa, Pasahitza FROM bezeroa WHERE Erabiltzailea = ?')) {
        $stmt->bind_param('s', $_POST['username']);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            $stmt->bind_result($idBezeroa, $Pasahitza);
            $stmt->fetch();

            if ($_POST['password'] === $Pasahitza) {
                session_regenerate_id();
                $_SESSION['loggedin'] = TRUE;
                $_SESSION['name'] = $_POST['username'];
                $_SESSION['id'] = $idBezeroa;


                header('Location: berriak.php');
                exit();
            } else {
                header('Location: berriak.php');
                exit();
            }
        } else {
            header('Location: berriak.php');
            exit();
        }
    } else {
        echo 'Errorea.';
    }
    $stmt->close();
}
?>