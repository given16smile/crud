package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import vaika.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utile.*;

public class CarServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            delete(req,resp);
            listBrand(req,resp);
            listBodyType(req,resp);
            listEngineType(req,resp);
            listModel(req, resp);
    
            if (req.getParameter("model") != null || req.getParameter("brand") != null || req.getParameter("p1") != null || req.getParameter("p2") != null) {
                search(req, resp);
            } else {
                // No search parameters, list all cars
                listCar(req, resp);
            }
    
            RequestDispatcher dispatcher = req.getRequestDispatcher("CarList.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            out.print(e.getMessage());
        }        
    }
    


    protected void listModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListModel",Model.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listBrand(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListBrand", Brand.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listBodyType(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListCarrosserie", BodyType.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listEngineType(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListMoteur", EnginType.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listCar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListCar", Car.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception{
        if (req.getParameter("action") != null && req.getParameter("action").equals("delete")) {
            Car m =  new Car();
            m.setIdCar(Integer.parseInt(req.getParameter("id")));
            m.delete();
        }
    }
    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            // Récupérer les paramètres de la requête
            Integer idModel=null;
            Integer idBrand = null;
            Double min = null;
            Double max = null;
    
            if (req.getParameter("brand") != null && !req.getParameter("brand").isEmpty()) {
                idBrand = Integer.parseInt(req.getParameter("brand"));
            }
    
            if (req.getParameter("model") != null && !req.getParameter("model").isEmpty()) {
                idModel = Integer.parseInt(req.getParameter("model"));
            }
    
            String minParam = req.getParameter("p1");
            if (minParam != null && !minParam.isEmpty()) {
                min = Double.parseDouble(minParam);
            }
    
            String maxParam = req.getParameter("p2");
            if (maxParam != null && !maxParam.isEmpty()) {
                max = Double.parseDouble(maxParam);
            }
    
            if (idModel != null || idBrand!=null ||  min != null || max != null) {
                req.setAttribute("ListCar", Car.find(min, max, idModel,idBrand));
            } else {
                req.setAttribute("ListCar", new ArrayList<Car>()); 
            }
        } catch (Exception e) {
            throw new ServletException("Error during search: " + e.getMessage());
        }
    }
    

}
