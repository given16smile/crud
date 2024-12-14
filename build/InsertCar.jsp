<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   Model model=(Model)request.getAttribute("modelForm");
   EnginType moto=new EnginType(model.getIdEnginType(),null);
   moto.getById();
   BodyType carrosserie=new BodyType(model.getIdBodyType(),null);
   carrosserie.getById();
   Brand b=new Brand(model.getIdBrand(),null);
   b.getById();
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

    <title>Add a new Car</title>
</head>
<body>
    <form action="carform" method="post" id="carform">
        <h1>Add a new Car</h1> 
        <input type="hidden" name="action" value="insert">
        Brand:
        <select name="idbrand">
            <option value="<%= model.getIdBrand() %>" selected><%= b.getBrand() %></option>
        </select>
        <br>
        Model:
        <input type="hidden" name="model" value="<%= model.getIdModel() %>">
        <input type="text" name="namemodel" value="<%= model.getName() %>">
        
        Body type:
        <select name="idcar">
            <option value="<%= model.getIdBodyType() %>" selected><%= carrosserie.getName() %></option>
        </select>
        <br>
        Engine Type:
        <select name="idmoto">
            <option value="<%= model.getIdEnginType() %>" selected><%= moto.getName() %></option>
        </select>
        <br>
        Color:
        <select name="color">
            <option selected ><%= model.getColor() %></option>
        </select>
        <br>
        Prix:
            <input type="number" id="price" name="price">

        <br>
    <button class="btn btn-outline-light" type="submit" value="Valider">Valider</button>
    </form>
</body>
</html>
