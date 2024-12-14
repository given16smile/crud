<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   Car car=(Car)request.getAttribute("carForm");
   Model model=new Model(car.getIdModel(),null, 0, 0, 0,null);
   model.getById();
   List <Car> cars=(List<Car>)request.getAttribute("ListCar");
   List <Model> models=(List<Model>)request.getAttribute("ListModel");
   EnginType moto=new EnginType(model.getIdEnginType(),null);
   moto.getById();
   BodyType carrosserie=new BodyType(model.getIdBodyType(),null);
   carrosserie.getById();
   Brand b=new Brand(model.getIdBrand(),null);
   b.getById();
   List <Mecanicien> mecaniciens=(List<Mecanicien>)request.getAttribute("ListMecaniciens");
   List <Garage> garages=(List<Garage>)request.getAttribute("ListGarages");
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

    <title>Reparation</title>
</head>
<body>
    <form action="reparationform" method="post" id="reparationform">
    <input type="hidden" name="action" value="insert">

        <h1>Reparation</h1> 
        Date début :
        <input type="date" name="dateDebut">
        <br>
        Date fin :
        <input type="date" name="dateFin">
        <br>
        Mecanicien:
         <select name="idMecanicien">
            <% for(Mecanicien m : mecaniciens) {%>
                <option value="<%= m.getIdMecanicien() %>" selected><%= m.getName()%></option>
           <% } %>
        </select>
        <br>
        Garage:
         <select name="idGarage">
            <% for(Garage g : garages) {%>
                <option value="<%= g.getIdGarage() %>" selected><%= g.getName()%></option>
           <% } %>
        </select>
        <br>
        Client :
        <input type="text" placeholder="Nom du Client" name="nomClient">
               Brand:
        <select name="idbrand">
            <option value="<%= model.getIdBrand() %>" selected><%= b.getBrand() %></option>
        </select>
        <br>
        <input type="hidden" name="idcar" value="<%= car.getIdCar() %>">
        Model:
       <select name="idmodel">
            <option value="<%=car.getIdCar() %>" selected><%= model.getName() %></option>
        </select>
        <br>
        
        Body type:
        <select name="idbt">
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
                <option selected><%= model.getColor() %></option>
            </select>
        <br>   
        Description :
        <input type="text" name="descri">
        Price:
        <input type="number" name="price">
        <button class="btn btn-outline-light" type="submit" value="Valider">Valider</button>
         <a href="reparation">
            <button class="btn btn-outline-light" type="button">Annuler</button>
        </a>
        <br>
      
    </form>
    <%@ include file="WEB-INF/web/Error.jsp" %>
</body>
</html>
