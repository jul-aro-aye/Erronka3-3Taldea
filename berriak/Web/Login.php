<div class="topnav login" id="login">
    <a href="javascript:void(0)" class="closebtn" onclick="closeLogin()">×</a>

    <?php
    if (!isset($_SESSION['loggedin']) || $_SESSION['loggedin'] !== true) {
        echo '<h1>Saioa Hasi</h1>
        <form action="autenticacion.php" method="post">
        <label for="username">
            <i class="fas fa-user"></i>
        </label>
        <input type="text" name="username" placeholder="Erabiltzailea" id="username" required>
        <label for="password">
            <i class="fas fa-lock"></i>
        </label>
        <input type="password" name="password" placeholder="Pasahitza" id="password" required>
        <input type="submit" class="botoia" value="Sartu">
    </form>';
        echo '<p>Ez duzu konturik? <a href="registroForm.php" class"registrolink">Erregistratu Hemen!</a></p>';
    }
    ?>


    <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true): ?>
        <form action="saioaItxi.php" method="post" onsubmit="return confirm('Saioa itxi nahi duzu?');">
            <button type="submit" class="botoia itxi">Saioa Itxi</button>
        </form>

        <input type="button" class="botoia" onclick="location.href='eskaerak.php';" value="Ikusi Nire Eskaerak" />

    <?php endif; ?>

</div>