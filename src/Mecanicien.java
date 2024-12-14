package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Mecanicien {
    int idMecanicien;
    String name;

    public int getIdMecanicien() {
        return idMecanicien;
    }

    public void setIdMecanicien(int idMecanicien) {
        this.idMecanicien = idMecanicien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mecanicien() {}

    public Mecanicien(int id, String name) {
        setIdMecanicien(id);
        setName(name);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Mecanicien WHERE idMecanicien = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdMecanicien());
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
            state = c.prepareStatement("SELECT * FROM Mecanicien WHERE idMecanicien = ?");
            state.setInt(1, this.getIdMecanicien());
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
            String sql = "UPDATE Mecanicien SET name = ? WHERE idMecanicien = ?";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdMecanicien());
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
            String sql = "INSERT INTO Mecanicien (name) VALUES (?)";
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

    public static List<Mecanicien> getAll() throws Exception {
        List<Mecanicien> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Mecanicien");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Mecanicien(result.getInt("idMecanicien"), result.getString("name")));
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
