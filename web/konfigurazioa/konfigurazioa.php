<?php
require_once("../header.php");
 
 
require_once("../db.php");
 
$conn = konexioaSortu();
?>
<html>
 
<head>
    <?php
    require_once "../head.php";
    ?>
    <title>Konfigurazioa</title>
 
</head>
 
<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <?php
        $config = simplexml_load_file('konf.xml');
    ?>
    <br><br>
    <form action="konfigurazioaPost.php" method="POST" id="konfigurazioaForm">
        <input type="hidden" value="changeConfig" name="action"/>
        <div>
            <div>
                <label for="mainColor">Kolore nagusia:</label>
            </div>
            <div>
                <input type="color" id="mainColor" name="mainColor" value="<?=$config->mainColor?>" />
            </div>
        </div>
        <div>
            <div>
                <label for="footerColor">Footer kolorea:</label>
            </div>
            <div>
                <input type="color" id="footerColor" name="footerColor" value="<?=$config->footerColor?>" />
            </div>
        </div>
        <button type="submit" id="konfigurazioaBotoia">Gorde</button>
    </form>
 
    </div>
    <?php
    require_once "../footer.php";
 
    ?>
 
 
</body>
 
</html>