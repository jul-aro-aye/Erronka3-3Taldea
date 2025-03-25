<?php
require_once("../header.php");


require_once("../db.php");

$conn = konexioaSortu();


$sql = "SELECT izenburua, textua, data FROM berria";
$result = $conn->query($sql);

$irudiak = [
    "EkoTekno aurkezten du bere lehen mugikor ekologikoa" => "../CSS+Irudiak/MugikorEkologikoa.jpg",
    "EkoTekno-k eguzki-kargagailu adimenduna merkaturatu du" => "../CSS+Irudiak/Eguzki-kargagailuAdimenduna.jpg",
    "EkoTekno eta AI: adimen artifizial jasangarria garatzen" => "../CSS+Irudiak/AI.jpg",

]

    ?>
<html>

<head>
    <?php
    require_once "../head.php";
    ?>
    <title>Berriak</title>

</head>

<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">EkoTekno</h1>
        <div id="berriak">

            <?php
            if ($result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    $berrienIzenburu = $row["izenburua"];


                    $berrienIrudi = isset($irudiak[$berrienIzenburu]) ? $irudiak[$berrienIzenburu] : "img/default.jpg";
                    echo "<div class='berria'>";
                    echo "<img src='" . $berrienIrudi . "' id='berrienIrudiak' height='150px' width='150px' alt='Berrien irudiak'" . htmlspecialchars($berrienIzenburu) . "'>";
                    echo "<br><br>";
                    echo "<h3>" . htmlspecialchars($row["izenburua"]) . "</h3>";
                    echo "<p>" . htmlspecialchars($row["textua"]) . "</p>";
                    echo "<p>" . htmlspecialchars($row["data"]) . "</p>";
                    echo "</div>";
                }
            } else {
                echo "<p>Ez daude produkturik.</p>";
            }


            ?>
        </div>
    </div>

    <?php
    require_once "../footer.php";

    ?>
</body>

</html>