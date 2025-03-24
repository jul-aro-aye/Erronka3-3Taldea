<?php
session_start();
?>
<?php require_once("Style+Js.php"); ?>
<title>Guri Buruz</title>
</head>

<body>
    <header>
        <?PHP require_once("header.php"); ?>
        <h1>Kontaktua</h1>
        <p>Kontaktatu Gurekin</p>
    </header>
    <?PHP require_once("Sidebar.php"); ?>
    <?PHP require_once("Login.php"); ?>
    <div class="container">
        <section class="contact-info">
            <h2>Kontaktu Informazioa</h2>
            <p><strong>Helbidea:</strong> Kale Nagusia 15, 1. solairua, 20010 Donostia, Gipuzkoa</p>
            <p><strong>Telefonoa:</strong> 943 123 456</p>
            <p><strong>Posta Elektronikoa:</strong> info@recycle.com</p>
            <p><strong>Ordutegia:</strong></p>
            <ul>
                <li>Asteartea - Ostirala: 9:00 - 18:00</li>
                <li>Larunbata: 10:00 - 14:00</li>
                <li>Igandea eta Astelehena: Itxita</li>
            </ul>
        </section>
    </div>
</body>

</html>