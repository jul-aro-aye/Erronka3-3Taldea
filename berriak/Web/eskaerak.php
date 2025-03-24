<?php require_once("Style+Js.php"); ?>
<title>Eskaerak</title>
</head>
<header>
    <a href="berriak.php" class="openbtn"><i class="fa fa-home" aria-hidden="true"></i></a>
    <h1>Nire Eskaerak</h1>
</header>




<?php
session_start();

$bezeroa_id = $_SESSION["id"];

require_once("db.php");
$conn = konexioaSortu();

$sql = "SELECT 
Eskaera.idEskaera AS EskaeraID,
CONCAT(Bezeroa.Izena, ' ', Bezeroa.Abizena) AS Bezeroa,
Bezeroa.Email AS Email,
Bezeroa.Telefonoa AS Telefonoa,
Eskaera.data AS 'Eskaera Data',
Eskaera.helbidea AS Helbidea,
GROUP_CONCAT(CONCAT(biltegia.ProduktuIzena, ' (', ' x ', eskaera_biltegia.Kantitatea, ')') SEPARATOR ', ') AS Produktuak,
CONCAT(SUM(eskaera_biltegia.Kantitatea * biltegia.ProduktuPrezioa), '€') AS Guztira,
CONCAT(garraiolaria.Izena, ' ', garraiolaria.Abizenak, ' | ', garraiolaria.Enpresa) AS Garraiolaria,
eskaeraEgoera AS 'Eskaera Egoera'
FROM 
Bezeroa
INNER JOIN 
Eskaera ON Bezeroa.idBezeroa = Eskaera.idBezeroa
INNER JOIN 
eskaera_biltegia ON Eskaera.idEskaera = eskaera_biltegia.idEskaera
INNER JOIN 
biltegia ON eskaera_biltegia.ProduktuId = biltegia.ProduktuId
INNER JOIN 
garraiolaria ON Eskaera.idGarraiolaria = garraiolaria.idGarraiolaria
WHERE 
Eskaera.idBezeroa = $bezeroa_id
GROUP BY 
Eskaera.idEskaera
ORDER BY 
Eskaera.data DESC";
$result = $conn->query($sql);
$count = 0;
if ($result->num_rows > 0) {
    echo "<br><br><br><br><br><br><br><br>";
    while ($row = $result->fetch_assoc()) {
        echo '<div class="eskaera-item">';
        echo '
                <h2>Eskaera: ' . $row["EskaeraID"] . ' | ' . $row["Eskaera Data"] . '</h2>
                <h3>Datuak</h3>
                <ul>
                    <li>Bezeroa: ' . $row["Bezeroa"] . '</li>
                    <li>Email: ' . $row["Email"] . '</li>
                    <li>Telefonoa: ' . $row["Telefonoa"] . '</li>
                    <li>Helbidea: ' . $row["Helbidea"] . '</li>
                </ul>
    
                <h3>Eskatutako Produktuak:</h3>
                <p>' . $row["Produktuak"] . '</p>
    
                
                
                <!-- Footer de la solicitud -->
                <div class="eskaera-footer">
                    <h2>Guztira: ' . $row["Guztira"] . '</h2>
                <h3>Garraioa: ' . $row["Garraiolaria"] . '</h3>
                <h3>Eskaeraren Egoera: ' . $row["Eskaera Egoera"] . '</h3>
                </div>
            ';
        echo "</div>";
    }

}


