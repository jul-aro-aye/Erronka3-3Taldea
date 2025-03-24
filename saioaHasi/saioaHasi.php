<html>

<head>

    <?php
    require_once "../head.php";
    ?>
    <title>Saioa Hasi</title>
    <link rel="stylesheet" href="saioaHasi.css">
</head>

<body>
    <?php

    require_once "../header.php";

    require_once "../db.php";
    $conn = konexioaSortu();

    ?>
    <div class="content-osoa">
        <h1 id="enpresaIzena">EkoTekno</h1>

        <form action="saioaHasi.php" method="POST" id="saioaHasiForm">
            <label for="erab">Erabiltzailea</label>
            <input type="text" name="erab" id="erab" pattern="^[a-z]{3}_[a-z]{3}_[a-z]{3}$"
                title="Erabiltzailearen formatua ez da okerra, formatu egokia (xxx_xxx_xxx) da." required> <br><br>

            <label for="pasa">Pasahitza</label>
            <input type="password" name="pasa" id="pasa" required> <br><br>

            <div id="divBotoia">
                <button id="saioaBotoia2">Saioa Hasi</button>
            </div>

        </form>
        <p>Erregistratu nahi duzu? Klikatu hemen <a href="../erregistroOrria/erregistroOrria.php"
                class="erregistratuLink">Erregistratu</a></p>


    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {

            $('#saioaBotoia2').on('click', function (e) {

                e.preventDefault();

            });

            $('#saioaBotoia2').on('click', function () {

                var erabiltzaileval = $("#erab").val();
                var pasahitzval = $("#pasa").val();

                $.ajax({
                    "url": "saioaBalidatu.php",
                    "method": "GET",
                    "data": {
                        "akzioa": "konprobatuSaioa",
                        "erabiltzailea": erabiltzaileval,
                        "pasahitza": pasahitzval,
                    }
                })
                    .done(function (konprobazioa) {

                        var saioKop = JSON.parse(konprobazioa);
                        if (saioKop.kopurua > 0) {
                            alert("Saioa hasi da");
                            window.history.back();
                        } else {
                            alert("Erabiltzaile eta pasahitz okerrak, saiatu berriro");
                        }
                        
                    })
                    .fail(function () {
                        alert("gaizki joan da");
                    })


            });
        });

    </script>

    <script>
        $(document).ready(function () {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('logout')) {
                alert('Saioa amaitu duzu.');
            }
        });
    </script>


    <?php

    require_once "../footer.php";

    ?>

</body>

</html>