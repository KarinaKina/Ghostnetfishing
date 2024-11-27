package com.tuturial;

 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class GhostDB
 */
@WebServlet("/GhostDB")
public class GhostDB extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {

					
	} // end doGet

		

		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		

		
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String netzName = req.getParameter("netzName");
				String groesse = req.getParameter("groesse");
				String koordinaten = req.getParameter("koordinaten");
				String datum = req.getParameter("datum");
				String status = req.getParameter("status");


			    int i = 0;			      

			    
			    
				try {

			      Class.forName("com.mysql.cj.jdbc.Driver");

			      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghostnetfishing", "root", "1234");

			      Statement ps = con.createStatement();	
				  String stmt = "INSERT INTO ghostnet (datum,groesse,koordinaten,netzName,status) VALUES('"+datum+"','"+groesse+"','"+koordinaten+"','"+netzName+"','"+status+"')";


			      String query = ("SELECT * FROM ghostnet");
				  ResultSet rs = ps.executeQuery(query);
				  


				  out.println("<html>");
			      out.println("<body>");
			      out.println("<table>");
			      out.println("<thead>"
			      		+ "<tr>"
			      		+ "<th>Nr.</th>"
			      		+ "<th>Datum</th>"
			      		+ "<th>Größe</th>"
			      		+ "<th>Koordinaten</th>"
			      		+ "<th>netzName</th>"
			      		+ "<th>Status</th>"
			      		+ "</tr>"
			      		+ "</thead>");
			      
			      while(rs.next()) {

			        out.println("<tr>"
			        + "<td>" + rs.getString("id")
			        + "</td><td>" + rs.getString("datum")		        		
			        + "</td><td>" + rs.getString("groesse") 
			        + "</td><td>" + rs.getString("koordinaten")
			        + "</td><td>" + rs.getString("netzName")
			        + "</td><td>" + rs.getString("status") 
			        + "</td></tr><br>");

			      }
			      
					i = ps.executeUpdate(stmt);


			      out.println("</tbody>");		      
			      

			      out.println("<form action='ghostDB' method='post'>");
			      out.println("<input type='text' name='datum'/>"
			      		+ "<input type='text' name='groesse'/>"
			      		+ "<input type='text' name='koordinaten'/>"
			      		+ "<input type='text' name='netzName'/>"
			      		+ "<input type='text' name='status'/>"
			    		+ "<input type='submit'/>"
			      		+ "</form>");		      
			      
				  
			      out.println("</body></html>");
			      



			    } // end try

			    catch(ClassNotFoundException e) {

			      out.println("Kein Treiber geladen " + e.getMessage());

			    }

			    catch(SQLException e) {

			      out.println("SQLException " + e.getMessage());

			    }



				if(i > 0) {
					out.print("<br>insert data");
				}else {
					out.print("not insert data");
				
				}
		
		
		
		
		

	} // end doPost

 
} // end ghostDB

