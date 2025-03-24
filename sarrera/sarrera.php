    <html>

    <head>
        <?php

        // if (!isset($_SESSION['username'])) {
    //     // header('Location: login.php');  // Redirige a la página de login si no está logueado
    //     // exit();
    // }
        require_once "../head.php";

        ?>
        <link rel="stylesheet" href="sarrera.css">
        <title>Sarrera</title>

    </head>

    <body>
        <?php
        require_once "../header.php";
        ?>
        <div class="content-osoa">
            <h1 id="enpresaIzena">EkoTekno</h1>

            <div id="zerAurkitu">
                <h3 class="sarreraTitulua1">Zer aurkituko duzu EkoTeknon?</h3>

                <img src="../CSS+Irudiak/ZerAurkitu.jpg" alt="Zer aurkitu argazkia" id="zerAurkituArgazkia" width="200px" height="200px"
                    ;>
                <ul id="zerAurkituLista">
                    <li>Ordenagailuak eta osagarriak – Eramangarriak, mahaigaineko PC-ak, monitoreak, teklatuak eta gehiago.
                    </li>
                    <li>Smartphone-ak eta osagarriak – Azken modeloak, fundak, kargagailuak eta gailu adimendunak.
                    </li>
                    <li>Gadget-ak eta gailu adimendunak – Smartwatch-ak, entzungailuak, bozgorailu adimendunak eta domotika.
                    </li>
                    <li>Osagaiak eta gaming-a – Txartel grafikoak, prozesadoreak, gaming-aulkiak eta zure esperientzia
                        hobetzeko behar duzun guztia.</li>
                </ul>
            </div>
            <div id="konpromisoa">
                <h3 class="sarreraTitulua2">Kalitatearekiko konpromisoa eta arreta pertsonalizatua</h3>

                <img src="../CSS+Irudiak/Konpromiso.jpg" alt="Konpromisoa argazkia" id="konpromisoaArgazkia" width="200px"
                    height="200px" ;>
                <p class="konpromisoaTextua">EkoTekno-n, teknologiaren zaleak gara eta zerbitzu hurbila eta fidagarria
                    eskaintzea gustatzen zaigu. Zure behar eta aurrekontura egokitutako produktua aurkitzen lagunduko
                    dizugu. Gure bezeroekin dauzkagun konpromisoa da gure lehentasuna eta zure iritzia gurekin partekatzea. 
                </p>

            </div>

        </div>

        <script>
            // var cookiak = confirm("eKoTekno-n, zure nabigazio esperientzia hobetzeko, gure webgunearen erabilera aztertzeko eta eskaintzen dizkizugun edukia pertsonalizatzeko cookie-ak erabiltzen ditugu. Gure webgunean jarraituz, cookie-ak erabiltzea onartzen duzu gure cookie politikarekin bat etorriz. Cookie-en lehentasunak edozein unetan kudeatu ditzakezu zure nabigatzailearen konfigurazioaren bidez. Erabiltzen ditugun cookie-ak eta nola kontrolatu ditzakezun jakiteko, kontsultatu gure Pribatutasun Politika.");
            // if (!cookiak) {
            //     window.history.back();
            // }

        </script>

        <?php
        require_once "../footer.php";
        ?>

    </body>

    </html>