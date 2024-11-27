<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	
	<title>Ghostnetfishing</title>

	<!-- Styleangaben für die Tabelle aus der Datenbank -->
	<style>
		form {
		 width: 400px;
		}
		label, input, select {
		display: block;
		float: left;
		padding: 2px;
		margin-bottom: 5px;
		}
		label {
		width: 200px;
		text-align: left;
		}
		input, select {
		 width: 150px;
		}
		form br {
		clear: left;
		}
		input #senden {
			float: right;
		}
	</style>
	
</head>

<body>

	<h1>Shea Sepherd</h1>
	
	<p>Hallo lieber Geisterfischer!</p>
	<p>Folgend bekommst du eine Übersicht aus unserer Geisternetzdatenbank. 
	   Du kannst weiter unten selbst Einträge vornehmen.</p>
	
	<!-- Verbindung zur Datenbank für die Ausgabe der Tabelle -->
 	<sql:setDataSource var="snap" driver="com.mysql.cj.jdbc.Driver"
	     url="jdbc:mysql://localhost:3306/ghostnetfishing"
	     user="root"  password=""/>
	
	<!-- Abfrage der Datenbankeinträge --> 
	<sql:query dataSource="${snap}" var="result">
		select * from ghostnet;
	</sql:query>
	 
	<table>
		<tr>
		   <th>ID</th>
		   <th>Datum</th>
		   <th>Größe</th>
		   <th>Koordinaten</th>
		   <th>Netzname</th>
		   <th>Status</th>
		   
		 </tr>
	 
		 <c:forEach var="row" items="${result.rows}">
		
		<tr>
		   <td><c:out value="${row.id}"/></td>
		   <td><c:out value="${row.datum}"/></td>
		   <td><c:out value="${row.groesse}"/></td>
		   <td><c:out value="${row.koordinaten}"/></td>
		   <td><c:out value="${row.netzname}"/></td>
		   <td><c:out value="${row.status}"/></td>
		   
		
		</tr>
		
		</c:forEach>
		
	</table>
		
		<h2>Geisternetze auf unserer Karte ansehen - OSM</h2>
		
		<p>Die Karte lässt sich auch bearbeiten, ein automatisches Weiterleiten an die Datenbank ist zum Zeitpunkt leider noch nicht möglich. 
		   Bitte nutzen Sie dafür das Formular weiter unten.</p>

			<!-- Übersicht der Karte mit den markierten Geisternetzen -->
			<div id="map">
			
				<iframe width="100%" height="300px" frameborder="0" src="//umap.openstreetmap.de/de/map/ghostnetfishing_64907"></iframe>
			
			</div>	

			<a href="https://umap.openstreetmap.de/de/map/ghostnetfishing_64907?scaleControl=false&miniMap=false&scrollWheelZoom=false&zoomControl=true&editMode=disabled&moreControl=true&searchControl=null&tilelayersControl=null&embedControl=null&datalayersControl=true&onLoadPanel=none&captionBar=false&captionMenus=true">Zur Bearbeitung</a>

			<br></br>

	
		<h2>Eintrag in die Shea Sepherd - Datenbank</h2> 
		
		<p>Hier kannst du nun eintragen ob du ein Geisternetz gefunden oder geborgen hast. 
		   Außerdem kannst du melden ob du eines nicht am Standort gefunden hast, oder dich selbst für die Bergung eines Netzes anmelden.</p>
		
	<!-- Formular für den Datenbankeintrag  -->
	<fieldset style="width:500px;">
	
		<legend>Netzangaben</legend>
	
		<form name="formular" action="insertSQL" method="post" onsubmit="return allesAusgefuellt()" id="formular" style="padding:20px; width:400px; height:400px;">
		
			<label for="datum">Datum:</label>
			<input type="text" name="datum"/>
					
			<label for="groesse">Größe:</label>	
			<input type='text' name='groesse'/>
	
			<label for="koordinaten">Koordinaten:</label>	
			<input type='text' name='koordinaten'/>
			
			<label for="netzName">Geisternetzbezeichnung:</label>	
			<input type='text' name='netzName'/>
			
			<label for="status">Bitte einen Status wählen:</label>
				
			<select name="status" id="status" onclick="checkAuswahl(this.options[this.selectedIndex].value);">
				<option selected ></option>
				<option value="melden">Sichtung melden</option>
				<option value="geborgen" id="geborgen">Geborgen melden</option>
				<option value="verschollen">Verschollen melden</option>
				<option value="bergen" id="bergen">Bergung anmelden</option>
			</select>
			
			<div style="display:none;" id="name">
			
			<br/>
			
			<p style="color:red;">Ein verschollenes Geisternetz kann nicht anonym gemeldet werden, bitte hinterlasse uns deshalb deinen Namen und die Telefonnummer!</p>
				<label for="name">Name:</label>
				<input type="text" name="name" />
				
				<label for="telefon">Telefon:</label>
				<input type="text" name="telefon"/>
				
			</div>
			
			<input type="submit"/>
		
		</form>
		
	</fieldset>

	<script type="text/javascript">
	
		// "verschollen" darf nicht ohne Namen und Telefon gemeldet werden
		function checkAuswahl(status){
		
			auswahlFeld = document.getElementById("name");
			
			if(status == "verschollen"){
				auswahlFeld.style.display = 'block';

			
			}else{
				auswahlFeld.value = '';
				auswahlFeld.style.display = 'none';
			}
		}
	
		// prüfen ob alles ausgefüllt
		function allesAusgefuellt (){
				
			erg = true;
			pruef = document.formular;
			
			if(pruef.datum.value == ""){
				erg = false;
			}
			if(pruef.groesse.value == ""){
				erg = false;
			}
			if(pruef.koordinaten.value == ""){
				erg = false;
			}
			if(pruef.netzName.value == ""){
				erg = false;
			}
			if(pruef.status.value == ""){
				erg = false;
			}
			
			// wenn Status verschollen, dann auch Name und Telefon auf Eintrag prüfen
			if(pruef.status.value == "verschollen"){
				
				if(pruef.name.value == ""){
					erg = false;
				}
				if(pruef.telefon.value == ""){
					erg = false;
				}
			}
			
			if(erg == false){
				alert('Bitte alle Felder ausfüllen');
				return erg;
							
			}
			
	}
		
	</script>
	

</body>
</html>