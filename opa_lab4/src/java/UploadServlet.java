// Import required java libraries
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private boolean is_png_gif_jpg;  // ??????????????????????????? SPRAWDZIĆ JAKO PLIK BĘDZIE WGRYWANY
   private String filePath;
   private final int maxFileSize = 10 * 1024 * 1024;
   private final int maxMemSize = 4 * 10 * 1024;
   private File file ;

   @Override
   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
   @Override
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
       System.out.println("REQUEST " + request);
      response.setContentType("text/html; charsert=utf-8");
      java.io.PrintWriter out = response.getWriter( );
      request.getRequestDispatcher("top.html").include(request, response);  
      request.setCharacterEncoding("UTF-8");
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;
            out.println("Uploaded Filename: " + fileName + "<br>");
            //
            // WPIS DO BAZY DANYCH
            
            Long l = sizeInBytes;
            double d = l.doubleValue();
            out.println("<br>Rozmiar "+ (double) d/1024.00 +" KB");
            out.println("<center>");
            if(insert.insert_photo_to_db(fileName, (double) d/1024.00) == true){//.doubleValue());
                    out.println("<br><br><h3>Pomyslenie dodano nowy obraz</h3><br>");
                    out.println("<a href='welcome'>");
                    out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Strona Glowna</button>");
                    out.println("</a>");
            }
            else{
                    out.println("<br><br><h3>Blad, podczas dodawania nowego obrazu</h3><br>");
                    out.println("<a href='add.html'>");
                    out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Sprobuj ponownie</button>");
                    out.println("</a>");
            }
            out.println("</center>");
            //
         }
      }
      out.println("</body>");
      out.println("</html>");
   }catch(Exception ex) {
       System.out.println(ex);
       out.println("ERROR<br>"+ ex);
       out.println("</body>");
       out.println("</html>");
   }
   }
   
   @Override
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   } 
}