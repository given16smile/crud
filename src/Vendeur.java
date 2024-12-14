package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Vendeur {
    int idVendeur;
    String name;

    public int getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(int idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendeur() {}

    public Vendeur(int id, String name) {
        setIdVendeur(id);
        setName(name);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Vendeur WHERE idvendeur = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdVendeur());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public void getById() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Vendeur WHERE idvendeur = ?");
            state.setInt(1, this.getIdVendeur());
            result = state.executeQuery();
            while (result.next()) {
                this.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public void update() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "UPDATE Vendeur SET name = ? WHERE idvendeur = ?";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdVendeur());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public void save() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "INSERT INTO Vendeur (name) VALUES (?)";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public static List<Vendeur> getAll() throws Exception {
        List<Vendeur> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Vendeur");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Vendeur(result.getInt("idvendeur"), result.getString("name")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
        return list;
    }
}
