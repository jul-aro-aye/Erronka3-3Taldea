<?php
require_once("../header.php");
require_once("../db.php");

$conn = konexioaSortu();

require_once("../konfigurazioa/layoutTop.php");


?>

<html>

<head>
    <?php

    require_once("../head.php");

    ?>

    <title>Administrazioan Sartu</title>

</head>

<body>

    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>

        <form id="administrazioForm">
            <label for="enpresarenIzena">Enpresaren Izena:</label><br>
            <input type="text" id="enpresarenIzena" name="enpresarenIzena" required placeholder="Izena"> <br><br>
            <label for="proposBarraka">Proposatutako Barraka:</label><br>
            <input type="text" id="proposBarraka" name="proposBarraka" required placeholder="Barrakaren Izena"> <br><br>
            <label for="barrakaMota">Barraka Mota:</label><br>
            <input type="text" id="barrakaMota" name="barrakaMota" required placeholder="Izena"> <br><br>
            <label for="telefonoa">Telefonoa:</label><br>
            <input type="text" id="telefonoa" name="telefonoa" required placeholder="000000000"> <br><br>
            <label for="emaila">Emaila:</label><br>
            <input type="email" id="emaila" name="emaila" placeholder="xxxxxx@gmail.com"> <br><br><br>


            <div id="administrazioanSartu">
                <button id="administrazioaSartzekoBotoia">Proposamena bidali</button>
            </div>

        </form>
    </div>
</body>

</html>