<?php
require_once("../header.php");
?>

<html>

<head>
    <title>Zure Karritoa</title>
    <link rel="stylesheet" href="karritoa.css">
    <?php require_once("../head.php"); ?>
</head>

<body>
    <div class="content-osoa">

        <h1 id="enpresaIzena">EkoTekno</h1>
        <br><br>

        <h2 id="zureKarritoaTitulua">Zure Karritoa</h2>
        <div id="karritoa-edukia"></div>
        <br>
        <div id="karrito-botoiak">
            <button id="erosi">Erosi</button>
        </div>


        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script>
            $(document).ready(function () {
                let $karritoaEdukia = $("#karritoa-edukia");
                let karritoa = JSON.parse(localStorage.getItem("karritoa")) || [];

                function erakutsiKarritoa() {
                    $karritoaEdukia.empty();
                    if (karritoa.length === 0) {
                        $karritoaEdukia.html("<p>Karritoa hutsik dago.</p>");
                        return;
                    }
                    karritoa.forEach((produktua, index) => {
                        let produktuaDiv = $("<div>").html(`
                <span>${produktua.izena} - $${produktua.prezioa} x ${produktua.kopurua}</span>
                <button class="ezabatu" data-index="${index}">Ezabatu</button>
            `);
                        $karritoaEdukia.append(produktuaDiv);
                    });
                }

                $("#erosi").click(function () {
                    erakutsiKarritoa();
                });

                $(document).on("click", ".ezabatu", function () {
                    let index = $(this).data("index");
                    karritoa.splice(index, 1);
                    localStorage.setItem("karritoa", JSON.stringify(karritoa));
                    erakutsiKarritoa();
                });

                $("#erosi").click(function () {
                    window.location.href = "ordaindu.php"; 
                });

                erakutsiKarritoa();
            });


        </script>
    </div>

    <?php require_once("../footer.php"); ?>
</body>

</html>