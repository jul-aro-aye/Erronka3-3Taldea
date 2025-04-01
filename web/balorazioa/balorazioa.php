<?php
require_once("../header.php");
require_once("../db.php");
$conn = konexioaSortu();
 
require_once("../konfigurazioa/layoutTop.php");
 
 
$sql = "SELECT DISTINCT izena FROM barraka";
$result = $conn->query($sql);
 
?>
<html>
 
<head>
    <?php require_once "../head.php"; ?>
    <title>Balorazioa</title>
</head>
 
<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <form action="" method="POST" id="balorazioaForm">
            <label for="barrakaIzena" class="barrakaIzena">Aukeratu barraka bat:</label>
            <select name="barrakaIzena" class="barrakaIzena">
                <option value="">-- Aukeratu --</option>
                <?php
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo '<option value="' . $row["izena"] . '">' . $row["izena"] . '</option>';
                    }
                } else {
                    echo '<option value="">Ez dago daturik</option>';
                }
 
                ?>
 
            </select>
            <br>
            <label for="balorazioa" id="balorazioa">Balorazioa<span class="asterisco">*</span>:
                <div class="rating">
                    <span class="star" data-value="1">&#9733;</span>
                    <span class="star" data-value="2">&#9733;</span>
                    <span class="star" data-value="3">&#9733;</span>
                    <span class="star" data-value="4">&#9733;</span>
                    <span class="star" data-value="5">&#9733;</span>
                </div>
                <div id="ratingResult"></div>
                <div class="hidden" id="ratingValue"></div>
            </label>
        </form>
    </div>
    <?php require_once "../footer.php"; ?>
</body>
 
</html>