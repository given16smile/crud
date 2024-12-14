package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Car {
    int idCar;
    int idModel;
    double prix;

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Car() {}

    public Car(int id, int idModel, double prix) {
        setIdCar(id);
        setIdModel(idModel);
        setPrix(prix);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Car WHERE idcar= ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdCar());
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
            state = c.prepareStatement("SELECT * FROM Car WHERE idcar = ?");
            state.setInt(1, this.getIdCar());
            result = state.executeQuery();
            while (result.next()) {
                this.setIdModel(result.getInt("idmodel"));
                this.setPrix(result.getDouble("prix"));
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
            String sql = "UPDATE Car SET idmodel = ?, prix = ? WHERE idcar = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdModel());
            state.setDouble(2, this.getPrix());
            state.setInt(3, this.getIdCar());
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
            String sql = "INSERT INTO Car (idmodel, prix) VALUES (?, ?)";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdModel());
            state.setDouble(2, this.getPrix());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public static List<Car> getAll() throws Exception {
        List<Car> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Car");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Car(result.getInt("idcar"), result.getInt("idmodel"), result.getDouble("prix")));
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


    public static List<Car> find(Double min, Double max, Integer idModel,Integer idBrand) throws Exception {
        List<Car> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
    
        try {
            c = Connector.getConnection();
            StringBuilder sql = new StringBuilder("SELECT * FROM Car WHERE 1=1 ");
            if (min != null && max != null) {
                sql.append("AND prix BETWEEN ? AND ? ");
            } else {
                if (min != null)  sql.append("AND prix <= ? ");
                if (max != null)  sql.append("AND prix >= ? ");
            }

            if (idBrand != null) {
                sql.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel IN (SELECT idModel FROM Model WHERE idBrand = ?))");
            }

            if (idModel != null) {
                sql.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel = ?)");
            }

            
            
            System.out.println(sql.toString());
    
            state = c.prepareStatement(sql.toString());

            int n = 1;
    
            if (min != null) {
                state.setDouble(n++, min);
            }
            if (max != null) {
                state.setDouble(n++, max);
            }
            if (idBrand != null) {
                state.setInt(n++, idBrand);
            }
            if (idModel != null) {
                state.setInt(n++, idModel);
            }
           
            result = state.executeQuery();
                while (result.next()) {
                    list.add(new Car(result.getInt("idcar"), result.getInt("idmodel"), 
                            result.getDouble("prix")));
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
