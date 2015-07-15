import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WelcomeServlet extends HttpServlet {

        @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("top.html").include(request, response);  
                request.setCharacterEncoding("UTF-8");
                out.println("<center><b>Strona Główna</b><center>");
                out.println("<hr><hr>");

                select.select_derby(out);//, request, response); // select.java Pobieranie obrazów z bazy danych
                out.println("</body>");
                out.println("</html>");
	}

}
