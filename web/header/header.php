<?php
require_once "../saioaHasi/session.php";
?>
 
<div class="header">
    <a href="javascript:void(0);" class="ikonoa">
        <i class="fa fa-bars" id="irudia"></i>
    </a>
    <div id="aukerak">
        <ul id="nabigazioBarra">
            <li><a class="produktuak" href="../zerbitzuOrria/zerbitzuOrria.php">Zerbitzuak</a></li><br>
            <li><a class="hornitzailea" href="../administrazioanSartu/administrazioanSartu.php">Zerbitzu bat duzu?</a>
            </li><br>
            <li><a class="ekintza" href="../ekintzak/ekintzak.php">Hurrengo ekintzak</a></li><br>
            <li><a class="ekintza" href="../balorazioa/balorazioa.php">Balorazioak</a></li><br>
            <li><a class="konfigurazioa" href="../konfigurazioa/konfigurazioa.php">Konfigurazioa</a></li>
        </ul>
    </div>
 
    <div class="kontuak">
        <?php if (isset($_SESSION['erabiltzailea'])): ?>
            <a href="../saioaHasi/saioaItxi.php" id="saioaItxiLink">Saioa Itxi</a>
        <?php else: ?>
            <a href="../saioaHasi/saioaHasi.php"><i class="fa fa-user" id="login"></i></a>
        <?php endif; ?>
        <button class="translate" data-lang="en">Euskera</button>
        <button class="translate" data-lang="eu">Ingelesa</button>
 
 
    </div>
</div>
 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script type="text/javascript">
    function googleTranslateElementInit() {
        new google.translate.TranslateElement({ pageLanguage: 'eu', autoDisplay: false }, 'google_translate_element');
    }
 
    $(document).ready(function () {
 
        $.getScript("//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit");
 
 
        function changeLanguage(lang) {
            var translateCookie = lang === 'eu' ? '/eu/en' : '/en/eu';
            document.cookie = "googtrans=" + translateCookie + "; path=/; domain=" + window.location.hostname;
            location.reload();
        }
 
 
        $(".translate").click(function () {
            var lang = $(this).attr("data-lang");
            changeLanguage(lang);
        });
 
 
        setInterval(function () {
            $(".goog-te-banner-frame").hide();
            $("iframe").contents().find(".goog-te-banner-frame").hide();
        }, 500);
    });
</script>
 
<script>
    $(document).ready(function () {
        function hideGoogleTranslateBanner() {
            setInterval(function () {
                $(".goog-te-banner-frame").hide();
                $("iframe").contents().find(".goog-te-banner-frame").hide();
                $("body").css("top", "0px");
            }, 100);
        }
 
        hideGoogleTranslateBanner();
    });
</script>
 
<script>
    $(document).ready(function () {
        $(".ikonoa").click(function (event) {
            event.stopPropagation();
            mugikorNabigazioa();
        });
 
        $("#aukerak").click(function (event) {
            event.stopPropagation();
        });
 
        $(document).click(function () {
            var ilara = document.getElementById("aukerak");
            if (ilara.style.display === "block") {
                ilara.style.display = "none";
            }
        });
    });
 
    function mugikorNabigazioa() {
        var ilara = document.getElementById("aukerak");
        ilara.style.display = (ilara.style.display === "block") ? "none" : "block";
    }
</script>
 
