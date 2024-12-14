package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Garage {
    int idGarage;
    String name;

    public int getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Garage() {}

    public Garage(int id, String name) {
        setIdGarage(id);
        setName(name);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Garage WHERE idGarage = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdGarage());
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
            state = c.prepareStatement("SELECT * FROM Garage WHERE idGarage = ?");
            state.setInt(1, this.getIdGarage());
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
            String sql = "UPDATE Garage SET name = ? WHERE idGarage = ?";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdGarage());
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
            String sql = "INSERT INTO Garage (name) VALUES (?)";
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

    public static List<Garage> getAll() throws Exception {
        List<Garage> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Garage");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Garage(result.getInt("idGarage"), result.getString("name")));
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
