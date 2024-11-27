package com.tuturial;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateData
 */
@WebServlet("/UpdateData")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest reque, HttpServletResponse respon) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		PrintWriter out = respon.getWriter();
		String name = reque.getParameter("name");
		String telefon = reque.getParameter("telefon");
		
		
		
		try {
			// Aufbau der Verbindung zur Datenbank
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ghostnetfishing","root","");
		//	String query = "UPDATE 'ghostnet' SET status = 'melden', name, telefon WHERE status = 'bergen', name = '"+name+"', telefon = '"+telefon+"'";
		//	String query = "UPDATE 'ghostnet' SET melden = CASE status WHEN 'status' THEN bergen WHEN 'name' THEN '"+name+"' WHEN 'telefon' THEN '"+telefon+"'";
			String query = "UPDATE 'ghostnet' SET name = ?, telefon = ?";
			
			PreparedStatement pst = con.prepareStatement(query);		   

       
            pst.setString(1, name);
            pst.setString(2, telefon);


		    // Update Datenbank
			int ergebnis = pst.executeUpdate();

			if(ergebnis > 0) {
                out.print("<h3>Data updated successfully!</h3>");
            } else {
                out.print("<h3>Failed to update data!</h3>");
            }

            // Close database resources
            pst.close();
            con.close();
		    
		} catch(Exception e) {
			
			e.printStackTrace();
		}

           

	
		
		
	}

}
