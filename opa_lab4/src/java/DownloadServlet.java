import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piotr
 */
public class DownloadServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
                try{
		request.getRequestDispatcher("top.html").include(request, response);  
                request.setCharacterEncoding("UTF-8");
                
		String file_url = request.getParameter("file_url");
                out.println("file_url: "+file_url+"<br>");
                String uri = file_url;
                String extension = uri.substring(uri.lastIndexOf(".") + 1);
                String extesion1 = uri.substring(uri.indexOf("."),uri.lastIndexOf("."));
                String extension2 = extesion1.substring(extesion1.lastIndexOf("/") + 1);
                String extesion3 = extension2.substring(extension2.lastIndexOf(".")+1);
//                out.println("<br>extension: "+extension+"<br>");
//                out.println("<br>extension2: "+extension2+"<br>");
//                out.println("<br>extesion1: "+extesion1+"<br>");
//                out.println("<br>extesion3: "+extesion3+"<br>");
//                out.println("<br>extension: "+extension.equals("gif")+"<br>");
//                out.println("<br>gif: "+extension.equals("gif")+"<br>");
//                out.println("<br>png: "+extension.equals("png")+"<br>");
//                out.println("<br>jpg: "+extension.equals("jpg")+"<br>");
                if(("gif".equals(extension)==true) || ("png".equals(extension)==true) || ("jpg".equals(extension)==true) ||
                        ("GIF".equals(extension)==true) || ("PNG".equals(extension)==true) || ("JPG".equals(extension)==true)){ // Plik nie zawiera zdjęcia, nie jest zdjęciem
                    out.println("<br><h3>Pod podanym linkiem znajduje się plik graficzny w formacie [png lub gif lub jpg]</h3><br>");
//                out.println("<h2>Let's start!</h2><br>");
                out.println("<center>");
                if(save_photo_controler(out, file_url, extension2, extension) == true){ // Pobranie OK --> zapis do bazy danych
                                    out.println("Pobrano plik na dysk<br>");    
                                //insert
                                String file_name = extension2+"."+ extension;
                                Double rozmiar_from_net = update.Rozmiar(file_name);

                                        if(insert.insert_photo_to_db(file_name, rozmiar_from_net) !=true){
                                            out.println("<br><h2>Blad podczas wpisu do bazy danych</h2><br>");
                                            out.println("<br>Podany plik prawdopodobnie już istnieje<br>");
                                            out.println("<a href='add.html'>");
                                            out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Sprobuj ponownie</button>");
                                            out.println("</a>");
                                        }
                                        else{
                                            out.println("<br><br><h1>Pobranie i wstawienie pliku zakończone sukcesem :) </h1><br>");
                                            //out.println("<br><br><h3>Pomyslenie dodano nowy obraz</h3><br>");
                                            out.println("<a href='welcome'>");
                                            out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Strona Glowna</button>");
                                            out.println("</a>");
                                        }
                    
                }else{ // Pobranie ERROR --> ponów próbę
                            if(save_photo_controler(out, file_url, extension2, extension) != true){ // 2 próba ERROR
                                out.println("<br><h2>Zapis obrazu na dysk zakończony niepowodzeniem :(</h2><br>"); 
                                out.println("<a href='add.html'>");
                                out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Sprobuj ponownie</button>");
                                out.println("</a>");
                            }else{ // Za 2-gim razem OK
                                out.println("Pobrano plik na dysk<br>");    
                                //insert
                                String file_name = extension2+"."+ extension;
                                Double rozmiar_from_net = update.Rozmiar(file_name);

                                        if(insert.insert_photo_to_db(file_name, rozmiar_from_net) !=true){
                                            out.println("<br><h2>Blad podczas wpisu do bazy danych</h2><br>");
                                            out.println("<br>Podany plik prawdopodobnie już istnieje<br>");
                                            out.println("<a href='add.html'>");
                                            out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Sprobuj ponownie</button>");
                                            out.println("</a>");
                                        }
                                        else{
                                            out.println("<br><br><h1>Pobranie i wstawienie pliku zakończone sukcesem :) </h1><br>");
                                            //out.println("<br><br><h3>Pomyslenie dodano nowy obraz</h3><br>");
                                            out.println("<a href='welcome'>");
                                            out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Strona Glowna</button>");
                                            out.println("</a>");
                                        }
                                }
                }
                //NEXT STEP
                out.println("</center>");
                }else{
                //ERROR
                out.println("<center>");
                out.println("<br><h3>Niepoprawny format pliku, wybierz inny plik w formacje [png, gif, jpg]</h3><br>");
                out.println("<a href='add.html'>");
                out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Jeszcze raz</button>");
                out.println("</a>");   
                out.println("</center>");
                }
                
                }catch(StringIndexOutOfBoundsException ex){
                    out.println("<center>");
                    out.println("<br>Błąd servletu<br><b>Prawdopodobnie nie wybrano żadnego pliku</b><br>Spróbuj ponownie<br><br>");
                    
                   // out.println("<br><h3>Niepoprawny format pliku, wybierz inny plik w formacje [png, gif, jpg]</h3><br>");
                    out.println("<a href='add.html'>");
                    out.println("<button type=\"button\" style=\"height:50px; width:200px\" >Dodaj zdjęcie</button>");
                    out.println("</a>");   
                    out.println("</center>");
                    System.out.println("ERROR [2]");
                }
                
                out.println("</body>");
                out.println("</html>");
    }
    
    //********************************-----------------------------------------
    
    public static boolean save_photo_controler(PrintWriter out1, String file_url_link,String file_name_without_url, String rozszerzenie) throws MalformedURLException, IOException {
        boolean is_save = false;
        
        try{
            URL url = new URL(file_url_link);

            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
               out.write(buf, 0, n);
            }
            out.close();
            in.close();

            String lokalizacja = select.file_path_folder+"temp_"+file_name_without_url+"."+rozszerzenie;//C:\\MetroPhoto\\MetroPhoto_kamera1_7.jpg";
            System.out.println("lokalizacja " + lokalizacja);
            File file = new File(lokalizacja);

            if (!file.exists()) {
                                            file.createNewFile();
                                    }
            byte[] response = out.toByteArray();
            System.out.println("rsfgdfgddfgdg");
            //        Thread.sleep(22);
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(response);
            fos.close();


                            File oldfile =new File(lokalizacja);
                            File newfile =new File(select.file_path_folder+file_name_without_url+"."+rozszerzenie);
                            System.out.println(select.file_path_folder+file_name_without_url+"."+rozszerzenie);
                            try{
                            // Rename file (or directory)
                            boolean success = oldfile.renameTo(newfile);

                            if(!success){
                                    System.out.println("Rename succesful");
                                    is_save = true;
                            }else{
                                    System.out.println("Rename failed");
                                    is_save = false;
                                    if (newfile.exists()) {
                                            System.out.println("Znany błąd, ale wgrano poprawnie /////////////////////////////////////////");
                                          //  out1.println("<br><center>Znany błąd, ponawiam próbę");
                                           // out1.println("</center><br>");
                                    }
                            }
                            }catch(Exception e)
                            {
                                System.out.println("das " +e);
                                is_save = false;
                            }
        }catch(IOException ec){
            out1.println("<br>Podany link do obrazu nie jest prawidłowy, pod podanym linkiem nic nie istnieje :( <br>");
            System.out.println("das " +ec);
        }

        
        return is_save;
    }
    
    
    
    
    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[1024];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}   
    
}
