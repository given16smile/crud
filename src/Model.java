package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Model {
    int idModel;
    String name;
    int idBrand;
    int idEnginType;
    int idBodyType;
    String color;

    public Model(int idModel) {
        this.idModel = idModel;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public int getIdEnginType() {
        return idEnginType;
    }

    public void setIdEnginType(int idEnginType) {
        this.idEnginType = idEnginType;
    }

    public int getIdBodyType() {
        return idBodyType;
    }

    public void setIdBodyType(int idBodyType) {
        this.idBodyType = idBodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Model() {}

    public Model(int id, String name, int brand, int enginType, int bodyType, String color) {
        setIdModel(id);
        setName(name);
        setIdBrand(brand);
        setIdEnginType(enginType);
        setIdBodyType(bodyType);
        setColor(color);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Model WHERE idmodel = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdModel());
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
            state = c.prepareStatement("SELECT * FROM Model WHERE idmodel = ?");
            state.setInt(1, this.getIdModel());
            result = state.executeQuery();
            while (result.next()) {
                this.setName(result.getString("name"));
                this.setIdBrand(result.getInt("idbrand"));
                this.setIdEnginType(result.getInt("idengintype"));
                this.setIdBodyType(result.getInt("idbodytype"));
                this.setColor(result.getString("color"));
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
            String sql = "UPDATE Model SET name = ?, idbrand = ?, idengintype = ?, idbodytype = ?, color = ? WHERE idmodel = ?";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdBrand());
            state.setInt(3, this.getIdEnginType());
            state.setInt(4, this.getIdBodyType());
            state.setString(5, this.getColor());
            state.setInt(6, this.getIdModel());
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
            String sql = "INSERT INTO Model (name, idbrand, idengintype, idbodytype, color) VALUES (?, ?, ?, ?, ?)";
            state = c.prepareStatement(sql);
            state.setString(1, this.getName());
            state.setInt(2, this.getIdBrand());
            state.setInt(3, this.getIdEnginType());
            state.setInt(4, this.getIdBodyType());
            state.setString(5, this.getColor());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public static List<Model> getAll() throws Exception {
        List<Model> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Model");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Model(result.getInt("idmodel"), result.getString("name"), result.getInt("idbrand"), result.getInt("idengintype"), result.getInt("idbodytype"), result.getString("color")));
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
    public static List<Model> search(Integer idBrand, Integer idBodyType, Integer idEngineType) throws Exception {
        List<Model> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Connector.getConnection();
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Model WHERE 1=1");

            if (idBrand != null) {
                queryBuilder.append(" AND idBrand = ?");
            }
            if (idBodyType != null) {
                queryBuilder.append(" AND idBodyType = ?");
            }
            if (idEngineType != null) {
                queryBuilder.append(" AND idEnginType = ?");
            }

            statement = connection.prepareStatement(queryBuilder.toString());

            int paramIndex = 1;
            if (idBrand != null) {
                statement.setInt(paramIndex++, idBrand);
            }
            if (idBodyType != null) {
                statement.setInt(paramIndex++, idBodyType);
            }
            if (idEngineType != null) {
                statement.setInt(paramIndex, idEngineType);
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Model model = new Model();
                model.setIdModel(resultSet.getInt("idModel"));
                model.setName(resultSet.getString("name"));
                model.setIdBrand(resultSet.getInt("idBrand"));
                model.setIdEnginType(resultSet.getInt("idEnginType"));
                model.setIdBodyType(resultSet.getInt("idBodyType"));
                model.setColor(resultSet.getString("color"));
                resultList.add(model);
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la recherche de modèle par critères: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Successful Closing");

        }

        return resultList;
    }
}
