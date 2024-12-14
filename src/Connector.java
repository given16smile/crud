package utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    static Connection connection;
    static String url = "jdbc:mysql://localhost:3306/Cars";
    static String username = "root";
    static String password = "";
    static String pilote = "com.mysql.cj.jdbc.Driver";

    public Connector() {
    }

    public static Connection getConnection() throws Exception {
        try {
            Class.forName(getPilote());
            setConnection( DriverManager.getConnection(getUrl(), getUsername(), getPassword()));            
            System.out.println("Successful Opening");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Erreur : Pilote JDBC introuvable.");
            throw e;
        } 
        catch (SQLException e) {
            System.out.println("Erreur : Impossible de se connecter à la base de données.");
            throw e;
        }
        return connection;
    }
    public static void setConnection(Connection connection) {
       Connector.connection = connection;
    }
    public static String getUrl() {
        return url;
    }
    public static void setUrl(String url) {
       Connector.url = url;
    }
    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
       Connector.username = username;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
       Connector.password = password;
    }
    public static String getPilote() {
        return pilote;
    }
    public static void setPilote(String pilote) {
       Connector.pilote = pilote;
    }
 }

