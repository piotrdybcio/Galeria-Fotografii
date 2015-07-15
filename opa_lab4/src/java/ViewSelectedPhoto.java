import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet obsługujący wyswietlanie obrazu i jego informacji
 * @author Piotr
 */
public class ViewSelectedPhoto extends HttpServlet {
    
            @Override
    	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

            	response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
                request.getRequestDispatcher("top.html").include(request, response);  
                request.getParameter("id");

                    String params = request.getParameter("id");
                    select.view_one_photo(params, out);
                    out.println("</body>");
                out.println("</html>");
                out.close();  
                //request.getRequestDispatcher("footer.html");

	}
    
}
