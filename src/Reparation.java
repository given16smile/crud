package vaika;

import utile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Reparation {
    int idReparation;
    Date dateDebutReparation;
    Date dateFinReparation;
    int idCar;
    int idGarage;
    int idMecanicien;
    double prix;
    String client;
    String description; 

    public int getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }

    public Date getDateDebutReparation() {
        return dateDebutReparation;
    }

    public void setDateDebutReparation(Date dateDebutReparation) {
        this.dateDebutReparation = dateDebutReparation;
    }

    public Date getDateFinReparation() {
        return dateFinReparation;
    }

    public void setDateFinReparation(Date dateFinReparation) {
        this.dateFinReparation = dateFinReparation;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public int getIdMecanicien() {
        return idMecanicien;
    }

    public void setIdMecanicien(int idMecanicien) {
        this.idMecanicien = idMecanicien;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reparation() {}

    public Reparation(int idReparation, Date dateDebutReparation, Date dateFinReparation, int idCar, int idGarage, int idMecanicien, String client, double prix, String description) {
        setIdReparation(idReparation);
        setDateDebutReparation(dateDebutReparation);
        setDateFinReparation(dateFinReparation);
        setIdCar(idCar);
        setIdGarage(idGarage);
        setIdMecanicien(idMecanicien);
        setClient(client);
        setPrix(prix);
        setDescription(description);
    }

    public void delete() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "DELETE FROM Reparation WHERE idReparation = ?";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdReparation());
            state.executeUpdate();
            System.out.println("Successful Closing");
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
        }
    }

    public void getById() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Reparation WHERE idReparation = ?");
            state.setInt(1, this.getIdReparation());
            result = state.executeQuery();
            while (result.next()) {
                this.setDateDebutReparation(result.getDate("dateDebutReparation"));
                this.setDateFinReparation(result.getDate("dateFinReparation"));
                this.setIdCar(result.getInt("idCar"));
                this.setIdGarage(result.getInt("idGarage"));
                this.setIdMecanicien(result.getInt("idMecanicien"));
                this.setClient(result.getString("client"));
                this.setPrix(result.getDouble("prix"));
                this.setDescription(result.getString("description")); 
            }
            System.out.println("Successful Closing");
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close();
        }
    }

    public void update() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "UPDATE Reparation SET dateDebutReparation = ?, dateFinReparation = ?, idCar = ?, idGarage = ?, idMecanicien = ?, client = ?, prix = ?, description = ? WHERE idReparation = ?";
            state = c.prepareStatement(sql);
            state.setDate(1, new java.sql.Date(this.getDateDebutReparation().getTime()));
            state.setDate(2, new java.sql.Date(this.getDateFinReparation().getTime()));
            state.setInt(3, this.getIdCar());
            state.setInt(4, this.getIdGarage());
            state.setInt(5, this.getIdMecanicien());
            state.setString(6, this.getClient());
            state.setDouble(7, this.getPrix());
            state.setString(8, this.getDescription()); 
            state.setInt(9, this.getIdReparation());
            state.executeUpdate();
            System.out.println("Successful Closing");
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
        }
    }

    public void save() throws Exception {
        Connection c = null;
        PreparedStatement state = null;
        try {
            c = Connector.getConnection();
            String sql = "INSERT INTO Reparation (dateDebutReparation, dateFinReparation, idCar, idGarage, idMecanicien, client, prix, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Modification de la requête pour inclure la description
            state = c.prepareStatement(sql);
            state.setDate(1, new java.sql.Date(this.getDateDebutReparation().getTime()));
            state.setDate(2, new java.sql.Date(this.getDateFinReparation().getTime()));
            state.setInt(3, this.getIdCar());
            state.setInt(4, this.getIdGarage());
            state.setInt(5, this.getIdMecanicien());
            state.setString(6, this.getClient());
            state.setDouble(7, this.getPrix());
            state.setString(8, this.getDescription());
            state.executeUpdate();
            System.out.println("Successful Closing");
        } catch (Exception e) {
            throw e;
        } finally {
            if (state != null) state.close();
            if (c != null) c.close();
        }
    }

    public static List<Reparation> getAll() throws Exception {
        List<Reparation> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            c = Connector.getConnection();
            state = c.prepareStatement("SELECT * FROM Reparation");
            result = state.executeQuery();
            while (result.next()) {
                list.add(new Reparation(result.getInt("idReparation"), result.getDate("dateDebutReparation"), result.getDate("dateFinReparation"), result.getInt("idCar"), result.getInt("idGarage"), result.getInt("idMecanicien"), result.getString("client"), result.getDouble("prix"), result.getString("description"))); // Ajout de la récupération de la description
            }
            System.out.println("Successful Closing");
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close();
        }
        return list;
    }
    public static List<Reparation> search(Date dateMin, Date dateMax, Integer idGarage, Integer idMecanicien, Integer idBrand, Integer idModel) throws Exception {
        List<Reparation> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = Connector.getConnection();
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Reparation WHERE 1=1");
    
            if (dateMin != null && dateMax != null) {
                queryBuilder.append(" AND dateDebutReparation BETWEEN ? AND ?");
            } else {
                if (dateMin != null) queryBuilder.append(" AND dateDebutReparation >= ?");
                if (dateMax != null) queryBuilder.append(" AND dateDebutReparation <= ?");
            }
    
            if (idGarage != null) {
                queryBuilder.append(" AND idGarage = ?");
            }
    
            if (idMecanicien != null) {
                queryBuilder.append(" AND idMecanicien = ?");
            }
    
            if (idBrand != null) {
                queryBuilder.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel IN (SELECT idModel FROM Model WHERE idBrand = ?))");
            }
    
            if (idModel != null) {
                queryBuilder.append(" AND idCar IN (SELECT idCar FROM Car WHERE idModel = ?)");
            }
    
            statement = connection.prepareStatement(queryBuilder.toString());
    
            int paramIndex = 1;
            if (dateMin != null) {
                statement.setDate(paramIndex++, new java.sql.Date(dateMin.getTime()));
            }
            if (dateMax != null) {
                statement.setDate(paramIndex++, new java.sql.Date(dateMax.getTime()));
            }
            if (idGarage != null) {
                statement.setInt(paramIndex++, idGarage);
            }
            if (idMecanicien != null) {
                statement.setInt(paramIndex++, idMecanicien);
            }
            if (idBrand != null) {
                statement.setInt(paramIndex++, idBrand);
            }
            if (idModel != null) {
                statement.setInt(paramIndex++, idModel);
            }
    
            resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Reparation reparation = new Reparation();
                reparation.setIdReparation(resultSet.getInt("idReparation"));
                reparation.setDateDebutReparation(resultSet.getDate("dateDebutReparation"));
                reparation.setDateFinReparation(resultSet.getDate("dateFinReparation"));
                reparation.setIdCar(resultSet.getInt("idCar"));
                reparation.setIdGarage(resultSet.getInt("idGarage"));
                reparation.setIdMecanicien(resultSet.getInt("idMecanicien"));
                reparation.setClient(resultSet.getString("client"));
                reparation.setPrix(resultSet.getDouble("prix"));
                reparation.setDescription(resultSet.getString("description"));
                resultList.add(reparation);
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
