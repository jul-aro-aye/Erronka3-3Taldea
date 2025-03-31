<?php
require_once("../header.php");
require_once("../db.php");
$conn = konexioaSortu();
 
require_once("../konfigurazioa/layoutTop.php");
 
$defaultMainColor = "#f1f2f9";
$defaultFooterColor = "#808000";
 
 
$config = simplexml_load_file('konf.xml');
$mainColor = isset($config->mainColor) ? (string) $config->mainColor : $defaultMainColor;
$footerColor = isset($config->footerColor) ? (string) $config->footerColor : $defaultFooterColor;
?>
<html>
<head>
    <?php require_once "../head.php"; ?>
    <title>Konfigurazioa</title>
</head>
<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <br><br>
        <form action="konfigurazioaPost.php" method="POST" id="konfigurazioaForm">
            <input type="hidden" value="changeConfig" name="action"/>
            <div>
                <label for="mainColor">Kolore nagusia:</label>
                <input type="color" id="mainColor" name="mainColor" value="<?= $mainColor ?>" />
            </div>
            <div>
                <label for="footerColor">Footer kolorea:</label>
                <input type="color" id="footerColor" name="footerColor" value="<?= $footerColor ?>" />
            </div>
            <button type="submit" id="konfigurazioaBotoia">Gorde</button>
        </form>
    </div>
    <?php require_once "../footer.php"; ?>
</body>
</html>
 