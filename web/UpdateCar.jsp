<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   Car car=(Car)request.getAttribute("carForm");
   Model m = new Model(car.getIdModel(), null, 0, 0, 0,null);
   m.getById();
   EnginType moto=new EnginType(m.getIdEnginType(),null);
   moto.getById();
   BodyType carrosserie=new BodyType(m.getIdBodyType(),null);
   carrosserie.getById();
   Brand b=new Brand(m.getIdBrand(),null);
   b.getById();

   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> EnginTypes=(List<EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> BodyTypes=(List<BodyType>)request.getAttribute("ListCarrosserie");
   List <Model> models=(List<Model>)request.getAttribute("ListModel");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/insert.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <title>Update car</title>
</head>
<body>
    <form action="carform" method="post" id="carform">
        <h1>Update car</h1> 
        <input type="hidden" name="action" value="update">
             Brand:
        <select name="idbrand">
            <option value="<%= m.getIdBrand() %>" selected><%= b.getBrand() %></option>
            <% for(Brand brand : brands) {
                if (brand.getIdBrand() != m.getIdBrand()) { %>
                    <option value="<%= brand.getIdBrand() %>"><%= brand.getBrand() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Model:
        <input type="hidden" name="idcar" value="<%= car.getIdCar() %>">
         <select name="idmodel">
            <option value="<%= car.getIdModel() %>" selected><%= m.getName() %></option>
            <% for(Model modele: models) {
                if (modele.getIdModel() != car.getIdModel()) { %>
                    <option value="<%= modele.getIdModel() %>"><%= modele.getName() %></option>
            <% } %>
        </select>
        <br>
        Body type:
        <select name="idcar">
            <option value="<%=modele.getIdBodyType() %>" selected><%= carrosserie.getName() %></option>
            <% for(BodyType bt : BodyTypes) {
                if (bt.getIdBodyType() !=modele.getIdBodyType()) { %>
                    <option value="<%= bt.getIdBodyType() %>"><%= bt.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Engine Type:
        <select name="idmoto">
            <option value="<%=modele.getIdEnginType() %>" selected><%= moto.getName() %></option>
            <% for(EnginType engine : EnginTypes) {
                if (engine.getIdEnginType() !=modele.getIdEnginType()) { %>
                    <option value="<%= engine.getIdEnginType() %>"><%= engine.getName() %></option>
            <% } 
            }%>
            
        </select>
         <% } %>
        <br>
        Color:
            <input type="color" id="color" name="color" value="<%= m.getColor() %>">
        <br>
        Price:
        <input type="price" id="price" name="price" value="<%= car.getPrix()%>">
        <br>
    <button class="btn btn-outline-light" type="submit" value="update">Update</button>
    </form>
</body>
</html>
