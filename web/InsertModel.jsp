<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> EnginTypes=(List<EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> BodyTypes=(List<BodyType>)request.getAttribute("ListCarrosserie");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/insert.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <title>Add a new Model</title>
</head>
<body>
    <form action="modelform" method="post" id="modelform">
        <h1>Add a new Model</h1> 
        <input type="hidden" name="action" value="insert">
        Brand :
    <select name="brand">
        <% for(Brand b : brands) { %>
            <option value="<%= b.getIdBrand() %>"><%= b.getBrand() %></option>
        <% } %>
    </select>
    <p>
    Model :
    <input type="text" id="model" name="model">
    Body type :
    <select name="carrosserie">
        <% for(BodyType c : BodyTypes) { %>
            <option value="<%= c.getIdBodyType() %>"><%= c.getName() %></option>
        <% } %>
    </select>
    <p>
    Engine Type :
    <select name="motorisation">
        <% for(EnginType moteur : EnginTypes) { %>
            <option value="<%= moteur.getIdEnginType() %>"><%= moteur.getName() %></option>
        <% } %>
    </select>
    <p>
    Color:
    <input type="color" id="color" name="color">
    <p>
    <button class="btn btn-outline-light" type="submit" value="Valider">Valider</button>
    </form>
</body>
</html>
