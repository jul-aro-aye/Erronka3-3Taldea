<?php
session_start();
session_destroy();
header("Location: saioaHasi.php?logout=1");
exit();
?>