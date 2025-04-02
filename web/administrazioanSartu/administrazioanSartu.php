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
 
 
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
 
        <script>
            $(document).ready(function () {
 
 
                $('#administrazioaSartzekoBotoia').on('click', function (e) {
                    e.preventDefault();
 
                    var enpresarenIzenaval = $("#enpresarenIzena").val();
                    var proposBarrakaval = $("#proposBarraka").val();
                    var barrakaMotaval = $("#barrakaMota").val();
                    var telefonoaval = $("#telefonoa").val();
                    var emailaval = $("#emaila").val();
 
                    console.log("Bidalitako datuak:", {
                        "akzioa": "administrazioaGehitu",
                        "enpresarenIzena": enpresarenIzenaval,
                        "proposBarraka": proposBarrakaval,
                        "barrakaMota": barrakaMotaval,
                        "telefonoa": telefonoaval,
                        "emaila": emailaval
 
                    });
 
                    $.ajax({
                        url: "administrazioaGehitu.php",
                        method: "POST",
                        data: {
                            "akzioa": "administrazioaGehitu",
                            "enpresarenIzena": enpresarenIzenaval,
                            "proposBarraka": proposBarrakaval,
                            "barrakaMota": barrakaMotaval,
                            "telefonoa": telefonoaval,
                            "emaila": emailaval
                        }
                    })
                        .done(function (administrazio) {
                            console.log("Zerbitzariaren erantzuna:", administrazio);
                            var administrazio = JSON.parse(administrazio);
                            if (administrazio.status === "ok") {
                                alert("Proposamena ondo bidali da");
                                window.location.href = "../administrazioanSartu/administrazioanSartu.php";
                            } else {
                                alert("Proposamena ez da ondo bidali: " + administrazio.error);
                            }
                        })
                        .fail(function () {
                            alert("Errorea egon da eskaeran");
                        });
                });
            });
        </script>
    </div>
    <?php
    require_once "../footer.php";
 
    ?>
 
 
 
</body>
 
</html>
 