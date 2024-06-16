<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Page de Connexion Admin</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">

<style>
    .snackbar {
        visibility: hidden;
        min-width: 250px;
        margin-left: -125px;
        background-color: #333;
        color: #fff;
        text-align: center;
        border-radius: 2px;
        padding: 16px;
        position: fixed;
        z-index: 1;
        left: 50%;
        bottom: 30px;
        font-size: 17px;
    }

    .snackbar.show {
        visibility: visible;
        -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
        animation: fadein 0.5s, fadeout 0.5s 2.5s;
    }

    .snackbar.success {
        background-color: #4CAF50;
    }

    .snackbar.error {
        background-color: #f44336;
    }

    @-webkit-keyframes fadein {
        from {bottom: 0; opacity: 0;} 
        to {bottom: 30px; opacity: 1;}
    }

    @keyframes fadein {
        from {bottom: 0; opacity: 0;}
        to {bottom: 30px; opacity: 1;}
    }

    @-webkit-keyframes fadeout {
        from {bottom: 30px; opacity: 1;} 
        to {bottom: 0; opacity: 0;}
    }

    @keyframes fadeout {
        from {bottom: 30px; opacity: 1;}
        to {bottom: 0; opacity: 0;}
    }
</style>
</head>
<body>

<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

<div class="main">

    <!-- Formulaire de Connexion -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="images/admin.png" alt="image de connexion">
                    </figure>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Page Admin</h2>
                    <form method="post" action="loginAdmin" class="register-form" id="login-form">
                        <div class="form-group">
                            <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="username" id="username" placeholder="Votre Nom" />
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Mot de passe" />
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                            <label for="remember-me" class="label-agree-term"><span><span></span></span>Se souvenir de moi</label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="Connexion" />
                        </div>
                    </form>
                    <div class="social-login">
                        <span class="social-label">Ou connectez-vous avec</span>
                        <ul class="socials">
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>

<div id="snackbar" class="snackbar"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="js/main.js"></script>

<script type="text/javascript">
    function showSnackbar(message, isError = false) {
        var snackbar = document.getElementById("snackbar");
        snackbar.className = "snackbar show " + (isError ? "error" : "success");
        snackbar.innerText = message;
        setTimeout(function() { 
            snackbar.className = snackbar.className.replace("show", ""); 
        }, 3000);
    }

    var status = document.getElementById("status").value;
    if (status === "failed") {
        showSnackbar("Vérifiez votre nom d'utilisateur ou mot de passe", true);
    } else if (status === "success") {
        showSnackbar("Connexion réussie", false);
    }
</script>

</body>
</html>
