import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Piotr
 */
public class UpdaterImageValue extends HttpServlet{
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
                
		request.getRequestDispatcher("top.html").include(request, response);  
                request.setCharacterEncoding("UTF-8");
                
		String autor = request.getParameter("autor");
                String opis = request.getParameter("opis");
                String id = request.getParameter("id");
                String url = request.getParameter("url");
                String mydate = request.getParameter("mydate");
                out.println("ID zdjęcia: "+id+"<br>");
                out.println("Nazwa (URL): "+url+"<br>");
                out.println("Autor modyfikacji: "+autor+"<br>");
                out.println("Wprowadzony opis: "+opis+"<br>");
                out.println("Data utworzenia pliku: "+mydate+"<br>");
                
                System.out.println("WYBRANY PLIK: "+ opis);

                //--------------------------------------------------------------
                // UPDATE
                //--------------------------------------------------------------
                out.println("<center>");
                if(update.update_modyfied_value(id, autor, opis, url, mydate)==true){
                    out.println("<h3>Pomyślenie dodano dane do tabeli o obrazie</h3><br>");
                    out.println("<a href='vsp?id="+id+"'>");
                    out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Zobacz zmiany</button>");
                    out.println("</a>");
                    
                }else{
                    out.println("<h3>Błąd, podczas wprowadznia danych o obrazie do bazy danych</h3><br>");
                    out.println("<a href='pev?id="+id+"'>");
                    out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Ponów próbę</button>");
                    out.println("</a>");
                }
                out.println("&nbsp;&nbsp;");
                out.println("<a href='welcome'>");
                out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Strona główna</button>");
                out.println("</a>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
                //--------------------------------------------------------------
                
    }
    
}
