<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   Car car=(Car)request.getAttribute("carForm");
   Model model=new Model(car.getIdModel(),null, 0, 0, 0,null);
   List <Car> cars=(List<Car>)request.getAttribute("ListCar");
   List <Model> models=(List<Model>)request.getAttribute("ListModel");
   EnginType moto=new EnginType(model.getIdEnginType(),null);
   moto.getById();
   BodyType carrosserie=new BodyType(model.getIdBodyType(),null);
   carrosserie.getById();
   Brand b=new Brand(model.getIdBrand(),null);
   b.getById();
   List <Vendeur> vendeurs=(List<Vendeur>)request.getAttribute("ListVendeurs");
   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> motorisations=(List<EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> carrosseries=(List<BodyType>)request.getAttribute("ListCarrosserie");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/insert.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <title>Vente</title>
</head>
<body>
    <form action="venteform" method="post" id="venteform">
    <input type="hidden" name="action" value="insert">

        <h1>Vente</h1> 
        Date de la Vente :
        <input type="date" name="dateVente">
        <br>
        Vendeur :
         <select name="idVendeur">
            <% for(Vendeur vendeur : vendeurs) {%>
                <option value="<%= vendeur.getIdVendeur() %>" selected><%= vendeur.getName()%></option>
           <% } %>
        </select>
        <br>
        Client :
        <input type="text" placeholder="Nom du Client" name="nomClient">
        Brand :
         <select name="idbrand">
            <% for(Brand brand : brands) {%>
                <option value="<%= model.getIdBrand() %>" ><%= brand.getBrand() %></option>
            <% } %>
        </select>
        <p>
        <input type="hidden" name="idModel" value="<%= model.getIdModel()%>">
        <input type="hidden" name="idcar" value="<%= car.getIdCar()%>">
        Body type :
        <select name="idcaross">
            <% for(BodyType bt : carrosseries) { %>
                    <option value="<%=  model.getIdBodyType() %>"><%= bt.getName() %></option>
            <%  } %>
        </select>
        <p>
        Engine Type :
         <select name="idmoto">
            <% for(EnginType engine : motorisations) {%>
                <option value="<%= model.getIdEnginType() %>" ><%= engine.getName() %></option>
            <% } %>
        </select>
        <p>
        Color:
            <input type="color" id="color" name="color" value="<%= model.getColor() %>">
        <br>
        Price:
        <input type="number" name="price" value="<%= car.getPrix() %>">
        <button class="btn btn-outline-light" type="submit" value="Valider">Valider</button>
         <a href="model">
            <button class="btn btn-outline-light" type="button">Retour</button>
        </a>
        <br>
      
    </form>
    <%@ include file="WEB-INF/web/Error.jsp" %>
</body>
</html>
