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

public class ModelServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            delete(req,resp);
            listBrand(req,resp);
            listBodyType(req,resp);
            listEngineType(req,resp);
            listModel(req, resp);
    
            if (req.getParameter("brand") != null|| req.getParameter("bodytype") != null || req.getParameter("engintype") != null) {
                search(req, resp);
            } else {
                listModel(req, resp);
            }
    
            RequestDispatcher dispatcher = req.getRequestDispatcher("ModelList.jsp");
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

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception{
        if (req.getParameter("action") != null && req.getParameter("action").equals("delete")) {
            Model m =  new Model();
            m.setIdModel(Integer.parseInt(req.getParameter("id")));
            m.delete();
        }
    }

    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Récupérer les paramètres de la requête
            Integer brand = null;
            Integer bodyType = null;
            Integer engineType = null;
    
            String brandParam = req.getParameter("brand");
            if (brandParam != null && !brandParam.isEmpty()) {
                brand = Integer.parseInt(brandParam);
            }
    
            String bodyTypeParam = req.getParameter("bodytype");
            if (bodyTypeParam != null && !bodyTypeParam.isEmpty()) {
                bodyType = Integer.parseInt(bodyTypeParam);
            }
    
            String engineTypeParam = req.getParameter("engintype");
            if (engineTypeParam != null && !engineTypeParam.isEmpty()) {
                engineType = Integer.parseInt(engineTypeParam);
            }
    
            // Vérifier si au moins un critère est différent de null
            if (brand != null || bodyType != null || engineType != null) {
                // Au moins un critère est présent, procéder à la recherche
                req.setAttribute("ListModel", Model.search(brand, bodyType, engineType));
            } else {
                // Aucun critère n'est présent, ne pas effectuer la recherche
                req.setAttribute("ListModel", new ArrayList<Model>()); // Envoyer une liste vide
            }
        } catch (Exception e) {
            throw new ServletException("Error during search: " + e.getMessage());
        }
    }
    
}
