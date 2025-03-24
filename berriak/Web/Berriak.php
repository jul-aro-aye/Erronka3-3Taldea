<?php
session_start();
?>
<?php require_once("Style+Js.php"); ?>
<title>Berriak</title>
</head>

<body>
    <header>
        <?PHP require_once("header.php"); ?>
        <h1>Berriak</h1>
        <p>Kontsultatu Re: Cycle-i buruzko azken nobedadeak</p>
    </header>
    <?PHP require_once("Sidebar.php"); ?>
    <?PHP require_once("Login.php"); ?>

    <div class="container" id="news-container">

    </div>

    <button class="load-more" id="load-more">Kargatu gehiago</button>

</body>

</html>