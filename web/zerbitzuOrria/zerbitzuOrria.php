<?php
 
require_once("../header.php");
 
require_once("../db.php");
 
$conn = konexioaSortu();
 
$modalitatea = isset($_GET['modalitatea']) ? $conn->real_escape_string($_GET['modalitatea']) : '';
$bilatu = isset($_GET["izenaBilatu"]) ? htmlspecialchars($_GET["izenaBilatu"]) : '';
 
 
$sql = "SELECT izena, modalitatea, kapazitatea, prezioa,irudia FROM barraka";
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
        <div id="karritoa-ikonoa">
            <a href="karritoa.php"><i class="fa fa-shopping-cart" id="karritoa"><span
                        id="karrito-kopurua">0</span></i></a>
        </div>
        <div id="zerbitzuak">
            <?php
            if ($emaitza->num_rows > 0) {
                while ($row = $emaitza->fetch_assoc()) {
                    if (strpos(strtolower($row["izena"]), strtolower($bilatu)) !== false) {
                        $barrakaIzena = $row["izena"];
                        $barrakaIrudi = "../CSS+Irudiak/BarrakaIrudiak/". $row["irudia"];
                        echo "<div class='barraka'>";
                        echo "<img src='" . $barrakaIrudi . "' height='140px' width='190px' alt='" . htmlspecialchars($barrakaIzena) . "'>";
                        echo "<h3>" . htmlspecialchars($row["izena"]) . "</h3>";
                        echo "<p>Modalitatea: " . htmlspecialchars($row["modalitatea"])."</p>";
                        echo "<p>Kapazitatea ". htmlspecialchars($row["kapazitatea"]). "</p>";
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
            function gehituSaskira(izena, prezioa) {
                let karritoa = JSON.parse(localStorage.getItem("karritoa")) || [];
                let aurkitua = karritoa.find(item => item.izena === izena);
                if (aurkitua) {
                    aurkitua.kopurua += 1;
                } else {
                    karritoa.push({ izena: izena, prezioa: prezioa, kopurua: 1 });
                }
                localStorage.setItem("karritoa", JSON.stringify(karritoa));
                eguneratuKarritoa();
            }
 
            function eguneratuKarritoa() {
                let karritoa = JSON.parse(localStorage.getItem("karritoa")) || [];
                let guztira = karritoa.reduce((total, item) => total + item.kopurua, 0);
                $("#karrito-kopurua").text(guztira);
            }
 
            $(document).on("click", ".gehituSaskira", function () {
                let izena = $(this).data("izena");  // Usamos .data() en lugar de .attr()
                let prezioa = parseFloat($(this).data("prezioa"));
 
                if (!izena || isNaN(prezioa)) {
                    console.error("Errorea: izena edo prezioa undefined/null");
                    return;
                }
 
                gehituSaskira(izena, prezioa);
            });
 
            eguneratuKarritoa();
        });
 
 
 
 
 
 
    </script>
    <?php require_once("../footer.php"); ?>
</body>
 
</html>