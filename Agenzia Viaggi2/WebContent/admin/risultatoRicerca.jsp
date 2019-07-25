<%@page import="java.util.ArrayList"%>
<%@page import="model.Clienti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultato Ricerca</title>
</head>
<body>
<%
	Clienti c = (Clienti) request.getAttribute("cc");
%>

<table border="1" align="center">
		<tr>
			<td>ID CLIENTE</td>
			<td>USERNAME</td>
			<td>NOME</td>
			<td>COGNOME</td>
			<td>TELEFONO</td>
		</tr>
		
		<tr align="center">
			<td><%= c.getIdCliente() %></td>
			<td><%= c.getUsername() %></td>
			<td><%= c.getNome() %></td>
			<td><%= c.getCognome() %></td>
			<td><%= c.getTel() %></td>
		</tr>
		
</table>
</body>
</html>