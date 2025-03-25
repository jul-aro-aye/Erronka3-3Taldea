<html>

<head>
    <?php
    require_once("../head.php");
    ?>

    <title>Erregistroa</title>
</head>

<body>
    <?php
    require_once("../header.php");
    ?>
    <div class="content-osoa">
    <h1 id="enpresaIzena">EkoTekno</h1>
    <br><br>
    <form id="erregistroForm">
        <label for="izenAbizenak">Izen-Abizenak:</label>
        <input type="text" id="izenAbizenak" name="izenAbizenak" required placeholder="Izena Abizena Abizena"> <br><br>
        <label for="erabiltzailea">Erabiltzailea:</label>
        <input type="text" id="erabiltzailea" name="erabiltzailea" required pattern="^[a-z]{3}_[a-z]{3}_[a-z]{3}$"
            title="Erabiltzailearen formatua ez da okerra, formatu egokia (xxx_xxx_xxx) da." placeholder="xxx_xxx_xxx">
        <br><br>
        <label for="pasahitza">Pasahitza:</label>
        <input type="password" id="pasahitza" name="pasahitza" required
            pattern="^[a-zA-Z0-9!@#$%^&*()_+={}[\]:;<>,.?/-]{1,8}$"
            title="8 karakter maximo eduki behar ditu, letrak, simbolo eta zenbakiak erabiliz"> <br><br>
        <label for="telefono">Telefonoa:</label>
        <input type="text" id="telefono" name="telefono" required placeholder="000000000"> <br><br>
        <label for="emaila">Emaila:</label>
        <input type="email" id="emaila" name="emaila" placeholder="xxxxxx@gmail.com" required> <br><br>
        <label for="jaio_urtea">Jaio Urtea:</label>
        <input type="date" id="jaio_urtea" name="jaio_urtea" placeholder="0000-00-00" required> <br><br>

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

                var izenabizenval = $("#izenAbizenak").val();
                var erabiltzaileval = $("#erabiltzailea").val();
                var pasahitzval = $("#pasahitza").val();
                var telefonoval = $("#telefono").val();
                var emailval = $("#emaila").val();
                var jaioval = $("#jaio_urtea").val();

                $.ajax({
                    "url": "erregistroaGehitu.php",
                    "method": "POST",
                    "data": {
                        "akzioa": "erregistroaGehitu",
                        "izenAbizenak": izenabizenval,
                        "erabiltzailea": erabiltzaileval,
                        "pasahitza": pasahitzval,
                        "telefono": telefonoval,
                        "emaila": emailval,
                        "jaio_urtea": jaioval,
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