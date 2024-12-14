<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/login.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <title>LOGIN</title>
</head>
<body>
    <form action="login" method="post" id="login">
        <h1>Sing in?</h1>

        <input type ="hidden" name="link" value=<%= request.getParameter("link")%>>
        <input type ="hidden" name="action" value=<%= request.getParameter("action")%>>
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <input type="text" name="pseudo" placeholder="Entrez votre adresse email">
        <input type="password" name="mdp" placeholder="Entrez votre mot de passe">
        <button class="btn btn-outline-light" type="submit" value="Login">Login</button>
        <a href="car">
            <button class="btn btn-outline-light" type="button">Retour</button>
        </a>
 
        <%@ include file="WEB-INF/web/Error.jsp" %>

    </form>
</body>
</html>
