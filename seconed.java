import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class seconed extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException	
	{ 
		 response.setContentType("text/html");
		 PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("	<head>");
		out.println("		<title>Petoogle</title>");
		out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">");
		out.println("		<link href=\"https://fonts.googleapis.com/css?family=Akronim\" rel=\"stylesheet\">");
		out.println("<link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">");
	    out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/materialize.min.css\"  media=\"screen,projection\"/>");
		out.println("	      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
		out.println("	</head>");
		out.println("	<body>");
		out.println("	<nav>");
		out.println("    <div class=\"nav-wrapper\">");
		out.println("      <a href=\"file:///C:/Users/Sarthak%20Birla/Desktop/WebProj/index.html\" class=\"brand-logo\">Petoogle</a>");
		out.println("      <ul id=\"nav-mobile\" class=\"right hide-on-med-and-down\">");
		out.println("       <li><a href=\"file:///C:/Users/Sarthak%20Birla/Desktop/WebProj/contact_us.html\">Contact US</a></li>");
		out.println("        <li><a href=\"file:///C:/Users/Sarthak%20Birla/Desktop/WebProj/Upload.html\">Contribute</a></li>");
		out.println("      </ul>");
		out.println("    </div>");
		out.println("  </nav>");
		out.println("  <nav>");
		out.println("    <div class=\"nav-wrapper\">");
		out.println("      <form method=\"get\" action=\"http://localhost:8080/Search/seconed\">");
		out.println("        <div class=\"input-field\">");
		out.println("          <input id=\"search\" name=\"search\" type=\"search\" required>");
		out.println("          <label class=\"label-icon\" for=\"search\"><i class=\"material-icons\">search</i></label>");
		out.println("          <i class=\"material-icons\">close</i>");
		out.println("        </div>");
		out.println("      </form>");
		out.println("    </div>");
		out.println("  </nav>");
		try
		{	
				String search=request.getParameter("search");
				Class.forName("com.mysql.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webtech", "root", "kitty1537");
				 Statement stmt = conn.createStatement();
         		 String sql;
         		 sql = "SELECT id,name,space,living,fandw,vaccination,cost_of_each,special FROM Petoogle where name LIKE '%"+search+"%'";
         		 ResultSet rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
				 	 int id  = rs.getInt("id");
	            	 String name = rs.getString("name");
					 out.println("<div class=\"col s12 m7\">");
				     out.println("<div class=\"card horizontal\">");
				     out.println("	<div class=\"card-image\">");
				     out.println("	<img src=\"http://lorempixel.com/100/190/nature/6\">");
				     out.println("</div>");
				     out.println("		<div class=\"card-stacked\">");
				     out.println("<div class=\"card-content\">");
				     out.println(name);
				     out.println("</div>");
				     out.println("<div class=\"card-action\">");
				     out.println("	<a href=\"http://localhost:8080/Display/database?id="+id+"\">Know More</a>");
				     out.println("   		</div>");
				  	out.println("    		</div>");
				    out.println("	</div>");
				  	out.println("</div>");
				 }
				 out.println("<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>");
  				out.println("<script type=\"text/javascript\" src=\"js/materialize.min.j\"></script>");
 				 out.println("</body>");
  				out.println("</html>");
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
	 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException	
	{
		doPost(request,response);
	} 
  }