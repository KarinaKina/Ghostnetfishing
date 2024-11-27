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
		SELECT * FROM ghostnet ORDER BY id DESC LIMIT 1		
	</sql:query>

<html >
	  
<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<title>Meldung Geisternetze</title>
		
		<style>
		
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
			<h1>Shea Sepherd - Meldung eines Geisternetzes</h1>
				
			<p>Vielen Dank für deine Meldung! </p>	
			<p>Hier noch einmal eine Auflistung deiner Meldung des Geistsernetzes:</p>

			<!-- Datenbankabfrage mit Ausgabe des letzten Eintrags -->
			<table>
			
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
	
			<p>Möchtest du weitere Eintragungen vornehmen?</p>
			<p>Hier gehts zurück zur Startseite: <a href="geisternetze.jsp">Startseite </a></p>
	
</body>
</html>
