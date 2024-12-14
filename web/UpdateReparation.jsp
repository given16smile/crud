<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
    Reparation r = (Reparation) request.getAttribute("reparationForm");
    Garage gar = new Garage(r.getIdGarage(), null);
    gar.getById();
    Mecanicien mec = new Mecanicien(r.getIdMecanicien(), null);
    mec.getById();
    Car car = new Car(r.getIdCar(), 0, 0);
    car.getById();
    Model model = new Model(car.getIdModel(), null, 0, 0, 0, null);
    model.getById();
    Brand b = new Brand(model.getIdBrand(), null);
    b.getById();
    EnginType moto = new EnginType(model.getIdEnginType(), null);
    moto.getById();
    BodyType carrosserie = new BodyType(model.getIdBodyType(), null);
    carrosserie.getById();
    List<Car> cars = (List<Car>) request.getAttribute("ListCar");
    List<Model> models = (List<Model>) request.getAttribute("ListModel");
    List<Mecanicien> mecaniciens = (List<Mecanicien>) request.getAttribute("ListMecaniciens");
    List<Garage> garages = (List<Garage>) request.getAttribute("ListGarages");
    List<Brand> brands = (List<Brand>) request.getAttribute("ListBrand");
    List<EnginType> motorisations = (List<EnginType>) request.getAttribute("ListMoteur");
    List<BodyType> carrosseries = (List<BodyType>) request.getAttribute("ListCarrosserie");
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
    <input type="hidden" name="action" value="update">
        <h1>Reparation</h1> 
        <input type="hidden" name="idrep" value="<%= r.getIdReparation() %>">
        Date début :
        <input type="date" name="dateDebut" value="<%= r.getDateDebutReparation() %>">
        <br>
        Date fin :
        <input type="date" name="dateFin" value="<%= r.getDateFinReparation() %>">
        <br>
        Mecanicien:
         <select name="idMecanicien">
            <option value="<%= r.getIdMecanicien() %>" selected><%= mec.getName() %></option>
            <% for(Mecanicien m : mecaniciens) {
                if (m.getIdMecanicien() != r.getIdMecanicien()) { %>
                    <option value="<%= m.getIdMecanicien() %>"><%= m.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Garage:
         <select name="idGarage">
           <option value="<%= r.getIdGarage() %>" selected><%= gar.getName() %></option>
            <% for(Garage gr : garages) {
                if (gr.getIdGarage() != r.getIdGarage()) { %>
                    <option value="<%= gr.getIdGarage() %>"><%= gr.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Client :
        <input type="text" value="<%= r.getClient() %>" name="nomClient">
        Brand:
        <select name="idbrand">
            <option value="<%= model.getIdBrand() %>" selected><%= b.getBrand() %></option>
        </select>
        <br>
        <input type="hidden" name="idcar" value="<%= car.getIdCar() %>">
        Model:
       <select name="idmodel">
            <option value="<%= car.getIdCar() %>" selected><%= model.getName() %></option>
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
        <input type="text" value="<%= r.getDescription() %>" name="descri">
        Price:
        <input type="number" value="<%= r.getPrix() %>"name="price">
        <button class="btn btn-outline-light" type="submit" value="Valider">Valider</button>
         <a href="reparation">
            <button class="btn btn-outline-light" type="button">Retour</button>
        </a>
        <br>
      
    </form>
    <%@ include file="WEB-INF/web/Error.jsp" %>
</body>
</html>
