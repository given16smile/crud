<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vaika.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Sales Website</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/accueil.css">
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="WEB-INF/web/menu.jsp" %>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-2 shadow-lg p-3 mb-5 bg-dark text-white" id="mynavbar">
                <form action="car" method="get">
                    <input type="hidden" name="action" value="search">
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
                    Enter price between:
                    <br>
                    <input type="number" name="p1">
                    <br>
                    and
                    <input type="number" name="p2">
                    <br>
                    <br>
                <td><a href="action=search&link=./carform"><button class="btn btn-outline-light" type="submit">SEARCH</button></a></td>
                </form>
            </div>
          <div class="col-6">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Brand</th>
                            <th><a href="model">Model</th></a>
                            <th>Body Type</th>
                            <th>Engine Type</th>
                            <th>Color</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  for(Car c : (List<Car>)request.getAttribute("ListCar")) {
                            Brand brand = null;
                            BodyType bodyType = null;
                            EnginType enginType = null;
                            Model model=null;
                            for(Model m : (List<Model>)request.getAttribute("ListModel")) {
                                 if(c.getIdModel() == m.getIdModel()) {
                                    model = m;
                                    break;
                                }
                            }
                             for(Brand b : (List<Brand>)request.getAttribute("ListBrand")) {
                                if(b.getIdBrand() == model.getIdBrand()) {
                                    brand = b;
                                    break;
                                }
                            }
                            for(BodyType bt : (List<BodyType>)request.getAttribute("ListCarrosserie")) {
                                if(bt.getIdBodyType() == model.getIdBodyType()) {
                                    bodyType = bt;
                                    break;
                                }
                            }
                            for(EnginType et : (List<EnginType>)request.getAttribute("ListMoteur")) {
                                if(et.getIdEnginType() == model.getIdEnginType()) {
                                    enginType = et;
                                    break;
                                }
                            }
                        
   
                            if(brand != null && bodyType != null && enginType != null && model!=null) {
                        %>
                        <tr>
                            <td><%= brand.getBrand() %></td>
                            <td><%= model.getName() %></td>
                            <td><%= bodyType.getName() %></td>
                            <td><%= enginType.getName() %></td>
                            <td><%= model.getColor() %></td>
                            <td><%= c.getPrix() %></td>
                            <td><a href="Login.jsp?action=insert&link=./venteform&id=<%= c.getIdCar() %>"><button class="btn btn-outline-light" type="submit">VENDRE</button></a></td>
                            <td><a href="Login.jsp?action=insert&link=./reparationform&id=<%= c.getIdCar() %>"><button class="btn btn-outline-light" type="submit">REPARE</button></a></td>
                            <td><a href="Login.jsp?action=update&link=./carform&id=<%= c.getIdCar() %>"><button class="btn btn-outline-light" type="submit">UPDATE</button></a></td>
                            <td><a href="Login.jsp?action=delete&link=./car&id=<%= c.getIdCar() %>"><button class="btn btn-outline-light" type="submit">DELETE</button></a></td>
                        </tr>
                        <% }
                         }
                         %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="WEB-INF/web/Footer.jsp" %>
</body>
</html>
