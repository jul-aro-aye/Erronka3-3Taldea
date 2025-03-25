<html>

<head>
    <?php

    require_once("../head.php");

    ?>

    <title>Hornitzaile Bihurtu</title>
</head>

<body>
    <?php
    require_once("../header.php");

    ?>
    <div class="content-osoa">
        <h1 id="enpresaIzena">EkoTekno</h1>


        <form id="hornitzaileForm">
            <label for="enpresaIzena">Enpresaren Izena:</label><br>
            <input type="text" id="enpresaIzena" name="enpresaIzena" required placeholder="Izena"> <br><br>
            <label for="kokapena">Kokapena:</label><br>
            <input type="text" id="kokapena" name="kokapena" required placeholder="Herria,Herrialdea"> <br><br>
            <label for="banatzaileKop">Banatzaile Kopurua:</label><br>
            <input type="number" id="banatzaileKop" name="banatzaileKop" required> <br><br>
            <label for="telefono">Telefonoa:</label><br>
            <input type="text" id="telefono" name="telefono" required placeholder="000000000"> <br><br>
            <label for="emaila">Emaila:</label><br>
            <input type="email" id="emaila" name="emaila" placeholder="xxxxxx@gmail.com"> <br><br><br>


            <div id="hornitzaileBihurtu">
                <button id="hornitzaileBihurtzekoBotoia">Hornitzaile bihurtu</button>
            </div>

        </form>


        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

        <script>
            $(document).ready(function () {


                $('#hornitzaileBihurtzekoBotoia').on('click', function (e) {
                    e.preventDefault();

                    var enpresaizenval = $("#enpresaIzena").val();
                    var kokapenaval = $("#kokapena").val();
                    var banatzaileval = $("#banatzaileKop").val();
                    var telefonoval = $("#telefono").val();
                    var emailval = $("#emaila").val();

                    $.ajax({
                        "url": "hornitzaileaGehitu.php",
                        "method": "POST",
                        "data": {
                            "akzioa": "hornitzaileaGehitu",
                            "enpresaIzena": enpresaizenval,
                            "kokapena": kokapenaval,
                            "banatzaileKop": banatzaileval,
                            "telefono": telefonoval,
                            "emaila": emailval,
                        }
                    })
                        .done(function (hornitzailea) {
                            var hornitzailea = JSON.parse(hornitzailea);
                            if (hornitzailea.status == "ok") {
                                alert("Hornitzaile bihurtu zara!");
                                window.location.href = "hornitzaileBihurtu.php";
                            } else {
                                alert("Hornitzaile bihurtzeko datuak ez dira zuzenak");
                            }

                        })
                        .fail(function () {
                            alert("Errorea egon da eskaeran: ");
                        })



                });
            }); 
        </script>
    </div>
    <?php
    require_once "../footer.php";

    ?>



</body>

</html>