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

public class VenteServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            delete(req,resp);
            listBrand(req,resp);
            listBodyType(req,resp);
            listEngineType(req,resp);
            listModel(req, resp);
            listCar(req, resp);
            listVente(req, resp);
            listVendeurs(req,resp);

            if (req.getParameter("d1") != null || req.getParameter("d2") != null || req.getParameter("idCar") != null || req.getParameter("idVendeur") != null ||  req.getParameter("brand") != null || req.getParameter("model") != null) {
                search(req, resp);
            } else {
                listVente(req, resp);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("VenteList.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            out.print(e.getMessage());
                
        }        
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

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception{
        if (req.getParameter("action") != null && req.getParameter("action").equals("delete")) {
            Vente v =  new Vente();
            v.setIdVente(Integer.parseInt(req.getParameter("id")));
            v.delete();
        }
    }
    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        // Récupérer les paramètres de la requête
        Integer idCar = null;
        Date dateMin = null;
        Date dateMax = null;
        Integer idVendeur = null;
        Integer idModel=null;
        Integer idBrand = null;
        if (req.getParameter("brand") != null && !req.getParameter("brand").isEmpty()) {
            idBrand = Integer.parseInt(req.getParameter("brand"));
        }

        if (req.getParameter("model") != null && !req.getParameter("model").isEmpty()) {
            idModel = Integer.parseInt(req.getParameter("model"));
        }
        String dateMinParam = req.getParameter("d1");
        String dateMaxParam = req.getParameter("d2");
        if (dateMinParam != null && !dateMinParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateMin = new Date(sdf.parse(dateMinParam).getTime());
        }
        if (dateMaxParam != null && !dateMaxParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateMax = new Date(sdf.parse(dateMaxParam).getTime());
        }
    
        String idVendeurParam = req.getParameter("idVendeur");
        if (idVendeurParam != null && !idVendeurParam.isEmpty()) {
            idVendeur = Integer.parseInt(idVendeurParam);
        }

        if (idBrand != null || idModel != null || dateMax != null || dateMin != null || idVendeur != null) {
            req.setAttribute("ListVente",  Vente.search(idBrand,idModel,dateMin, dateMax, idVendeur));
        } else {
            req.setAttribute("ListVente", new ArrayList<Vente>()); 
        }
    RequestDispatcher dispatcher = req.getRequestDispatcher("VenteList.jsp");
        dispatcher.forward(req, resp);
    }
    
    
}
