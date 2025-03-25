<?php
require_once("../header.php");


require_once("../db.php");

$conn = konexioaSortu();


$sql = "SELECT izenburua, informazioa, data FROM ekintza";
$result = $conn->query($sql);

$irudiak = [
    "Jaialdi handia Bilboko kaleetan." => "../CSS+Irudiak/MugikorEkologikoa.jpg",
    "Jazz musika Donostiako antzokietan." => "../CSS+Irudiak/Eguzki-kargagailuAdimenduna.jpg",
    "Azoka tradizionala Gasteizko plazan." => "../CSS+Irudiak/AI.jpg",
    "Entzierro ospetsua Iruñeko kaleetan." => "../CSS+Irudiak/AI.jpg",
    "Musika eta dantza Barakaldon." => "../CSS+Irudiak/AI.jpg",

]

    ?>
<html>

<head>
    <?php
    require_once "../head.php";
    ?>
    <title>Ekintzak</title>

</head>

<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <div id="ekintzak">

            <?php
            if ($result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    $ekintzenIzenburu = $row["izenburua"];


                    $ekintzenIrudi = isset($irudiak[$ekintzenIzenburu]) ? $irudiak[$ekintzenIzenburu] : "img/default.jpg";
                    echo "<div class='berria'>";
                    echo "<img src='" . $ekintzenIrudi . "' id='ekintzenIrudiak' height='150px' width='150px' alt='" . htmlspecialchars($ekintzenIzenburu) . "'>";

                    echo "<br><br>";
                    echo "<h3>" . htmlspecialchars($row["izenburua"]) . "</h3>";
                    echo "<p>" . htmlspecialchars($row["informazioa"]) . "</p>";
                    echo "<p>" . htmlspecialchars($row["data"]) . "</p>";
                    echo "</div>";
                }
            } else {
                echo "<p>Ez daude ekintzarik.</p>";
            }


            ?>
        </div>
    </div>

    <?php
    require_once "../footer.php";

    ?>
</body>

</html>