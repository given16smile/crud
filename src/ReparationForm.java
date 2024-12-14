package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vaika.*;

public class ReparationForm extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null && action.equalsIgnoreCase("update")) {
            try {
                update(req, resp);
                resp.sendRedirect("reparation");
                
            } catch (Exception e) {
                out.println("Erreur lors de la modification de la vente : " + e.getMessage());
            }   
        } 
        if (action != null && action.equalsIgnoreCase("insert")) {
            try {
                insert(req,resp);
                resp.sendRedirect("reparation");
            } catch (Exception e) {
                out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
            }   
        } 
    }
     


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            if (req.getParameter("action") != null && req.getParameter("action").equals("update")) {
                listModel(req, resp);
                listMecanicien(req, resp);
                listBodyType(req, resp);
                listBrand(req, resp);
                listEngineType(req, resp);
                listCar(req, resp);
                listGarage(req, resp);
                prerempli(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateReparation.jsp");
                dispatcher.forward(req, resp);
            
            } else {
                listModel(req, resp);
                listMecanicien(req, resp);
                listBodyType(req, resp);
                listGarage(req, resp);
                listBrand(req, resp);
                listCar(req, resp);
                prerempireparation(req, resp);
                listEngineType(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("InsertReparation.jsp");
                dispatcher.forward(req, resp);
            }
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
    protected void listVendeurs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListVendeurs", Vendeur.getAll());
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
    protected void listReparation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListReparations", Reparation.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listGarage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListGarages", Garage.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
    protected void listMecanicien(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListMecaniciens", Mecanicien.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
   

    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        
        String dateString = req.getParameter("dateDebut");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        java.sql.Date debut= new java.sql.Date(parsedDate.getTime());
        
        String dateString2 = req.getParameter("dateFin");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate2 = dateFormat2.parse(dateString);
        java.sql.Date fin= new java.sql.Date(parsedDate.getTime());

        int idMecanicien = Integer.parseInt(req.getParameter("idMecanicien"));
        int idg = Integer.parseInt(req.getParameter("idGarage"));
        String nomClient = req.getParameter("nomClient");
        int idcar = Integer.parseInt(req.getParameter("idcar"));
        String description=req.getParameter("descri");
        double prix= Integer.parseInt(req.getParameter("price"));

        Reparation rep=new Reparation(idcar, debut, fin, idcar, idg, idMecanicien, nomClient, prix, description);
        rep.save();
     }
     

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("update")) {
            String dateString = req.getParameter("dateDebut");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            java.sql.Date debut= new java.sql.Date(parsedDate.getTime());
            
            String dateString2 = req.getParameter("dateFin");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate2 = dateFormat2.parse(dateString2);
            java.sql.Date fin= new java.sql.Date(parsedDate2.getTime());

            int idrep=Integer.parseInt(req.getParameter("idrep"));
            int idMecanicien = Integer.parseInt(req.getParameter("idMecanicien"));
            int idg = Integer.parseInt(req.getParameter("idGarage"));
            String nomClient = req.getParameter("nomClient");
            int idcar = Integer.parseInt(req.getParameter("idcar"));
            String description=req.getParameter("descri");
            double prix= Double.parseDouble(req.getParameter("price"));

            Reparation r=new Reparation(idrep, debut, fin, idcar, idg, idMecanicien, nomClient, prix, description);
            r.update();

        }
    }
    
    protected void prerempli(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Reparation r=new Reparation(id, null, null, 0, 0, 0,null,0,null);
            r.getById();
            req.setAttribute("reparationForm",r);
        } catch (Exception e) {
         
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }

    protected void prerempireparation(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Car c=new Car(id,0,0);
            c.getById();
            req.setAttribute("carForm",c);
        } catch (Exception e) {
         
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }
}
