
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Servlet obsługujący panel edycji atrybutów obrazu [z bazy danych]
 * @author Piotr
 */
public class PhotoEditValue extends HttpServlet{
    
                @Override
    	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

            	response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
                request.getRequestDispatcher("top.html").include(request, response);  
                request.getParameter("id");
 
                        String params = request.getParameter("id");
                        select.view_photo_to_edit(params, out);
                out.println("</body>");
                out.println("</html>");        
                out.close();  

	}
    
}
