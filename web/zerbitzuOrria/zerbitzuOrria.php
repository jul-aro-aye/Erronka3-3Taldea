<?php
 
 require_once("../header/header.php");
 require_once("../db.php");
 
$conn = konexioaSortu();
require_once("../konfigurazioa/layoutTop.php");
 
$modalitatea = isset($_GET['modalitatea']) ? $conn->real_escape_string($_GET['modalitatea']) : '';
$bilatu = isset($_GET["izenaBilatu"]) ? htmlspecialchars($_GET["izenaBilatu"]) : '';
 
$sql = "SELECT izena, modalitatea, kapazitatea, prezioa, irudia FROM barraka";
if (!empty($modalitatea)) {
    $sql .= " WHERE modalitatea = '$modalitatea'";
}
 
$emaitza = $conn->query($sql);
 
?>
<html>
 
<head>
    <?php require_once("../head.php"); ?>
    <title>Zerbitzuak</title>
</head>
 
<body>
 
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <form id="filtro" method="GET" action="zerbitzuOrria.php">
            <input type="text" name="izenaBilatu" placeholder="Barrakaren izena bilatu..." /><br>
 
            <label for="modalitatea">Modalitatea</label>
            <select id="modalitatea" name="modalitatea">
                <option value="">Guztiak</option>
                <?php
                $modalitateasql = "SELECT DISTINCT modalitatea FROM barraka";
                $modalitateaResult = $conn->query($modalitateasql);
                while ($modalitateaRow = $modalitateaResult->fetch_assoc()) {
                    $selected = ($modalitatea == $modalitateaRow['modalitatea']) ? 'selected' : '';
                    echo "<option value='" . htmlspecialchars($modalitateaRow['modalitatea']) . "' $selected>" . htmlspecialchars($modalitateaRow['modalitatea']) . "</option>";
                }
                ?>
            </select>
            <button id="botoia" type="submit">Bilatu</button>
        </form>
 
 
        <div id="barrakaErreserba-ikonoa">
            <a href="barrakaErreserba.php">
                <i class="fa fa-clipboard-list" id="barrakaErreserba">
                    <span id="erreserba-kopurua">0</span>
                </i>
            </a>
        </div>
 
        <div id="zerbitzuak">
            <?php
            if ($emaitza->num_rows > 0) {
                while ($row = $emaitza->fetch_assoc()) {
                    if (strpos(strtolower($row["izena"]), strtolower($bilatu)) !== false) {
                        $barrakaIzena = $row["izena"];
                        $barrakaIrudi = "../CSS+Irudiak/BarrakaIrudiak/" . $row["irudia"];
                        echo "<div class='barraka'>";
                        echo "<img src='" . $barrakaIrudi . "' height='140px' width='160px' alt='" . htmlspecialchars($barrakaIzena) . "'>";
                        echo "<h3>" . htmlspecialchars($row["izena"]) . "</h3>";
                        echo "<p>Modalitatea: " . htmlspecialchars($row["modalitatea"]) . "</p>";
                        echo "<p>Kapazitatea " . htmlspecialchars($row["kapazitatea"]) . "</p>";
                        echo "<p>Prezioa: €" . number_format($row["prezioa"], 2) . "</p>";
                        echo "<button class='zerbitzuEskatu' data-izena='" . htmlspecialchars($row["izena"]) . "' data-prezioa='" . $row["prezioa"] . "'>Zerbitzua Eskatu</button>";
                        echo "</div>";
                    }
                }
            } else {
                echo "<p>Ez dago barrakarik.</p>";
            }
            ?>
        </div>
    </div>
 
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {
 
            function gehituErreserba(izena, prezioa) {
                let barrakaErreserba = JSON.parse(localStorage.getItem("barrakaErreserba")) || [];
                let aurkitua = barrakaErreserba.find(item => item.izena === izena);
 
                if (!aurkitua) {
                    barrakaErreserba.push({ izena: izena, prezioa: prezioa});
                    localStorage.setItem("barrakaErreserba", JSON.stringify(barrakaErreserba));
                    eguneratuBarrakaErreserba();
                } else {
                    alert("Barraka hau dagoeneko gehituta dago errserban!");
                }
            }
 
            function eguneratuBarrakaErreserba() {
                let barrakaErreserba = JSON.parse(localStorage.getItem("barrakaErreserba")) || [];
                let guztira = barrakaErreserba.length;
                $("#erreserba-kopurua").text(guztira);
            }
 
           
            window.addEventListener("storage", function () {
                eguneratuBarrakaErreserba();
            });
 
            $(document).on("click", ".zerbitzuEskatu", function () {
                let izena = $(this).data("izena");
                let prezioa = parseFloat($(this).data("prezioa"));
 
                if (!izena || isNaN(prezioa)) {
                    console.error("Errorea: izena edo prezioa undefined/null");
                    return;
                }
 
                gehituErreserba(izena, prezioa);
            });
 
            eguneratuBarrakaErreserba();
        });
 
    </script>
    <?php require_once("../footer.php"); ?>
</body>
 
</html>