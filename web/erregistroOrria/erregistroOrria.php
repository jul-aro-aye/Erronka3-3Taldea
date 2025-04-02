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
 
    <title>Erregistroa</title>
    <link rel="stylesheet" href="erregistroOrria.css">
</head>
 
<body>
 
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <br><br>
        <form id="erregistroForm">
            <label for="izena">Izena:</label>
            <input type="text" id="izena" name="izena" required placeholder="Izena"> <br><br>
            <label for="abizenak">Abizenak:</label>
            <input type="text" id="abizenak" name="abizenak" required placeholder="Abizena Abizena"> <br><br>
            <label for="erabiltzailea">Erabiltzailea:</label>
            <input type="text" id="erabiltzailea" name="erabiltzailea" required pattern="^[a-z]{3}_[a-z]{3}_[a-z]{3}$"
                title="Erabiltzailearen formatua ez da okerra, formatu egokia (xxx_xxx_xxx) da."
                placeholder="xxx_xxx_xxx">
            <br><br>
            <label for="pasahitza">Pasahitza:</label>
            <input type="password" id="pasahitza" name="pasahitza" required
                pattern="^[a-zA-Z0-9!@#$%^&*()_+={}[\]:;<>,.?/-]{1,8}$"
                title="8 karakter maximo eduki behar ditu, letrak, simbolo eta zenbakiak erabiliz"> <br><br>
            <label for="telefonoa">Telefonoa:</label>
            <input type="text" id="telefonoa" name="telefonoa" required placeholder="000000000"> <br><br>
            <label for="emaila">Emaila:</label>
            <input type="email" id="emaila" name="emaila" placeholder="xxxxxx@gmail.com" required> <br><br>
 
 
            <br><br>
 
            <div id="erregistroa">
                <button id="erregistratuBotoia">Erregistratu</button>
            </div>
 
        </form>
    </div>
 
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
 
    <script>
        $(document).ready(function () {
 
 
            $('#erregistratuBotoia').on('click', function (e) {
                e.preventDefault();
 
                var izenaval = $("#izena").val();
                var abizenval = $("#abizenak").val();
                var erabiltzaileval = $("#erabiltzailea").val();
                var pasahitzval = $("#pasahitza").val();
                var telefonoval = $("#telefonoa").val();
                var emailval = $("#emaila").val();
 
                $.ajax({
                    "url": "erregistroaGehitu.php",
                    "method": "POST",
                    "data": {
                        "akzioa": "erregistroaGehitu",
                        "izena": izenaval,
                        "abizenak": abizenval,
                        "erabiltzailea": erabiltzaileval,
                        "pasahitza": pasahitzval,
                        "telefono": telefonoval,
                        "emaila": emailval,
                    }
                })
                    .done(function (erregistroa) {
                        var erregistroa = JSON.parse(erregistroa);
                        if (erregistroa.status == "ok") {
                            alert("Erregistroa ondo egin da");
                            window.location.href = "../saioaHasi/saioaHasi.php";
                        } else {
                            alert("Erregistroa ez da ondo egin");
                        }
 
                    })
                    .fail(function () {
                        alert("Errorea egon da eskaeran: ");
                    })
 
 
 
            });
        });
    </script>
 
    <?php
    require_once "../footer.php";
    ?>
 
 
</body>
 
</html>