<%@page import="model.Clienti"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Dao.CrudAmministratore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA UTENTI</title>
</head>
<body>
<% CrudAmministratore cr = new CrudAmministratore();
Clienti c = new Clienti(); 
ArrayList<Clienti> listaUtenti = cr.leggiUtenti(c);
%>

<h1>RICERCA UTENTI</h1>
INSERISCI I CAMPI NECESSARI:
<form method="post" action="menuAdmin?y=5">
<input type ="text" name="idCliente" list="list">
		<datalist id="list"> 
		<% for(Clienti l : listaUtenti){ %>
			<option><%= l.getIdCliente() %></option>
		<% } %>
		</datalist>	
		<input type="submit" value="ricerca">
	</form>
</body>
</html>