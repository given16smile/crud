package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Brand {
    int idBrand;
    String brand;

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Brand() {}

    public Brand(int id, String name) {
        setIdBrand(id);
        setBrand(name);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "delete from Brand  where idbrand = ? ";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdBrand());
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
            state = c.prepareStatement("select * from brand where idbrand = ?");
            state.setInt(1, this.getIdBrand());
            result = state.executeQuery();
            while (result.next()) {
                this.setBrand((result.getString(2)));
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
            String sql = "update Brand set name = ? where idbrand = ? ";
            state = c.prepareStatement(sql);
            state.setString(1, this.getBrand());
            state.setInt(2, this.getIdBrand());
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
            String sql = "insert into Brand (name) values (?)";
            state = c.prepareStatement(sql);
            state.setString(1, this.getBrand());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public static List<Brand> getAll() throws Exception {
        List<Brand> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("select * from brand");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Brand(result.getInt(1), result.getString(2)));
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
