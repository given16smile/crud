package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import vaika.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utile.*;

public class ReparationServlet extends HttpServlet{
    
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            delete(req, resp);
            listBrand(req, resp);
            listBodyType(req, resp);
            listEngineType(req, resp);
            listModel(req, resp);
            listCar(req, resp);
            listVente(req, resp);
            listVendeurs(req, resp);
            listMecanicien(req, resp);
            listGarage(req, resp);

            // Vérifier s'il y a des paramètres de recherche
            if (req.getParameter("d1") != null || req.getParameter("d2") != null || req.getParameter("garage") != null || req.getParameter("mecano") != null || req.getParameter("brand") != null || req.getParameter("model") != null) {
                search(req, resp);
            } else {
                listReparation(req, resp);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("ReparationList.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            out.print(e.getMessage());
        }        
    }

    // Autres méthodes list*() et delete() ici...

    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        // Récupérer les paramètres de la requête
        String dateMinParam = req.getParameter("d1");
        String dateMaxParam = req.getParameter("d2");
        Integer idGarage = null;
        Integer idMecanicien = null;
        Integer idBrand = null;
        Integer idModel = null;
        Date dateMin = null;
        Date dateMax = null;
        // Vérifier et extraire les paramètres de la requête
        if (req.getParameter("garage") != null && !req.getParameter("garage").isEmpty()) {
            idGarage = Integer.parseInt(req.getParameter("garage"));
        }

        if (req.getParameter("mecano") != null && !req.getParameter("mecano").isEmpty()) {
            idMecanicien = Integer.parseInt(req.getParameter("mecano"));
        }

        if (req.getParameter("brand") != null && !req.getParameter("brand").isEmpty()) {
            idBrand = Integer.parseInt(req.getParameter("brand"));
        }

        if (req.getParameter("model") != null && !req.getParameter("model").isEmpty()) {
            idModel = Integer.parseInt(req.getParameter("model"));
        }

        // Convertir les dates en objet Date
        if (dateMinParam != null && !dateMinParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateMin = new Date(sdf.parse(dateMinParam).getTime());
        }
        if (dateMaxParam != null && !dateMaxParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateMax = new Date(sdf.parse(dateMaxParam).getTime());
        }

        // Appeler la fonction search de la classe Reparation
        List<Reparation> reparations = Reparation.search(dateMin, dateMax, idGarage, idMecanicien, idBrand, idModel);

        // Définir les attributs pour la vue
        req.setAttribute("ListReparations", reparations);
    }

    protected void listVente(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            req.setAttribute("ListVente", Vente.getAll());
        } catch (Exception e) {
            throw e;
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

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception{
        if (req.getParameter("action") != null && req.getParameter("action").equals("delete")) {
            Reparation r =  new Reparation();
            r.setIdReparation(Integer.parseInt(req.getParameter("id")));
            r.delete();
        }
    }
}
