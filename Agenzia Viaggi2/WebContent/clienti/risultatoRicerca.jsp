<%@page import="model.Viaggi"%>
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
	Viaggi v = (Viaggi) request.getAttribute("v1");
%>

<table border="1" align="center">
		<tr>
			<td>ID VIAGGI</td>
			<td>DESTINAZIONE</td>
			<td>PREZZO</td>
			<td>DISPONIBILITA'</td>
			<td>DATA INIZIO</td>
			<td>DATA FINE</td>
		</tr>
		
		<tr align="center">
			<td><%= v.getIdViaggio() %></td>
			<td><%= v.getDestinazione() %></td>
			<td><%= v.getPrezzo() %></td>
			<td><%= v.getDisponibilita() %></td>
			<td><%= v.getDataininzio() %></td>
			<td><%= v.getDatafine() %></td>
		</tr>
		
</table>
</body>
</html>