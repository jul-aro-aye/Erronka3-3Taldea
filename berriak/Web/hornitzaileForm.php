<?php require_once("Style+Js.php"); ?>
<title>Hornitzaile Bihurtu</title>

<header>
    <a href="berriak.php" class="openbtn"><i class="fa fa-home" aria-hidden="true"></i></a>
    <h1>Hornitzaile <br> Bihurtu</h1>
</header>
<form class="registroform" action="hornitzaileGehitu.php" method="post">
    <label for="izena">Izena</label>
    <input type="text" name="izena" id="izena" required>

    <label for="deskripzioa">Deskripzioa</label>
    <textarea name="deskripzioa" id="deskripzioa" required></textarea>

    <label for="telefonoa">Telefonoa</label>
    <input type="text" name="telefonoa" id="telefonoa">

    <label for="email">Helbide Elektronikoa</label>
    <input type="email" name="email" id="email" required>

    <input class="button" type="submit" value="Erregistratu">
</form>