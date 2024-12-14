package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vaika.*;

public class ModelForm extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null && action.equalsIgnoreCase("update")) {
            try {
                update(req, resp);
                resp.sendRedirect("model");
            } catch (Exception e) {
                out.println("Erreur lors de la modification du modèle : " + e.getMessage());
            }   
        } 
        if (action != null && action.equalsIgnoreCase("insert")) {
            try {
                String modelName = req.getParameter("model");
                String color = req.getParameter("color");
                int brandId = Integer.parseInt(req.getParameter("brand"));
                int bodyTypeId = Integer.parseInt(req.getParameter("carrosserie"));
                int engineTypeId = Integer.parseInt(req.getParameter("motorisation"));
              

                insert(modelName, color, brandId, bodyTypeId, engineTypeId);
                resp.sendRedirect("model");
            } catch (Exception e) {
                out.println("Erreur lors de l'ajout du modèle : " + e.getMessage());
            }   
        } 
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            if (req.getParameter("action") != null && req.getParameter("action").equals("update")) {
                listBrand(req, resp);
                listBodyType(req, resp);
                listEngineType(req, resp);
                prerempli(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateModel.jsp");
                dispatcher.forward(req, resp);
            } else {
                listBrand(req, resp);
                listBodyType(req, resp);
                listEngineType(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("InsertModel.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            out.print(e.getMessage());
        }  
    }

    protected void listBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            req.setAttribute("ListBrand", Brand.getAll());
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des marques : " + e.getMessage());
        }
    }

    protected void listBodyType(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            req.setAttribute("ListCarrosserie", BodyType.getAll());
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des types de carrosserie : " + e.getMessage());
        }
    }

    protected void listEngineType(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            req.setAttribute("ListMoteur", EnginType.getAll());
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des types de moteur : " + e.getMessage());
        }
    }

    protected void insert(String modelName, String color, int brandId, int bodyTypeId, int engineTypeId) throws ServletException {
       try {
            Model m = new Model();
            m.setIdBrand(brandId);
            m.setName(modelName);
            m.setColor(color);
            m.setIdBodyType(bodyTypeId);
            m.setIdEnginType(engineTypeId);
            m.save();
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'insertion du modèle : " + e.getMessage());
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("idmodel"));
            int brandId = Integer.parseInt(req.getParameter("idbrand"));
            int engineTypeId = Integer.parseInt(req.getParameter("idmoto"));
            int bodyTypeId = Integer.parseInt(req.getParameter("idcar"));
            String modelName = req.getParameter("model");
            String color= req.getParameter("color");

            Model m = new Model();
            m.setIdModel(id);
            m.setIdBrand(brandId);
            m.setIdEnginType(engineTypeId);
            m.setIdBodyType(bodyTypeId);
            m.setName(modelName);
            m.setColor(color);
            m.update();
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la mise à jour du modèle : " + e.getMessage());
        }
    }

    protected void prerempli(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Model m = new Model(id, null, 0, 0, 0,null);
            m.getById();
            req.setAttribute("modelForm", m);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }

    protected void search (HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            String brand=req.getParameter("brand");
            String model=req.getParameter("model");
            double p1=Integer.parseInt(req.getParameter("p1"));
            double p2=Integer.parseInt(req.getParameter("p2"));

        } catch (Exception e) {

        }
    }
}
