<button class="openbtn" onclick="openNav()">☰</button>
<button class="loginbtn" onclick="openLogin()"><i class='fas fa-user-alt'></i></button>
<?php
if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
    echo "Ong etorri, " . $_SESSION['name'] . "!";
} else {
    echo "Hasi saioa funtzio gehiagorentzat";
}

?>
