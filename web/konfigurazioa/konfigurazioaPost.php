<?php
 
function changeConfig($inputValue)
{
    // XML konfigurazio fitxategiaren bidea definitu
    $xmlFile = '/konf.xml';
 
    // XML konfigurazioa kargatu
    $config = simplexml_load_file($xmlFile);
    if ($config === false) {
        die("Errorea: Ezin da XML fitxategia kargatu.");
    }
 
    $config->mainColor = $inputValue["mainColor"];
    $config->menuColor = $inputValue["menuColor"];
   
    if (!$config->asXML($xmlFile)) {
        die("Errorea: Ezin da XML fitxategia gorde.");
    }
}
 
// Egiaztatu formularioa bidali dela eta ekintza zuzena dela
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['action']) && $_POST['action'] === 'changeConfig') {
    $xmlFile = 'konf.xml';
    $config = simplexml_load_file($xmlFile);
   
    if ($config === false) {
        die("Errorea: XML fitxategia ezin da kargatu.");
    }
 
    $mainColor = $_POST['mainColor'] ?? '#f1f2f9';
    $menuColor = $_POST['menuColor'] ?? '#808000';
   
    $config->mainColor = $mainColor;
    $config->menuColor = $menuColor;
 
    if (!$config->asXML($xmlFile)) {
        die("Errorea: Ezin izan da XML fitxategia gorde.");
    }
 
    // Birbideratu orri nagusira
    header('Location: ../zerbitzuOrria/zerbitzuOrria.php');
    exit();
}
?>