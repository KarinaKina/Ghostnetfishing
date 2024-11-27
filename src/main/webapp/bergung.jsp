<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<%@ page import="com.mysql.cj.jdbc.Driver" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<!-- Verbindungsaufbau zur Datenbank -->
	<sql:setDataSource 	var="db" 
						driver="com.mysql.cj.jdbc.Driver"
						url="jdbc:mysql://localhost:3306/ghostnetfishing" 
						user="root" password=""/>
	
	<!-- Abfrage der letzten Zeile der Datenbank -->					
	<sql:query dataSource="${db}" var="rs" >		
		SELECT * FROM ghostnet WHERE status = 'melden'	
	</sql:query>
	


<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<title>Bergung eines Geisternetzes</title>	
		
		<style>
	
			tr.markierung {
				background-color: grey;
			}
		
			table, th, td {
			  border: thin solid #a0a0a0;
			}
			table {
			  border-collapse: collapse;
			  border-width: thin 0 0 thin;
			  margin: 0 0 2em;
			  table-layout: auto;
			  max-width: 100%;		  
			}
			th, td {
			  font-weight: normal;
			  text-align: left;
			  padding: 5px;
			}
			th {
			  font-weight: bold;
			}

		</style>
</head>
<body>

	<h1>Bergung eines Geisternetzes</h1>
	
	<p>Vielen Dank für deine </p>
	
	<p>Möchtest du ein weiteres Netz bergen?</p>
	
	<p>Hier siehst du einen Überblick über Netze die du bergen kannst. Klicke auf ein Netz und gib bitte deinen Namen sowie deine Telefonnummer an!</p>
	<br />
	
		<!-- Datenbankabfrage mit Ausgabe der zu bergenden Geisternetze -->
	<table id="eintraegeAuswaehlen">
	
	    <tr>
	    	<th>ID</th>
		    <th>Datum</th>
		    <th>Größe</th>
		    <th>Koordinaten</th>
		    <th>Geisternetz</th>
		    <th>Status</th>
		    <th>Name</th>
		    <th>Telefon</th>
		   
	    </tr>   
	
		
		 <c:forEach var="row" items="${rs.rows}">  
		   <tr>    
			   <td><c:out value="${row.id}"/></td>
			   <td><c:out value="${row.datum}"/></td>
			   <td><c:out value="${row.groesse}"/></td>
			   <td><c:out value="${row.koordinaten}"/></td>
			   <td><c:out value="${row.netzname}"/></td>
			   <td><c:out value="${row.status}"/></td>
			   <td><c:out value="${row.name}"/></td>
			   <td><c:out value="${row.telefon}"/></td>
			</tr>
		 </c:forEach>
		 
	</table>
	
	<!-- verstecktes Formular -->	
	<div style="display:none;" id="zeigeFormular">	
		<!-- Formular für den Datenbankeintrag  -->
	<fieldset style="width:500px;">
	
		<legend>Netzangaben</legend>
	
		<form action="updateData" method="post" style="padding:20px; width:400px; height:400px;">
		
		  <p>Ausgewähltes Geisternetz:</p>
		  <p id="ausgabe" ></p>		


			<label for="name">Name:</label>
			<input type="text" name="name" /><br />
				
			<label for="telefon">Telefon:</label>
			<input type="text" name="telefon"/>				

			<input type="submit"/>
		
		</form>
		
	</fieldset>	
					
	</div> <!-- End verstecktes Formular -->	
	
	<script  type="text/javascript">
	
	// Geisternetz und Formular für Name anzeigen, wenn geklickt
	 var rows = document.getElementById('eintraegeAuswaehlen').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	    for (i = 0; i < rows.length; i++) {
	        rows[i].addEventListener('click', function() {
	        	document.getElementById('zeigeFormular').style.display = 'block';  
	        	// Markierung der ausgewählten Zeile
	        	this.classList.toggle('markierung');
	        	
	        	gewaehltesNetz = this.textContent;
	   				// gewähltes Netz ausgeben
	        		document.getElementById("ausgabe").textContent = gewaehltesNetz;
	     });
	  }
	

	</script>
	
</body>
</html>