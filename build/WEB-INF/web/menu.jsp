<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/accueil.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>
      <div class="container-fluid" col="2">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Logo</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="car">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact</a>
                        </li>
                    </ul>
                    
                <form class="d-flex ms-auto">
                </div>
            </div>
        </nav>
           <div class="col-2 shadow-lg bg-dark text-white" id="mynavbar">
                <ul class="nav nav-pills flex-column">
                 <li class="nav-item">
                        <a class="nav-link link-light" href="car">Home</a>
                    </li>
                    <li class="nav-item">
                         <a class="nav-link link-light" href="Login.jsp?action=insert&link=./modelform&id=0">New Model</a>
                    </li>
                <li class="nav-item">
                        <a class="nav-link link-light" href="Login.jsp?action=insert&link=./carform&id=0">New Car</a>
                </li>
                <li class="nav-item">
                        <a class="nav-link link-light" href="Login.jsp?action=insert&link=./vente&id=0">Historique des Ventes</a>
                </li>
                <li class="nav-item">
                        <a class="nav-link link-light" href="Login.jsp?action=insert&link=./reparation&id=0">Historique des Reparations</a>
                </li>
                </ul>
            </div>
        
</body>
</html>
