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

    <title>Add a new Model</title>
</head>
<body>
    <form action="modelform" method="post" id="modelform">
        <h1>Add a new Model</h1> 
        <input type="hidden" name="action" value="update">
             Brand:
        <select name="idbrand">
            <option value="<%= model.getIdBrand() %>" selected><%= b.getBrand() %></option>
            <% for(Brand brand : brands) {
                if (brand.getIdBrand() != model.getIdBrand()) { %>
                    <option value="<%= brand.getIdBrand() %>"><%= brand.getBrand() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Model:
        <input type="hidden" name="idmodel" value="<%= model.getIdModel() %>">
        <input type="text" name="model" value="<%= model.getName() %>">
        
        Body type:
        <select name="idcar">
            <option value="<%= model.getIdBodyType() %>" selected><%= carrosserie.getName() %></option>
            <% for(BodyType car : BodyTypes) {
                if (car.getIdBodyType() != model.getIdBodyType()) { %>
                    <option value="<%= car.getIdBodyType() %>"><%= car.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Engine Type:
        <select name="idmoto">
            <option value="<%= model.getIdEnginType() %>" selected><%= moto.getName() %></option>
            <% for(EnginType engine : EnginTypes) {
                if (engine.getIdEnginType() != model.getIdEnginType()) { %>
                    <option value="<%= engine.getIdEnginType() %>"><%= engine.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Color:
            <input type="color" id="color" name="color" value="<%= model.getColor() %>">

    <p>
    <button class="btn btn-outline-light" type="submit" value="update">Update</button>
    </form>
</body>
</html>
