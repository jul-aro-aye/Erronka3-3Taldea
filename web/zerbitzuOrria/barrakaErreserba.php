<?php
require_once("../header.php");
require_once("../db.php");
 
$conn = konexioaSortu();
require_once("../konfigurazioa/layoutTop.php");
 
?>
 
<html>
 
<head>
    <title>Zure erreserba</title>
    <link rel="stylesheet" href="barrakaErreserba.css">
    <?php require_once("../head.php"); ?>
 
</head>
 
<body>
    <div class="content-osoa">
 
        <h1 id="enpresaIzena">AeroPark</h1>
        <br><br>
 
        <h2 id="zureErreserbaTitulua">Zure Erreserba</h2>
        <div id="erreserba-edukia"></div>
        <br>
        <div id="erreserba-botoiak">
            <button id="erreserbatuBotoia">Erreserbatu</button>
        </div>
 
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script>
            $(document).ready(function () {
                let $erreserbaEdukia = $("#erreserba-edukia");
                let erreserba = JSON.parse(localStorage.getItem("barrakaErreserba")) || [];
 
                function erakutsiErreserba() {
                    $erreserbaEdukia.empty();
                    if (erreserba.length === 0) {
                        $erreserbaEdukia.html("<p>Erreserba hutsik dago.</p>");
                        return;
                    }
                    erreserba.forEach((barraka, index) => {
                        let barrakaDiv = $("<div>").html(`
                <span>${barraka.izena} - €${barraka.prezioa}</span>
                <button class="ezabatu" data-index="${index}">Ezabatu</button>
            `);
                        $erreserbaEdukia.append(barrakaDiv);
                    });
                }
 
                $(document).on("click", ".ezabatu", function () {
                    let index = $(this).data("index");
                    erreserba.splice(index, 1);
                    localStorage.setItem("barrakaErreserba", JSON.stringify(erreserba));
                    erakutsiErreserba();
                });
 
                $("#erreserbatuBotoia").click(function () {
                    window.location.href = "ordaindu.php";
                });
 
                erakutsiErreserba();
            });
 
 
        </script>
    </div>
 
    <?php require_once("../footer.php"); ?>
</body>
 
</html>