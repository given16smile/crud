<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   Vente vente=(Vente)request.getAttribute("venteForm");
   Car voiture=new Car(vente.getIdCar(),0,0);
   voiture.getById();
   Model model=new Model(voiture.getIdModel(),null, 0, 0, 0,null);
   model.getById();
   EnginType moto=new EnginType(model.getIdEnginType(),null);
   moto.getById();
   BodyType carrosserie=new BodyType(model.getIdBodyType(),null);
   carrosserie.getById();
   Brand b=new Brand(model.getIdBrand(),null);
   b.getById();
   Vendeur vendeur=new Vendeur(vente.getIdVendeur(),null);
   vendeur.getById();
   List <Car> cars=(List<Car>)request.getAttribute("ListCar");
   List <Model> models=(List<Model>)request.getAttribute("ListModels");
   List <Vendeur> vendeurs=(List<Vendeur>)request.getAttribute("ListVendeurs");
   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> motorisations=(List <EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> carrosseries=(List<BodyType>)request.getAttribute("ListCarrosserie");
   List<Vente> ventes=(List<Vente>)request.getAttribute("ListVente");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/insert.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <title>Update Model</title>
</head>

<body>
<form action="venteform" method="post" id="venteForm">
    <h1>Update a Vente</h1>  
    <input type="hidden" name="action" value="update">
     <input type="hidden" name="idVente" value="<%= vente.getIdVente() %>">
        Date de la Vente :
        <input type="date" name="dateVente" value="<%= vente.getDateVente() %>">
        Client :
        <input type="text" placeholder="Nom du Client" name="nomClient" value="<%= vente.getClient()%>">

        Vendeur :
         <select name="idVendeur">
            <option value="<%= vente.getIdVendeur() %>" selected><%= vendeur.getName() %></option>
            <% for(Vendeur v : vendeurs) {
                if (v.getIdVendeur() != vente.getIdVendeur()) { %>
                    <option value="<%= v.getIdVendeur() %>"><%= v.getName() %></option>
            <%     } 
            } %>
        </select>
        <br>
        Brand:
        <select name="idbrand">
            <option value="<%= model.getIdBrand() %>" selected><%= b.getBrand() %></option>
        </select>
        <br>
        <input type="hidden" name="idcar" value="<%= voiture.getIdCar() %>">
        Model:
       <select name="idmodel">
            <option value="<%= voiture.getIdCar() %>" selected><%= model.getName() %></option>
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
        Price:
        <input type="number" name="price" value="<%= voiture.getPrix() %>">
        
        <button class="btn btn-outline-light" type="submit" value="Update">Update</button>
</form>
</body>
</html>
