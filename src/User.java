package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utile.Connector;
import vaika.Brand;

public class User {
    int idUser;
    String pseudo;
    String mdp;
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getpseudo() {
        return pseudo;
    }
    public void setpseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public User(){}

    public User(int id,String pseudo,String mdp){
        setIdUser(id);
        setpseudo(pseudo);
        setMdp(mdp);
    }

    public User(String pseudo,String mdp){
        setpseudo(pseudo);
        setMdp(mdp);
    }

    public void getById () throws Exception{
        Connection c= null; PreparedStatement state = null; ResultSet result = null;
        try {
            c=Connector.getConnection();
            state = c.prepareStatement("select * from users where pseudo = ? and pwd = ?");
            state.setString(1, this.getpseudo());
            state.setString(2,this.getMdp());
            result = state.executeQuery();
            while (result.next() ) {
                this.setIdUser((result.getInt(1)));
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close();System.out.println("Successful Closing");
        }
    }

    public void save () throws Exception{
        Connection c= null; PreparedStatement state = null; 
        try {
            c=Connector.getConnection();
            String sql = "insert into Users (pseudo,pwd) values (?,?)";
            state = c.prepareStatement(sql);
            state.setString(1, this.getpseudo());
            state.setString(2, this.getMdp());
            state.executeUpdate();            
        } catch (Exception e) {
            throw e;
        }
        finally{
            if(state != null) state.close();
            if(c != null) c.close();System.out.println("Successful Closing");
        }
    }

    public void delete () throws Exception{
        Connection c=null; 
        PreparedStatement state = null; 
        try {
            c=Connector.getConnection();
            String sql = "delete from Users where iduser = ? ";
            state = c.prepareStatement(sql);
            state.setInt(1, this.getIdUser());
            state.executeUpdate();            
        } catch (Exception e) {
            throw e;
        }
        finally{
            if(state != null) state.close();
            if(c != null) c.close(); System.out.println("Successful Closing");
        }
    }

    public void update () throws Exception {
        Connection c= null; PreparedStatement state = null; 
        try {
            c=Connector.getConnection();
            String sql = "update Users set pseudo = ? , mdp=? where iduser = ? ";
            state = c.prepareStatement(sql);
            state.setString(1, this.getpseudo());
            state.setString(2, this.getMdp());
            state.executeUpdate();            
        } catch (Exception e) {
            throw e;
        }
        finally{
            if(state != null) state.close();
            if(c != null) c.close();System.out.println("Successful Closing");
        }
    }

     public static List<User> getall () throws Exception{
        List<User> list = new ArrayList<>();
        Connection c= null; PreparedStatement state = null; ResultSet result = null;
        try {
            c=Connector.getConnection();
            state = c.prepareStatement("select * from users");
            result = state.executeQuery();
            while (result.next() ) {
                list.add(new User(result.getString(1),result.getString(2)));
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            if (result != null) result.close();
            if (state != null) state.close();
            if (c != null) c.close(); System.out.println("Successful Closing");
        }
        return list;
    }

}
