<?php
require_once("Style+Js.php");
session_start();
require_once("db.php");
$conn = konexioaSortu();

if (!isset($_SESSION["id"])) {
    die("Errorea: Bezeroa saioa hasi behar du.");
}

$bezeroa_id = $_SESSION["id"];


if (isset($_GET['product'])) {
    $productId = $_GET['product'];
    $cart = [$productId => ['name' => $productId, 'quantity' => 1]];

    $_SESSION['cart'] = $cart;
} elseif (isset($_GET['cart'])) {
    $cart = json_decode(urldecode($_GET['cart']), true);
    $_SESSION['cart'] = $cart;
} else {
    $cart = isset($_SESSION['cart']) ? $_SESSION['cart'] : [];
}

// Bezeroaren datuak eskuratzea
try {
    $stmt = $conn->prepare("SELECT izena, abizena, email FROM bezeroa WHERE idBezeroa = ?");
    $stmt->bind_param("i", $bezeroa_id);
    $stmt->execute();
    $stmt->bind_result($izena, $abizena, $emaila);
    $stmt->fetch();
    $stmt->close();
} catch (Exception $e) {
    die("Errorea: Bezeroaren datuak ezin dira eskuratu.");
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $helbidea = $_POST["helbidea"] ?? "";

    $sql = "SELECT idGarraiolaria FROM garraiolaria ORDER BY RAND() LIMIT 1";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $idGarraiolaria = $row["idGarraiolaria"];
    }

    if (empty($helbidea)) {
        die("Errorea: Helbidea bete behar da.");
    }

    $eskaeraEgoera = "Prozesatzen";

    // Eskaria sartzea
    $stmt = $conn->prepare("INSERT INTO eskaera (data, helbidea, eskaeraEgoera, idBezeroa, idGarraiolaria) VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?)");
    $stmt->bind_param("ssii", $helbidea, $eskaeraEgoera, $bezeroa_id, $idGarraiolaria);
    $stmt->execute();
    $eskaera_id = $stmt->insert_id;
    $stmt->close();

    if ($eskaera_id) {
        foreach ($_SESSION["cart"] as $ProduktuId => $kantitatea) {
            // Produktuaren prezioa eskuratzea
            $stmt = $conn->prepare("SELECT ProduktuPrezioa FROM biltegia WHERE ProduktuId = ?");
            $stmt->bind_param("i", $ProduktuId);
            $stmt->execute();
            $stmt->bind_result($ProduktuPrezioa);
            $stmt->fetch();
            $stmt->close();
            $ProduktuPrezioa = floatval($ProduktuPrezioa);
            $kantitatea = isset($cart[$ProduktuId]) ? $cart[$ProduktuId]['quantity'] : 0;
            if ($ProduktuPrezioa !== null) {
                $guztiraPrezioa = $ProduktuPrezioa * $kantitatea;

                // Eskaera biltegian gordetzea
                $stmt = $conn->prepare("INSERT INTO eskaera_biltegia (idEskaera, ProduktuId, kantitatea, guztiraPrezioa) VALUES (?, ?, ?, ?)");
                $stmt->bind_param("iiid", $eskaera_id, $ProduktuId, $kantitatea, $guztiraPrezioa);
                $stmt->execute();
                $stmt->close();
            }
        }
        unset($_SESSION["cart"]);
        echo "Eskaria ondo egin da!";
        header('Location: katalogoa.php?clearCart=true');
        exit();
    } else {
        echo "Errorea: Eskaria ezin izan da sortu.";
    }
}
?>

<!DOCTYPE html>
<html>

<head>
    <title>Erosi</title>
</head>

<body>
    <header>
        <a href="katalogoa.php" class="openbtn"><i class="fas fa-reply" aria-hidden="true"></i></a>
        <h2>Erosi</h2>
    </header>
    <form class="erosiform" method="post">
        <label>Izena:</label>
        <input type="text" value="<?php echo htmlspecialchars($izena); ?>" disabled><br>
        <label>Abizena:</label>
        <input type="text" value="<?php echo htmlspecialchars($abizena); ?>" disabled><br>
        <label>Emaila:</label>
        <input type="email" value="<?php echo htmlspecialchars($emaila); ?>" disabled><br>
        <label>Helbidea:</label>
        <input type="text" name="helbidea" required><br>
        <button type="submit">Erosi</button>
    </form>
</body>

</html>