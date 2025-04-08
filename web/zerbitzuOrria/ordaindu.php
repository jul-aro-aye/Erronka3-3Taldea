<?php
require_once("../header/header.php");
require_once("../db.php");

$conn = konexioaSortu();
require_once("../konfigurazioa/layoutTop.php");
?>

<html>

<head>
    <title>Ordaindu - Erosketa Berretsi</title>
    <link rel="stylesheet" href="ordaindu.css">
    <?php require_once("../head.php"); ?>
</head>

<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <br><br>
        <h2 id="ordainduTitulua">Ordaindu</h2>
        <div id="ordainketa-edukia"></div>
        <br>
        <div id="eskaeraIzena">
            <label for="eIzena">Sartu eskaeraren izena:</label>
            <input type="text" id="eIzena" name="eIzena" placeholder="Eskaera izena" required>
        </div>
        <br>
        <div id="ordaindu-botoiak">
            <button id="erosketaBerretsi">Erosketa Berretsi</button>
        </div>

        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

        <script>
            $(document).ready(function () {
                erakutsiErosketa();

                $("#erosketaBerretsi").click(function () {
                    berretsiErosketa();
                });
            });

            function erakutsiErosketa() {
                let ordainketaEdukia = $("#ordainketa-edukia");
                let erreserba = JSON.parse(localStorage.getItem("barrakaErreserba")) || [];

                ordainketaEdukia.html("");
                if (erreserba.length === 0) {
                    ordainketaEdukia.html("<p>Ez dago barrakarik erreserban.</p>");
                    return;
                }

                let guztira = 0;
                let lista = "<ul>";
                erreserba.forEach(barraka => {
                    lista += `<li>${barraka.izena} - €${barraka.prezioa}</li>`;
                    guztira += barraka.prezioa;
                });
                lista += "</ul>";
                ordainketaEdukia.html(lista + `<h3>Guztira: €${guztira.toFixed(2)}</h3>`);
            }

            function berretsiErosketa() {
                let eskaeraIzena = $("#eIzena").val().trim();
                let erreserba = JSON.parse(localStorage.getItem("barrakaErreserba")) || [];

                if (!eskaeraIzena) {
                    alert("Mesedez, sartu eskaeraren izena.");
                    return;
                }

                if (erreserba.length === 0) {
                    alert("Ez dago barrakarik erreserban.");
                    return;
                }

                $.ajax({
                    url: "erosketaBerretsi.php",
                    method: "POST",
                    data: JSON.stringify({ eskaeraIzena, erreserba }),
                    contentType: "application/json",
                    success: function (data) {
                        console.log("Erantzuna:", data);
                        if (data.success) {
                            alert("Eskerrik asko erreserbatzeagatik!");
                            localStorage.removeItem("barrakaErreserba");
                            erakutsiErosketa();
                            $("#eIzena").val("");
                        } else {
                            alert("Errorea: " + data.error);
                        }
                    },
                    error: function () {
                        alert("Errorea transakzioan. Saiatu berriro.");
                    }
                });
            }



            function eguneratuErreserba() {
                localStorage.setItem("erreserbaKopurua", "0");
                window.dispatchEvent(new Event("storage"));
            }

        </script>
    </div>

    <?php require_once("../footer.php"); ?>

</body>

</html>