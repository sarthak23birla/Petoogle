import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class database extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException	
	{ 
		 response.setContentType("text/html");
		 PrintWriter out =response.getWriter();
		 out.println("<!DOCTYPE html>");
		 out.println("<html>");
		 out.println("<head>");
		 out.println("<title>Petoogle</title>");
		 out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">");
	 	 out.println("<link href=\"https://fonts.googleapis.com/css?family=Akronim\" rel=\"stylesheet\">");
    	 out.println("<link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">");
      	 out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/materialize.min.css\"  media=\"screen,projection\"/>");
         out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
		 out.println("</head>");
		 out.println("<body class= \"center\">");
		 out.println("<div class=\"parallax-container\">");
		 out.println("<div class=\"parallax\"><img src=\"images/golden.png\"></div>");
		 out.println("</div>");
		 out.println("<div class=\"section white\">");
		 out.println("<div class=\"row container\">");
		 try{
        	    
        	     Class.forName("com.mysql.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webtech", "root", "kitty1537");
				 Statement stmt = conn.createStatement();
         		 String sql;
         		 String id = request.getParameter("id");
         		 sql = "SELECT id, name,space,living,fandw,vaccination,cost_of_each,special FROM Petoogle where id="+id;
         		 ResultSet rs = stmt.executeQuery(sql);
         		 while(rs.next())
         		 {
	            	 String name = rs.getString("name");
	            	 String space = rs.getString("space");
	            	 String living = rs.getString("living");
	            	 String fandw = rs.getString("fandw");
	            	 String vaccination = rs.getString("vaccination");
	            	 String cost_of_each = rs.getString("cost_of_each");
	            	 String special= rs.getString("special");
	                out.println( "<h1 class=\"header\">"+name+"</h1>");
					out.println("<h4 class=\"header\">Living Enviroment Requirements</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+living+"</p>");
					out.println("</div>");
					out.println("<div class=\"row container\">");
					out.println("<h4 class=\"header\">Space Requirements</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+space+"</p>");
					out.println("		</div>");
					out.println("<div class=\"row container\">");
					out.println("<h4 class=\"header\">Food And Water Requirements</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+fandw+"</p>");
					out.println("</div>");
					out.println("<div class=\"row container\">");
					out.println("<h4 class=\"header\">Vacination methods and Medical Care</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+vaccination+"</p>");
					out.println("</div>");
					out.println("<div class=\"row container\">");
					out.println("<h4 class=\"header\">Cost Of Each</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+cost_of_each+"</p>");
					out.println("</div>");
					out.println("<div class=\"row container\">");
					out.println("<h4 class=\"header\">Special Requirements</h2>");
					out.println("<p class=\"grey-text text-darken-3 lighten-3\">"+special+" </p>");
					out.println("</div>");
					out.println("</div>");
					out.println("<div class=\"parallax-container\">");
					out.println("<div class=\"parallax\"><img src=\"images/main.png\"></div>");
					out.println("</div>");  
	  				out.println("<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>");
      				out.println("<script type=\"text/javascript\" src=\"js/materialize.min.js\"></script>");
  					out.println("<script>");
     				out.println("$(document).ready(function(){");
      				out.println("$('.parallax').parallax();");
    				out.println("});");
					out.println("</script>");
					out.println("</body>");
					out.println("</html>");      
         		 }
      		}
      	catch(SQLException se)
      	{
         se.printStackTrace();
     	}
      	catch(Exception e)
      	{
         e.printStackTrace();
      	} 
   }
} 