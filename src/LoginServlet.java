package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vaika.*;
import user.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String link = req.getParameter("link");
        int id = Integer.parseInt(req.getParameter("id"));
        
        boolean v = verify(req, resp);
        
        if (action != null && v) {
            resp.sendRedirect(link + "?action=" + action + "&id=" + id);
        } else {
            req.setAttribute("errorMessage", "Echec de l'authentification. Veuillez verifier vos identifiants.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected boolean verify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = req.getParameter("pseudo");
        String mdp = req.getParameter("mdp");
        PrintWriter out = resp.getWriter();
        try {
            User user = new User();
            user.setpseudo(pseudo);
            user.setMdp(mdp);
            user.getById();
            if (user.getIdUser() != 0) {
                return true;
            }
        } catch (Exception e) {
            out.println("Une erreur s'est produite lors de l'authentification.");
        }
        return false;
    }
}
