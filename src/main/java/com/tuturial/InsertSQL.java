package com.tuturial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertSQL
 */
@WebServlet("/InsertSQL")
public class InsertSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSQL() {
        super();
        // TODO Auto-generated constructor stub
    }
/**    
    public static void printResultSet(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for(int i=1; i<=cols; i++)
            System.out.print(rsmd.getColumnLabel(i)+"\t");

        System.out.println("\n-------------------------------");

        while(rs.next())
        {
            // eine zeile ausgeben
            for(int i=1; i<=cols; i++)
                System.out.print(rs.getString(i)+"\t");

         
        }
    }*/
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String datum = request.getParameter("datum");
		String groesse = request.getParameter("groesse");
		String koordinaten = request.getParameter("koordinaten");
		String netzName = request.getParameter("netzName");
		String status = request.getParameter("status");
		String name = request.getParameter("name");
		String telefon = request.getParameter("telefon");


		int i = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghostnetfishing","root","");
			Statement ps = con.createStatement();
			String stmt = "INSERT INTO ghostnet (datum,groesse,koordinaten,netzName,status,name,telefon) VALUES('"+datum+"','"+groesse+"','"+koordinaten+"','"+netzName+"','"+status+"','"+name+"','"+telefon+"')";
		   
			// Abfrage Datenbank
			//String query = "SELECT * FROM ghostfisher";
			//ResultSet rs = ps.executeQuery(query);
			
		

	/**		// Ausgabe der Namen
	        while (rs.next()) {
	           //String vorname = rs.getString("Name");
	           //out.print(vorname + "<br>");
	        	out.println(
	        				rs.getString(1) + " " + 
	        	            rs.getString(2) + " " + 
	        	            rs.getString(3) + " " + 
	        	            rs.getString(4) + " " + 
	        	            rs.getString(5) + " " + 
	        	            rs.getString(6) + " " + 
	        	            rs.getString(7) + "<br>");
	            }
	        
	  */

		    // Update Datenbank
			i = ps.executeUpdate(stmt);
  
		    
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		if(i > 0) {
			                                                
			if(status.equals("melden")) {
				response.sendRedirect( "gemeldet.jsp");
			}else if(status.equals("geborgen")) {
				response.sendRedirect( "geborgen.jsp");
			}else if(status.equals("verschollen")) {
				response.sendRedirect( "verschollen.jsp");
			}else if(status.equals("bergen")) {
				response.sendRedirect( "bergung.jsp");
			}else {
				out.print("du musst selecten");
			}

			
		}else {
			out.print("Leider hat der Eintrag in die Datenbank nicht funktioniert! <a href='geisternetze.jsp'> Gehe zurück!</a>"); 
		
		}


}}
