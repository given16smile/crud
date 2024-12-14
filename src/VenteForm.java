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

public class VenteForm extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null && action.equalsIgnoreCase("update")) {
            try {
                update(req, resp);
                resp.sendRedirect("vente");
                
            } catch (Exception e) {
                out.println("Erreur lors de la modification de la vente : " + e.getMessage());
            }   
        } 
        if (action != null && action.equalsIgnoreCase("insert")) {
            try {
                insert(req,resp);
                resp.sendRedirect("vente");
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
                listModels(req, resp);
                listVendeurs(req, resp);
                listBodyType(req, resp);
                listBrand(req, resp);
                listEngineType(req, resp);
                listCar(req, resp);
                prerempli(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateVente.jsp");
                dispatcher.forward(req, resp);
            
            } else {
                listModels(req, resp);
                listVendeurs(req, resp);
                listBodyType(req, resp);
                listBrand(req, resp);
                listCar(req, resp);
                preremplivente(req, resp);
                listEngineType(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("InsertVente.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            out.print(e.getMessage());
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
            req.setAttribute("ListCarrosserie",BodyType.getAll());
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
    protected void listModels(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListModels", Model.getAll());
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

    protected void listVendeurs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListVendeurs", Vendeur.getAll());
        } catch (Exception e) {
            throw e;
        }
    }
   

    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        
        String dateString = req.getParameter("dateVente");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        java.sql.Date dateVente= new java.sql.Date(parsedDate.getTime());
        
        int idVendeur = Integer.parseInt(req.getParameter("idVendeur"));
        String nomClient = req.getParameter("nomClient");
        int idcar = Integer.parseInt(req.getParameter("idcar"));

        
        Vente vente = new Vente();
        vente.setDateVente(dateVente);
        vente.setIdVendeur(idVendeur);
        vente.setClient(nomClient);
        vente.setIdCar(idcar);
        vente.save();
     }
     

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (req.getParameter("action") != null && req.getParameter("action").equalsIgnoreCase("update")) {
            int idVente = Integer.parseInt(req.getParameter("idVente"));
            String dateString = req.getParameter("dateVente");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            java.sql.Date DateVente= new java.sql.Date(parsedDate.getTime());
            int idVendeur = Integer.parseInt(req.getParameter("idVendeur"));
            String nomClient = req.getParameter("nomClient");
            int idcar= Integer.parseInt(req.getParameter("idcar"));

            Vente vente = new Vente();
            vente.setIdVente(idVente);
            vente.setDateVente(DateVente);
            vente.setIdVendeur(idVendeur);
            vente.setClient(nomClient);
            vente.setIdCar(idcar);
            vente.update();
        }
    }
    
    protected void prerempli(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            int idVente = Integer.parseInt(req.getParameter("id"));
            Vente vente = new Vente();
            vente.setIdVente(idVente);
            vente.getById();
            req.setAttribute("venteForm", vente);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la pré-remplissage du formulaire : " + e.getMessage());
        }
    }

    protected void preremplivente(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
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
