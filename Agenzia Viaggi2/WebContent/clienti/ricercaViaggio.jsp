<%@page import="model.Viaggi"%>
<%@page import="model.Dao.CrudViaggi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA VIAGGIO</title>
</head>
<body>
<% 	CrudViaggi cv = new CrudViaggi();
Viaggi v = new Viaggi(); 
ArrayList<Viaggi> listaViaggi = (ArrayList) cv.listaViaggi(v);
%>

<h1>RICERCA UTENTI</h1>
INSERISCI I CAMPI NECESSARI:
<form method="post" action="menuCliente?y=4">
<input type ="text" name="idViaggi" list="list">
		<datalist id="list"> 
		<% for(Viaggi l : listaViaggi){ %>
			<option><%=	l.getIdViaggio() %></option>
		<% } %>
		</datalist>	
		<input type="submit" value="ricerca">
	</form>
</body>
</html>