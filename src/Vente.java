package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Vente {
    int idVente;
    Date dateVente;
    int idVendeur;
    String client;
    int idCar;

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public int getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(int idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public Vente() {}

    public Vente(int id, Date dateVente, int idVendeur, String client, int idCar) {
        setIdVente(id);
        setDateVente(dateVente);
        setIdVendeur(idVendeur);
        setClient(client);
        setIdCar(idCar);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Vente WHERE idVente = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdVente());
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
            state = c.prepareStatement("SELECT * FROM Vente WHERE idVente= ?");
            state.setInt(1, this.getIdVente());
            result = state.executeQuery();
            while (result.next()) {
                this.setDateVente(result.getDate("datevente"));
                this.setIdVendeur(result.getInt("idvendeur"));
                this.setClient(result.getString("client"));
                this.setIdCar(result.getInt("idcar"));
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
            String sql = "UPDATE Vente SET datevente = ?, idvendeur = ?, client = ?, idcar = ? WHERE idVente = ?";
            state = c.prepareStatement(sql);
            state.setDate(1, new java.sql.Date(this.getDateVente().getTime()));
            state.setInt(2, this.getIdVendeur());
            state.setString(3, this.getClient());
            state.setInt(4, this.getIdCar());
            state.setInt(5, this.getIdVente());
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
            String sql = "INSERT INTO Vente (datevente, idvendeur, client, idcar) VALUES (?, ?, ?, ?)";
            state = c.prepareStatement(sql);
            state.setDate(1, new java.sql.Date(this.getDateVente().getTime()));
            state.setInt(2, this.getIdVendeur());
            state.setString(3, this.getClient());
            state.setInt(4, this.getIdCar());
            state.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
            System.out.println("Successful Closing");
        }
    }

    public static List<Vente> getAll() throws Exception {
        List<Vente> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Vente");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Vente(result.getInt("idVente"), result.getDate("datevente"), result.getInt("idvendeur"), result.getString("client"), result.getInt("idcar")));
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

    public static List<Vente> search(Integer idBrand, Integer idModel, Date dateMin, Date dateMax, Integer idVendeur) throws Exception {
        List<Vente> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = Connector.getConnection();
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Vente WHERE 1=1");
    
            if (idBrand != null) {
                queryBuilder.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel IN (SELECT idModel FROM Model WHERE idBrand = ?))");
            }
    
            if (idModel != null) {
                queryBuilder.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel = ?)");
            }
            if (dateMin != null && dateMax != null) {
                queryBuilder.append(" AND dateVente BETWEEN ? AND ?");
            }
            else{
                if (dateMin != null)  queryBuilder.append(" AND dateVente <= ? ");
                if (dateMax != null)  queryBuilder.append(" AND dateVente >= ? ");
            }
            if (idVendeur != null) {
                queryBuilder.append(" AND idVendeur = ?");
            }
    
            statement = connection.prepareStatement(queryBuilder.toString());
    
            int paramIndex = 1;
            if (idBrand != null) {
                statement.setInt(paramIndex++, idBrand);
            }
            if (idModel != null) {
                statement.setInt(paramIndex++, idModel);
            }
            if (dateMin != null) {
                statement.setDate(paramIndex++, new java.sql.Date(dateMin.getTime()));
            }
            if (dateMax!= null) {
                statement.setDate(paramIndex++, new java.sql.Date(dateMax.getTime()));
            }
            if (idVendeur != null) {
                statement.setInt(paramIndex++, idVendeur);
            }
            System.out.println(statement);
            resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Vente vente = new Vente();
                vente.setIdVente(resultSet.getInt(1));
                vente.setDateVente(resultSet.getDate(2));
                vente.setIdVendeur(resultSet.getInt(3));
                vente.setClient(resultSet.getString(4));
                vente.setIdCar(resultSet.getInt(5));
                resultList.add(vente);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    
        return resultList;
    }
    

}
