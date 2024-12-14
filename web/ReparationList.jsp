<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<%
   List<Reparation> reparations=(List<Reparation>)request.getAttribute("ListReparations");
   List <Car> cars=(List<Car>)request.getAttribute("ListCar");
   List <Model> models=(List<Model>)request.getAttribute("ListModel");
   List <Mecanicien> mecaniciens=(List<Mecanicien>)request.getAttribute("ListMecaniciens");
   List <Garage> garages=(List<Garage>)request.getAttribute("ListGarages");
   List <Brand> brands=(List<Brand>)request.getAttribute("ListBrand");
   List <EnginType> motorisations=(List <EnginType>)request.getAttribute("ListMoteur");
   List <BodyType> carrosseries=(List<BodyType>)request.getAttribute("ListCarrosserie");
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
            <div class="col-2 shadow-lg p-3 mb-10  bg-dark text-white" id="mynavbar">
                <form id="filtre" method="get" action="reparation">
                    Date entre :
                    <br>
                    <input type="date" name="d1">
                    <br>
                    et
                    <br>
                    <input type="date" name="d2">
                    <br>
                    Garage :
                    <br>
                    <select name="garage">
                        <option></option>
                        <% for(Garage g: (List<Garage>)request.getAttribute("ListGarages")) { %>
                            <option value="<%= g.getIdGarage() %>"><%= g.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                    Mecanicien :
                    <br>
                    <select name="mecano">
                        <option></option>
                        <% for(Mecanicien mecano : (List<Mecanicien>)request.getAttribute("ListMecaniciens")) { %>
                            <option value="<%= mecano.getIdMecanicien() %>"><%= mecano.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                    Brand:
                    <br>
                    <select name="brand">
                        <option></option>
                        <% for(Brand b : (List<Brand>)request.getAttribute("ListBrand")) { %>
                            <option value="<%= b.getIdBrand() %>"><%= b.getBrand() %></option>
                        <% } %>
                    </select>
                    <br>
                    Model:
                    <br>
                    <select name="model">
                        <option></option>
                        <% for(Model m : (List<Model>)request.getAttribute("ListModel")) { %>
                            <option value="<%= m.getIdModel() %>"><%= m.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                    <br>
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form>
            </div>

            <div class="col-2 ">
                <table class="table table-bordered">
                        <tr>
                            <td><a href="#">Date debut </a></td>
                            <td><a href="#">Date fin </a></td>
                            <td><a href="#">Garage</a></td>
                            <td><a href="#">Mecanicien</a></td>
                            <td><a href="#">Client</a></td>
                            <td><a href="#">Brand</a></td>
                            <td><a href="model">Model</a></td>
                            <td><a href="#">Body Type</a></td>
                            <td><a href="#">Engin Type</a></td>
                            <td scope="col">Color</td>
                            <td scope="col">Description</td>
                            <td scope="col">Prix Reparation</td>
                            <td scope="col"></td>
                            <td scope="col"></td>
                        </tr>
                    </thead>
                    <tbody>

                    <% 
                    for(Reparation r : reparations){
                        Brand idBrand = null;
                        BodyType idCarrosserie=null;
                        EnginType idMotorisation=null;
                        Model idModel=null;
                        Mecanicien idMecanicien=null;
                        Garage idgarage=null;
                       for(Garage g : garages) {
                               if(g.getIdGarage() == r.getIdGarage()) {
                                   try {
                                    idgarage=g;
                                       break;
                                   } catch(Exception e) {
                                       e.printStackTrace();
                                   }
                               }
                           } 
                        for(Mecanicien meca : mecaniciens) {
                               if(meca.getIdMecanicien() == r.getIdMecanicien()) {
                                   try {
                                    idMecanicien=meca;
                                       break;
                                   } catch(Exception e) {
                                       e.printStackTrace();
                                   }
                               }
                           } 
                        Car car=new Car(r.getIdCar(),0,0);
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
                
                     if(idBrand != null && idCarrosserie != null && idMotorisation != null && idModel!=null && idgarage!=null && idMecanicien!=null && car!=null) { %>
                            <tr>
                                <td><%=r.getDateDebutReparation()%></td>
                                <td><%=r.getDateFinReparation()%></td>
                                <td><%=idgarage.getName()%></td>
                                <td><%=idMecanicien.getName()%></td>
                                <td><%=r.getClient()%></td>
                                <td><%= idBrand.getBrand() %></td>
                                <td><%= idModel.getName() %></td>
                                <td><%= idCarrosserie.getName()%></td>
                                <td><%= idMotorisation.getName() %></td>
                                <td><%= idModel.getColor() %></td>
                                <td><%= r.getDescription() %></td>
                                <td><%=r.getPrix()%></td>
                                <td><a href="Login.jsp?action=update&link=./reparationform&id=<%= r.getIdReparation() %>"><button class="btn btn-outline-light" type="submit">Update</button></a></td>
                                <td><a href="Login.jsp?action=delete&link=./reparation&id=<%= r.getIdReparation() %>"><button class="btn btn-outline-light" type="submit">Delete</button></a></td>
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
