import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Piotr
 */
public class select {
    
    public static final String file_path_folder="C:\\Users\\Piotr\\Dropbox\\opa_lab4_galeria\\opa_lab4\\web\\images\\"; // TRZEBA UZUPEŁNIĆ SAMEMU, w razie zmiany lokalizacji projektu, lub pliku z danymi
    public static final String login = "asd";
    public static final String pass = "asd";
    public static final String port = "1527";
    public static final String db_name = "asd";
     
    public select() {
    }
    
       
       /**
        * Wyświetlanie pojedyńczego zdjęcia i jego atrybutów [z bazy danych]
        * @param id_value id obrazu, aby pobrać odpowiednie wartości z bazy danych
        * @param out PrintWriter, aby drukować na ekranie
        */
    public static void view_one_photo(String id_value, PrintWriter out){
             String connectionURL = "jdbc:derby://localhost:"+port+"/"+db_name;


                try {
                     Connection conn = DriverManager.getConnection(connectionURL, login, pass);
                     String sql = "SELECT * FROM url where id="+id_value+"";
                     Statement st = conn.createStatement();
                     ResultSet rs=null;
                     rs=st.executeQuery(sql);
                     out.println("<center>");


                                 while(rs.next()){
                                        //out.println("<br>NIE MA TAKIEGO OBRAZU W BAZIE DANYCH<br>" + rs.isFirst() );
                                         System.out.println(rs.getInt("id")+"\t"+rs.getString("url")+rs.getString("autor")+rs.getString("opis")+rs.getDouble("rozmiar")+rs.getString("wymiary")+rs.getTimestamp("data_dodania"));
                                           // context.getServletContext(); 
                                           // context.setAttribute("company",rs.getInt("id")); 
                                         //out.println("<img src=\"images/"+rs.getString("name")+"\" alt=\""+rs.getString("name")+"\" >");
                                        // out.println("<a href='vsp?id="+rs.getInt("id")+"'>");out.print("<input type=\'image\' name=\'imgButton\' src=\'images/"+rs.getString("name")+"\'/>");
                                         out.println("<img id=\""+rs.getInt("id")+"\" src=\"images/"+rs.getString("url")+"\" alt=\""+rs.getString("url")+"\" >");
                                         java.text.DecimalFormat df=new java.text.DecimalFormat();
                                     df.setMaximumFractionDigits(2);
                                     df.setMinimumFractionDigits(2);
                                     if(              ((rs.getString("autor")) !=null)==true){
                                     out.println("<br><b>Autor:</b> " + rs.getString("autor"));}
                                     if(              ((rs.getString("opis")) !=null)==true){
                                     out.println("<br><b>Opis:</b> " + rs.getString("opis"));}
                                     if((rs.getDouble("rozmiar") == 0)!=true){
                                         out.println("<br><b>Rozmiar:</b> " + df.format(rs.getDouble("rozmiar"))+" KB");}
                                     if(              (rs.getString("wymiary") == null)!=true){
                                         out.println("<br><b>Wymiary:</b> " + rs.getString("wymiary"));}
                                     if(              ((rs.getString("data_utworzenia")) !=null)==true){
                                     out.println("<br><b>Data utworzenia pliku:</b> " + rs.getString("data_utworzenia"));}
                                     out.println("<br><br><b>Data dodania:</b> " + rs.getTimestamp("data_dodania"));
                                     if(              (rs.getTimestamp("data_modyfikacji") == null)!=true){
                                     out.println("<br><b>Data ostatniej modyfikacji:</b> " + rs.getTimestamp("data_modyfikacji"));
                                     }else{out.println("<br><b>Data ostatniej modyfikacji:</b> " + "brak aktualizacji");}
                                     
                                     out.println("<br><br><h2><b>Kliknij, aby edytować:</b><br>");
                                     out.println("<a href='pev?id="+rs.getInt("id")+"'>");
                                     out.println("<button type=\"button\" style=\"height:50px; width:100px\" >Edytuj</button>");
                                     out.println("</a></h2>");


                                 }
                     out.println("</center>");
                     rs.close();
                     st.close();
                     conn.close();
         } catch (SQLException ex) {
         out.println("<br>Connect failed ! " +ex);
         System.out.println("Connect failed ! " +ex);
         }   

    }

    
    
    /**
     *  Formularz edycji atrybutów
     * @param id_value id zdjęcia
     * @param out aby wyświetlać tekst
     */
    public static void view_photo_to_edit(String id_value, PrintWriter out){
        
        String connectionURL = "jdbc:derby://localhost:"+port+"/"+db_name;
 
        try {
             Connection conn = DriverManager.getConnection(connectionURL, login, pass);
             String sql = "SELECT * FROM url where id="+id_value+"";
             Statement st = conn.createStatement();
             ResultSet rs=null;
             rs=st.executeQuery(sql);
             out.println("<center>");
 
 
                 while(rs.next()){
                                 out.println("<img id=\""+rs.getInt("id")+"\" src=\"images/"+rs.getString("url")+"\" alt=\""+rs.getString("url")+"\" width=\"400\" height=\"300\">");

                //         java.text.DecimalFormat df=new java.text.DecimalFormat();
                //                 df.setMaximumFractionDigits(2);
                //                 df.setMinimumFractionDigits(2);
                //                 if(              ((rs.getString("autor")) !=null)==true){
                //                     out.println("<br><b>Autor:</b> " + rs.getString("autor"));}
                //                 if(              ((rs.getString("opis")) !=null)==true){
                //                 out.println("<br><b>Opis:</b> " + rs.getString("opis"));}
                //                 if((rs.getDouble("rozmiar") == 0)!=true){
                //                     out.println("<br><b>Rozmiar:</b> " + df.format(rs.getDouble("rozmiar"))+" KB");}
                //                 if(              (rs.getString("wymiary") == null)!=true){
                //                     out.println("<br><b>Wymiary:</b> " + rs.getString("wymiary"));}
                //                 out.println("<br><b>Data dodania:</b> " + rs.getTimestamp("data_dodania"));
                //                 if(              (rs.getTimestamp("data_modyfikacji") == null)!=true){
                //                 out.println("<br><b>Data ostatniej modyfikacji:</b> " + rs.getTimestamp("data_modyfikacji"));
                //                 }else{out.println("<br><b>Data ostatniej modyfikacji:</b> " + "brak aktualizacji");}
                                 out.println("<script>function validateForm(){var x = document.forms[\"myForm\"][\"autor\"].value;if (x==null || x==\"\" || x==\"null\") {alert(\"Pole autor musi być uzupełnione!\");return false;}}</script>");
                                 //out.println("<script type=\"text/javascript\"> function checkdate(input){ var validformat=/^\\d{2}\\/\\d{2}\\/\\d{4}$/ //Basic check for format validity var returnval=false if (!validformat.test(input.value)) alert(\"Invalid Date Format. Please correct and submit again.\") else{ //Detailed check for valid date ranges var monthfield=input.value.split(\"/\")[0] var dayfield=input.value.split(\"/\")[1] var yearfield=input.value.split(\"/\")[2] var dayobj = new Date(yearfield, monthfield-1, dayfield) if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) alert(\"Invalid Day, Month, or Year range detected. Please correct and submit again.\") else returnval=true}if (returnval==false) input.select()return returnval}</script>");
                                 
                                 out.println("<br><br><h2>Uzupełnij opis zdjęcia</h2></b>");
                                 out.println("<form name=\"myForm\" action=\"uiv\"  onsubmit=\" return checkdate(this.mydate)\" method=\"post\" accept-charset=\"ISO-8859-2\" >   ");
                                 out.println("<input type=\"hidden\" name=\"id\" value=\""+rs.getInt("id")+"\"/>");
                                 out.println("<input type=\"hidden\" name=\"url\" value=\""+rs.getString("url")+"\"/>");
                                 out.println("Autor: <input type=\"text\" name=\"autor\" value=\""+rs.getString("autor")+"\" maxlength=\"29\"><br><br>");
                                 out.println("Data utworzenia pliku: <input type=\"text\" name=\"mydate\" value=\""+rs.getString("data_utworzenia")+"\" style=\"height:15px; width:80px\"> (w formacje [dd/mm/rrrr] )<br><br>");
                                 out.println("Dodaj opis do zdjęcia (opcjonalnie):<br><textarea name=\"opis\" rows=\"4\" cols=\"50\" maxlength=\"600\" >"+rs.getString("opis")+"</textarea><br>");
                                 out.println("<br><input type=\"submit\" id=\"1\" value=\"Wprowadź opis do bazy danych\" onclick=\"return validateForm()\" style=\"height:40px; width:250px\">");
                                 out.println("</form><br><br>");

                 }

                  out.println("</center>");
                 rs.close();
                 st.close();
                 conn.close();
             } catch (SQLException ex) {
             System.out.println("Connect failed ! " +ex);
             }   
      
    }
    
    
    /**
     * Wyświetla wszystkie dostępne zdjęcia
     * @param out
     * @throws IOException 
     */
    public static void select_derby(PrintWriter out) throws IOException {
        
    String connectionURL = "jdbc:derby://localhost:"+port+"/"+db_name;
    int width = 300;
    int height = 200;
    
        try {
                 Connection conn = DriverManager.getConnection(connectionURL, login, pass);
                 String sql = "SELECT * FROM url";
                 Statement st = conn.createStatement();
                 ResultSet rs = null;
                 rs=st.executeQuery(sql);
                 out.println("<center>");
                 int licz = 2;
                 while(rs.next()){

                         out.println("<a href='vsp?id="+rs.getInt("id")+"'>");
                         out.println("<img id=\""+rs.getInt("id")+"\" src=\"images/"+rs.getString("url")+"\" alt=\""+rs.getString("url")+"\" width=\""+width+"\" height=\""+height+"\">");
                         out.println("</a>");

                         if(licz==0){
                         out.println("<br>");
                         licz=2;
                         }else{licz--;}

                 }
                 out.println("</center>");
                 rs.close();
                 st.close();
                 conn.close();
             } catch (SQLException ex) {
                 out.println("<br>Connect failed ! " + ex);
             System.out.println("Connect failed ! " + ex);
             }
      
    }
}
