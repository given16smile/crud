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
            <div class="col-2 shadow-lg p-3 mb-5">
                <form action="model" method="get">
                    Brand:
                    <br>
                    <select name="brand">
                        <option></option>
                        <% for(Brand b : (List<Brand>)request.getAttribute("ListBrand")) { %>
                            <option value="<%= b.getIdBrand() %>"><%= b.getBrand() %></option>
                        <% } %>
                    </select>
                    <br>
                    Body Type
                    <br>
                    <select name="bodytype">
                        <option></option>
                        <% for(BodyType bt: (List<BodyType>)request.getAttribute("ListCarrosserie")) { %>
                            <option value="<%= bt.getIdBodyType() %>"><%= bt.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                    Engin Type
                    <br>
                    <select name="engintype">
                        <option></option>
                        <% for(EnginType et: (List<EnginType>)request.getAttribute("ListMoteur")) { %>
                            <option value="<%= et.getIdEnginType() %>"><%= et.getName() %></option>
                        <% } %>
                    </select>
                    <br>
                    <br>
                    <button class="btn btn-outline-light" type="submit">Valider</button>
                </form>
            </div>
          <div class="col-8">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Body Type</th>
                            <th>Engine Type</th>
                            <th>Color</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Model m : (List<Model>)request.getAttribute("ListModel")) {
                            Brand brand = null;
                            BodyType bodyType = null;
                            EnginType enginType = null;
                            for(Brand b : (List<Brand>)request.getAttribute("ListBrand")) {
                                if(b.getIdBrand() == m.getIdBrand()) {
                                    brand = b;
                                    break;
                                }
                            }
                            for(BodyType bt : (List<BodyType>)request.getAttribute("ListCarrosserie")) {
                                if(bt.getIdBodyType() == m.getIdBodyType()) {
                                    bodyType = bt;
                                    break;
                                }
                            }
                            for(EnginType et : (List<EnginType>)request.getAttribute("ListMoteur")) {
                                if(et.getIdEnginType() == m.getIdEnginType()) {
                                    enginType = et;
                                    break;
                                }
                            }
                            if(brand != null && bodyType != null && enginType != null) {
                        %>
                        <tr>
                            <td><%= brand.getBrand() %></td>
                            <td><%= m.getName() %></td>
                            <td><%= bodyType.getName() %></td>
                            <td><%= enginType.getName() %></td>
                            <td><%= m.getColor() %></td>
                            <td><a href="Login.jsp?action=update&link=./modelform&id=<%= m.getIdModel() %>"><button class="btn btn-outline-light" type="submit">UPDATE</button></a></td>
                            <td><a href="Login.jsp?action=delete&link=./model&id=<%= m.getIdModel() %>"><button class="btn btn-outline-light" type="submit">DELETE</button></a></td>
                            <td><a href="Login.jsp?action=insert&link=./carform&id=<%= m.getIdModel() %>"><button class="btn btn-outline-light" type="submit">STOCK</button></a></td>
                        </tr>
                        <% }
                            } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="WEB-INF/web/Footer.jsp" %>
</body>
</html>
