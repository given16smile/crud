package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vaika.*;

public class CarForm extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null && action.equalsIgnoreCase("update")) {
            try {
                update(req, resp);
                resp.sendRedirect("car");
            } catch (Exception e) {
                out.println("Erreur lors de la modification de la voiture : " + e.getMessage());
            }   
        } 
        if (action != null && action.equalsIgnoreCase("insert")) {
            try {
                insert(req,resp);
                resp.sendRedirect("car");
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
                listModel(req,resp);
                prerempli(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateCar.jsp");
                dispatcher.forward(req, resp);
            } else {
                listBrand(req, resp);
                listBodyType(req, resp);
                listEngineType(req, resp);
                preremplimodel(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("InsertCar.jsp");
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
    protected void listModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            req.setAttribute("ListModel", Model.getAll());
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des types de moteur : " + e.getMessage());
        }
    }
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
       try {
            int idmodel= Integer.parseInt(req.getParameter("model"));
            double prix=Double.parseDouble(req.getParameter("price"));

            Car c=new Car();
            c.setIdModel(idmodel);
            c.setPrix(prix);
            c.save();
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'insertion du modèle : " + e.getMessage());
        }
    }


    
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("idcar"));
            int idmodel= Integer.parseInt(req.getParameter("idmodel"));
            double prix=Double.parseDouble(req.getParameter("price"));
            Car c=new Car();
            c.setIdCar(id);
            c.setIdModel(idmodel);
            c.setPrix(prix);
            c.update();
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la mise à jour de la voiture : " + e.getMessage());
        }
    }
    protected void prerempli(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Car c = new Car(id,0,0);
            c.getById();
            req.setAttribute("carForm", c);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }

    protected void preremplimodel(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Model m = new Model(id, null, 0, 0, 0,null);
            m.getById();
            req.setAttribute("modelForm", m);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }
   
}
