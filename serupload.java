import java.io.*;
import java.util.*;
 import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class serupload extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath="D:";
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;

   public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,IOException 
   {
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      PrintWriter out = response.getWriter( );
      if( !isMultipart )
	  {
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
      factory.setRepository(new File("C:\\temp"));
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
				if( fileName.lastIndexOf("\\") >= 0 )
				{
				   file = new File( filePath + 
				   fileName.substring( fileName.lastIndexOf("\\"))) ;
				}
				else
				{
				   file = new File( filePath + 
				   fileName.substring(fileName.lastIndexOf("\\")+1)) ;
				}
				  fi.write( file ) ;
				  out.println("Uploaded Filename: " + fileName + "<br>");
				  String name = request.getParameter("name");
				  String space = request.getParameter("space");
				  String living = request.getParameter("living");
				  String fandw = request.getParameter("fandw");
				  String vaccination = request.getParameter("vaccination");
				  String cost_of_each = request.getParameter("cost_of_each");
				  String special= request.getParameter("special");
				  Class.forName("com.mysql.jdbc.Driver");
				  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webtech","root","kitty1537");
				  Statement stmt = conn.createStatement();
				  String sql="INSERT INTO Petoogle(name,space,living,fandw,vaccination,cost_of_each,special,imagepath) VALUES("+ name+","+space+","+living+","+fandw+","+vaccination+","+cost_of_each+","+special+","+fileName+")";
				  int i=stmt.executeUpdate(sql);   		 
				  if(i>0)
				  {
					  out.println("The Record Submitted Successfully");
				  }
				  else
				  {
					  out.println("The Record not Submitted Successfully");
				  }
				  out.println("</body>");
				  out.println("</html>");	
			}
			}
		}
		catch(Exception ex){
				out.println(ex);
		}
   }
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        
        throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
   } 
}