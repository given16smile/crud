<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%

   List <Car> cars=(List<Car>)request.getAttribute("ListCar");
   List <Model> models=(List<Model>)request.getAttribute("ListModel");
   List <Vendeur> vendeurs=(List<Vendeur>)request.getAttribute("ListVendeurs");
   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> motorisations=(List <EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> carrosseries=(List<BodyType>)request.getAttribute("ListCarrosserie");
   List<Vente> ventes=(List<Vente>)request.getAttribute("ListVente");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/accueil.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <title>Car Sales Website</title>
</head>
<body>
<%@ include file="WEB-INF/web/menu.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-2" id="mynavbar">
            <form action="vente" method="get">
                <!-- Champs de recherche pour la date entre deux dates -->
                Date entre :
                <br>
                <input type="date" name="d1">
                <br>
                et
                <br>
                <input type="date" name="d2">
                <br>
                 Brand:
                    <select name="brand">
                        <option></option>
                        <% for(Brand b : (List<Brand>)request.getAttribute("ListBrand")) { %>
                            <option value="<%= b.getIdBrand() %>"><%= b.getBrand() %></option>
                        <% } %>
                    </select>
                    <br>
                    Model:
                    <select name="model">
                        <option></option>
                        <% for(Model m : (List<Model>)request.getAttribute("ListModel")) { %>
                            <option value="<%= m.getIdModel() %>"><%= m.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                Vendeur:
                <select name="idVendeur">
                    <option></option>
                    <% for(Vendeur vendeur : vendeurs) {%>
                        <option value="<%= vendeur.getIdVendeur() %>"><%= vendeur.getName()%></option>
                    <% } %>
                </select>
                <br>
                <br>
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
        </div>
            <div class="col-8 ">
                <table class="table table-bordered">
                        <tr>
                        <td><a href="#">Date de la Vente</a></td>
                            <td><a href="#">Vendeur</a></td>
                            <td><a href="#">Client</a></td>
                            <td><a href="#">Brand</a></td>
                            <td><a href="model">Model</a></td>
                            <td><a href="#">Body Type</a></td>
                            <td><a href="#">Engin Type</a></td>
                            <td scope="col">Color</td>
                            <td scope="col">Prix</td>
                            <td scope="col"></td>
                            <td scope="col"></td>
                        </tr>
                    </thead>
                    <tbody>
                    <% for(Vente v : ventes) { 
                        Brand idBrand = null;
                        BodyType idCarrosserie=null;
                        EnginType idMotorisation=null;
                        Model idModel=null;
                        Vendeur idVendeur=null;
                       for(Vendeur vendeur : vendeurs) {
                               if(vendeur.getIdVendeur() == v.getIdVendeur()) {
                                   try {
                                    idVendeur = vendeur;
                                       break;
                                   } catch(Exception e) {
                                       e.printStackTrace();
                                   }
                               }
                           } 
                        Car car=new Car(v.getIdCar(),0,0);
                        car.getById();
                        for(Model m : models) {
                            if(car.getIdModel()==m.getIdModel()) {
                            try {
                                idModel = m;
                                break;
                            } catch(Exception e) {
                                throw e;
                            }
                          } 
                        }  
                        for(Brand br : brands) {
                        if(br.getIdBrand() == idModel.getIdBrand()) {
                            try {
                                idBrand = br;
                                break;
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                      } 
                        for(BodyType cr : carrosseries) {
                        if(cr.getIdBodyType() == idModel.getIdBodyType()) {
                            try {
                                idCarrosserie = cr;
                                break;
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } 
                    for(EnginType motor : motorisations) {
                        if(motor.getIdEnginType() == idModel.getIdEnginType()) {
                            try {
                                idMotorisation = motor;
                                break;
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                
                     if(idBrand != null && idCarrosserie != null && idMotorisation != null && idModel!=null && idVendeur!=null && car!=null) { %>
                            <tr>
                                <td><%=v.getDateVente()%></td>
                                <td><%=idVendeur.getName()%></td>
                                <td><%=v.getClient()%></td>
                                <td><%= idBrand.getBrand() %></td>
                                <td><%= idModel.getName() %></td>
                                <td><%= idCarrosserie.getName()%></td>
                                <td><%= idMotorisation.getName() %></td>
                                <td><%= idModel.getColor() %></td>
                                <td><%=car.getPrix()%></td>
                                <td><a href="Login.jsp?action=update&link=./venteform&id=<%= v.getIdVente() %>"><button class="btn btn-outline-light" type="submit">Update</button></a></td>
                                <td><a href="Login.jsp?action=delete&link=./vente&id=<%= v.getIdVente() %>"><button class="btn btn-outline-light" type="submit">Delete</button></a></td>
                            </tr>   
                    <% }
                    }
                %>
                    </tbody>
                </table>
            </div>
    <%@ include file="WEB-INF/web/Footer.jsp" %>
</body>
</html>
