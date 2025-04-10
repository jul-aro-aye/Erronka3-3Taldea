<?php
require_once("../header/header.php");
require_once("../db.php");
$conn = konexioaSortu();

require_once("../konfigurazioa/layoutTop.php");


$sql = "SELECT DISTINCT idBarraka,izena FROM barraka";
$result = $conn->query($sql);

?>
<html>

<head>
    <?php require_once "../head.php"; ?>
    <title>Balorazioa</title>
</head>

<body>
    <div class="content-osoa">
        <h1 id="enpresaIzena">AeroPark</h1>
        <form id="balorazioaForm">
            <label for="barrakaIzena" id="barrakaIzena">Aukeratu barraka bat:<span class="asterisco">*</span></label>
            <select name="barrakaIzena" class="barrakaIzena">
                <option value="">-- Aukeratu --</option>
                <?php
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo '<option value="' . $row["izena"] . '">' . $row["izena"] . '</option>';
                    }
                } else {
                    echo '<option value="">Ez dago daturik</option>';
                }

                ?>

            </select>
            <br>
            <label for="balorazioa" id="balorazioa">Balorazioa<span class="asterisco">*</span>
                <div class="rating">
                    <span class="star" data-value="1">&#9733;</span>
                    <span class="star" data-value="2">&#9733;</span>
                    <span class="star" data-value="3">&#9733;</span>
                    <span class="star" data-value="4">&#9733;</span>
                    <span class="star" data-value="5">&#9733;</span>
                </div>
                <div id="ratingResult"></div>
                <div class="hidden" id="ratingValue"></div>
            </label>
            <br>
            <label for="iruzkina" id="iruzkina">Iruzkinak:<span class="asterisco">*</span></label>
            <br>
            <textarea name="iruzkina" class="iruzkina" rows="4" cols="20" placeholder="Idatzi iruzkina"></textarea>
            <br><br>
            <div id="baloratu">
                <button id="baloratuBotoia">Bidali</button>
            </div>

        </form>
    </div>
    <?php require_once "../footer.php"; ?>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $(".star").click(function () {
                let rating = $(this).data("value");
                $("#ratingValue").text(rating);


                $(".star").each(function () {
                    let starValue = $(this).data("value");
                    if (starValue <= rating) {
                        $(this).css("color", "gold");
                    } else {
                        $(this).css("color", "black");
                    }
                });
            });
        });

    </script>

    <script>
        $(document).ready(function () {

            $('#baloratuBotoia').on('click', function (e) {
                e.preventDefault();

                var barrakaIzenaval = $(".barrakaIzena").val();
                var izarKopuruaval = $("#ratingValue").text().trim();
                var iruzkinakval = $(".iruzkina").val().trim();

                console.log("Bidalitako datuak:", {
                    "akzioa": "balorazioaGehitu",
                    "barrakaIzena": barrakaIzenaval,
                    "izarKopurua": izarKopuruaval,
                    "zergatia": iruzkinakval
                });

                $.ajax({
                    url: "balorazioaGehitu.php",
                    method: "POST",
                    data: {
                        "akzioa": "balorazioaGehitu",
                        "barrakaIzena": barrakaIzenaval,
                        "izarKopurua": izarKopuruaval,
                        "zergatia": iruzkinakval
                    }
                })
                    .done(function (baloratu) {
                        console.log("Zerbitzariaren erantzuna:", baloratu);
                        var baloratu = JSON.parse(baloratu);
                        if (baloratu.status === "ok") {
                            alert("Barraka ondo baloratu da");
                            window.location.href = "../balorazioa/balorazioa.php";
                        } else {
                            alert("Barraka ez da ondo baloratu: " + baloratu.error);
                        }
                    })
                    .fail(function () {
                        alert("Errorea egon da eskaeran");
                    });
            });


        });
    </script>


</body>

</html>