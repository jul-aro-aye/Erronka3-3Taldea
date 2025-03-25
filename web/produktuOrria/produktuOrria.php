<?php

require_once("../header.php");

require_once("../db.php");

$conn = konexioaSortu();

$mota = isset($_GET['mota']) ? $conn->real_escape_string($_GET['mota']) : '';
$marka = isset($_GET['marka']) ? $conn->real_escape_string($_GET['marka']) : '';
$bilatu = isset($_GET["izenaBilatu"]) ? htmlspecialchars($_GET["izenaBilatu"]) : '';


$sql = "SELECT izena, prezioa, mota FROM erronka2.produktua";
if (!empty($mota) && !empty($marka)) {
    $sql .= " WHERE mota = '$mota' AND marka = '$marka'";
} elseif (!empty($mota)) {
    $sql .= " WHERE mota = '$mota'";
} elseif (!empty($marka)) {
    $sql .= " WHERE marka = '$marka'";
}

$emaitza = $conn->query($sql);

$irudiak = [
    "iPhone 12" => "../CSS+Irudiak/Iphone12.jpg",
    "Galaxy S21" => "../CSS+Irudiak/GalaxyS21.jpg",
    "ThinkPad X1 Carbon" => "../CSS+Irudiak/ThinkPad.jpg",
    "MacBook Pro 13" => "../CSS+Irudiak/Macbook.jpg",
    "iPad Air 4" => "../CSS+Irudiak/IpadAir4.jpg",
    "Surface Pro 7" => "../CSS+Irudiak/Surface.jpg",
    "PS5 Digital Edition" => "../CSS+Irudiak/PS5.jpg",
    "Xbox Series X" => "../CSS+Irudiak/Xbox.jpg",
    "Nintendo Switch" => "../CSS+Irudiak/Switch.jpg",
    "Dell XPS 13" => "../CSS+Irudiak/Dell.jpg",
    "OnePlus 9" => "../CSS+Irudiak/OnePlus.jpg",
    "Google Pixel 6" => "../CSS+Irudiak/Pixel6.jpg",
    "ASUS ROG Strix G15" => "../CSS+Irudiak/Asus.jpg",
    "Google Pixel 9" => "../CSS+Irudiak/Pixel9.jpg",
    "HP Spectre x360" => "../CSS+Irudiak/HP.jpg",
    "Huawei Mate Pad" => "../CSS+Irudiak/Huawei.jpg",
    "Xiaomi Redmi 12 pro" => "../CSS+Irudiak/Xiaomi.jpg",
    "Asus Rog Ally X" => "../CSS+Irudiak/AllyX.jpg",
    "Bose QuietComfort 45" => "../CSS+Irudiak/Bose.jpg",
    "Galaxy S23" => "../CSS+Irudiak/GalaxyS23.jpg",
    "PlayStation 4 Pro" => "../CSS+Irudiak/PS4.jpg",
    "Xbox One" => "../CSS+Irudiak/XboxOne.jpg",
    "HP EliteBook" => "../CSS+Irudiak/EliteBook.jpg",
    "Lenovo Tab M10" => "../CSS+Irudiak/Lenovo.jpg",
    "Honor MagicPad2" => "../CSS+Irudiak/Honor.jpg",
    "Huawei FreeBuds" => "../CSS+Irudiak/FreeBuds.jpg",
    "JBL Tune" => "../CSS+Irudiak/JBL.jpg",
    "Play Station Vita" => "../CSS+Irudiak/PSVita.jpg",
    "Poco M6" => "../CSS+Irudiak/Poco.jpg",
    "Apple Airpods Pro" => "../CSS+Irudiak/Airpods.jpg"
];

?>
<html>

<head>
    <?php require_once("../head.php"); ?>
    <title>Produktuak</title>

</head>

<body>

    <div class="content-osoa">
        <h1 id="enpresaIzena">EkoTekno</h1>
        <form id="filtro" method="GET" action="produktuOrria.php">
            <input type="text" name="izenaBilatu" placeholder="Produktuaren izena bilatu..." /><br>

            <label for="mota">Mota</label>
            <select id="mota" name="mota">
                <option value="">Guztiak</option>
                <?php
                $motasql = "SELECT DISTINCT mota FROM erronka2.produktua";
                $motaResult = $conn->query($motasql);
                while ($motaRow = $motaResult->fetch_assoc()) {
                    $selected = ($mota == $motaRow['mota']) ? 'selected' : '';
                    echo "<option value='" . htmlspecialchars($motaRow['mota']) . "' $selected>" . htmlspecialchars($motaRow['mota']) . "</option>";
                }
                ?>
            </select>

            <label for="marka">Marka</label>
            <select id="marka" name="marka">
                <option value="">Guztiak</option>
                <?php
                $markasql = "SELECT DISTINCT marka FROM erronka2.produktua";
                $markaResult = $conn->query($markasql);
                while ($markaRow = $markaResult->fetch_assoc()) {
                    $selected = ($marka == $markaRow['marka']) ? 'selected' : '';
                    echo "<option value='" . htmlspecialchars($markaRow['marka']) . "' $selected>" . htmlspecialchars($markaRow['marka']) . "</option>";
                }
                ?>
            </select>

            <button id="botoia" type="submit">Bilatu</button>
        </form>
        <div id="karritoa-ikonoa">
            <a href="karritoa.php"><i class="fa fa-shopping-cart" id="karritoa"><span
                        id="karrito-kopurua">0</span></i></a>
        </div>
        <div id="produktuak">
            <?php
            if ($emaitza->num_rows > 0) {
                while ($row = $emaitza->fetch_assoc()) {
                    if (strpos(strtolower($row["izena"]), strtolower($bilatu)) !== false) {
                        $produktuIzena = $row["izena"];
                        $produktuIrudi = isset($irudiak[$produktuIzena]) ? $irudiak[$produktuIzena] : "img/default.jpg";
                        echo "<div class='produktua'>";
                        echo "<img src='" . $produktuIrudi . "' height='100px' width='75px' alt='" . htmlspecialchars($produktuIzena) . "'>";
                        echo "<h3>" . htmlspecialchars($row["izena"]) . "</h3>";
                        echo "<p>Prezioa: $" . number_format($row["prezioa"], 2) . "</p>";
                        echo "<button class='gehituSaskira' data-izena='" . htmlspecialchars($row["izena"]) . "' data-prezioa='" . $row["prezioa"] . "'>Gehitu saskira</button>";

                        echo "</div>";
                    }
                }
            } else {
                echo "<p>Ez dago produkturik.</p>";
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