
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Piotr
 */
public class insert {
    
    public static boolean insert_photo_to_db(String file_name, Double rozmiar){// String Wymiary){
        boolean is_success_insert = false;
        
                 String connectionURL = "jdbc:derby://localhost:"+select.port+"/"+select.db_name;
                 //ConnectionURL, username and password should be specified in getConnection()
                 try {
                 Connection conn = DriverManager.getConnection(connectionURL, select.login, select.pass);
                 //insert INTO URLS (ID,URL) VALUES (1,'obrazy-na-plotnie[5].jpg') 
                 //String sql = "INSERT INTO DEMO (ID, NAME) VALUES (3, 'demo3')";
                 String sql = "INSERT INTO URL (URL, ROZMIAR) VALUES ('"+file_name+"',"+rozmiar+")";// , '"+Wymiary+"')";
                 Statement st = conn.createStatement();
                 st.executeUpdate(sql);
                 st.close();
                 conn.close();
                 is_success_insert = true;
                 } catch (SQLException ex) {
                 System.out.println("Connect failed ! " + ex);
                 is_success_insert = false;
                 }
        return is_success_insert;
    }
    
    public static void main(String[] args) {
 
    }
}
