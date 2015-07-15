import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;


/**
 *
 * @author Piotr
 */
public class update {
    
    /**
     * Aktualizaowanie informacji o obrazie w bazie danych
     * @param id_value
     * @param Autor
     * @param Opis
     * @param url_name
     * @param data_utworzenia
     * @return true jeśli update przebieg pomyślnie
     */
    public static boolean update_modyfied_value(String id_value, String Autor, String Opis, String url_name, String data_utworzenia){
        boolean is_success_update = false;
        double rozmiar = 0;
        String Wymiary = null;
        java.util.Date date= new java.util.Date();
        System.out.println(new Timestamp(date.getTime()));
        
        try{
            rozmiar = Rozmiar(url_name);
            Wymiary = Wymiary(url_name);

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
            String connectionURL = "jdbc:derby://localhost:"+select.port+"/"+select.db_name;
             //ConnectionURL, username and password should be specified in getConnection()
             try {
                    Connection conn = DriverManager.getConnection(connectionURL, select.login, select.pass);
                    String sql = "UPDATE URL SET autor='"+Autor+"', opis='"+Opis+"', wymiary='"+Wymiary+"', data_modyfikacji='"+new Timestamp(date.getTime())+"', data_utworzenia='"+data_utworzenia+"' WHERE ID="+id_value+"";
                    Statement st = conn.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    conn.close();
                    is_success_update = true;
                    
             } catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ ex);
                    ex.printStackTrace();
                    is_success_update = false;
             }
        
        
        return is_success_update;
    }
   
    
        /**
         * Pobiera wymiary obrazka
         * @param url_name
         * @return
         * @throws IOException 
         */
        public static String Wymiary(String url_name) throws IOException{
        String wymiar_image = null;
        //    System.out.println(select.file_path_folder+rs.getString("url"));
         BufferedImage bimg = ImageIO.read(new File(select.file_path_folder+url_name));
         //BufferedImage bimg = ImageIO.read(new File("./../images/"+rs.getString("name")));
       
         int width          = bimg.getWidth();
        int height         = bimg.getHeight();
            System.out.println("UPDATE: width " + width + " height " +height );
            wymiar_image = width+"x"+height;
                    
        return wymiar_image;
        
    }
    
        
       /**
        * Pobiera Rozmiar obrazka
        * @param url_name
        * @return 
        * @throws java.io.IOException 
        */
       public static Double Rozmiar(String url_name) throws IOException{
        
            double bytes = 0;
            File imageFile = new File(select.file_path_folder+url_name);
            bytes = imageFile.length();
            System.out.println("UPDATE: File Size: " + String.format("%.2f", bytes/1024) + "kb");

        return bytes/1024;
    }
}
