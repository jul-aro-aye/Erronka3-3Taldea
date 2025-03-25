<?php
session_start();
$isLoggedIn = isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true ? 'true' : 'false';
?>
<script>
    var isLoggedIn = <?php echo json_encode($isLoggedIn); ?>;
</script>

<?php require_once("Style+Js.php"); ?>

<title>Katalogoa</title>
</head>

<body>
    <header>
        <?PHP require_once("header.php"); ?>
        <h1>Katalogoa</h1>
        <p>Ikusi Gure Katalogoa</p>
        <div class="search-container">
            <button class="search-btn" onclick="toggleSearch()">
                <i class='fas fa-search'></i>
            </button>
            <div id="search-bar" class="search-bar">
                <input type="text" id="search-input" placeholder="Bilatu produktuak...">
                <button id="search-button">Bilatu</button>
            </div>
        </div>


    </header>
    <?PHP require_once("Sidebar.php"); ?>
    <?PHP require_once("Login.php"); ?>


    <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true): ?>
        <div id="cart-container">
            <button id="cart-icon">
                <i class='fas fa-shopping-cart'></i> <span id="cart-count">0</span>
            </button>
            <div id="cart-dropdown" class="hidden">
                <ul id="cart-items"></ul>
                <button id="checkout-button">Erosi</button>
            </div>
        </div>


    <?php endif; ?>




    <div class="container" id="prod-container">

    </div>

    <button class="load-more" id="load-more-prod">Kargatu gehiago</button>

</body>

</html>