<?php
require_once("../header.php");


require_once("../db.php");

$conn = konexioaSortu();


$sql = "SELECT izenburua, informazioa, data, idEkintza FROM ekintza";
$result = $conn->query($sql);

$irudiak = [
    1 => "../CSS+Irudiak/MugikorEkologikoa.jpg",
    2 => "../CSS+Irudiak/Eguzki-kargagailuAdimenduna.jpg",
    3 => "../CSS+Irudiak/AI.jpg",
    4 => "../CSS+Irudiak/AI.jpg",
    5 => "../CSS+Irudiak/AI.jpg",

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
                    $id = $row["idEkintza"];


                    $ekintzenIrudi = isset($irudiak[$id]) ? $irudiak[$id] : "img/default.jpg";
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