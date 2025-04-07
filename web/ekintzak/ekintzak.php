<?php
require_once("../header/header.php");
 
 
require_once("../db.php");
 
$conn = konexioaSortu();
require_once("../konfigurazioa/layoutTop.php");
 
$sql = "SELECT izenburua, informazioa, data, irudia FROM ekintza";
$result = $conn->query($sql);
 
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
 
                    $ekintzenIrudi = "../CSS+Irudiak/EkintzaIrudiak/" . $row["irudia"];
                    echo "<div class='ekintzek'>";
                    echo "<img src='" . $ekintzenIrudi . "' id='ekintzenIrudiak' height='150px' width='220px' alt='" . htmlspecialchars($ekintzenIzenburu) . "'>";
 
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