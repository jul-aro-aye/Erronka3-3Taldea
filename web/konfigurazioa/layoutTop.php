<!DOCTYPE HTML>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/favicon.ico">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <title>Konfigurazioa</title>
   
 
    <!-- External -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <?php
    //XMLko konfiguraziotik hartzen dute informazioa
    $config = simplexml_load_file('../konfigurazioa/konf.xml');
    $mainColor = $config->mainColor;
    $menuColor = $config->menuColor;
 
    ?>
 
    <style>
        :root {
            --mainColor: <?= $mainColor ?>;
            --menuColor: <?= $menuColor ?>;
        }
 
        /* AZPIAN EGON BEAHR DA CSS-a mainColor eta menuColor erabiltzen dituztenak */
    </style>
    <!-- Internal -->
    <link href="../CSS+Irudiak/css.css" rel="stylesheet">
 
 
</head>
 
<body class=""></body>