package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class BodyType {
    int idBodyType;
    String name;

    public int getIdBodyType() {
        return idBodyType;
    }

    public void setIdBodyType(int idBodyType) {
        this.idBodyType = idBodyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyType() {}

    public BodyType(int id, String name) {
        setIdBodyType(id);
        setName(name);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM BodyType WHERE idbodytype = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdBodyType());
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
            state = c.prepareStatement("SELECT * FROM BodyType WHERE idbodytype = ?");
            state.setInt(1, this.getIdBodyType());
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
            String sql = "UPDATE BodyType SET name = ? WHERE idbodytype = ?";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdBodyType());
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
            String sql = "INSERT INTO BodyType (name) VALUES (?)";
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

    public static List<BodyType> getAll() throws Exception {
        List<BodyType> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM BodyType");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new BodyType(result.getInt("idbodytype"), result.getString("name")));
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
