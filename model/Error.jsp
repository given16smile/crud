<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>footer</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/accueil.css">
    <!-- Bootstrap JavaScript -->
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <p id="error-message" style="color: red; text-align: center;">
        <% 
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println(errorMessage);
        }
        %>
        </p>
</body>
</html>
