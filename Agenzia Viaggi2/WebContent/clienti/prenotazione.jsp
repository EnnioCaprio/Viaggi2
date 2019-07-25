<%@page import="model.Viaggi"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Dao.CrudPrenotazioni"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.Dao.CrudViaggi"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Pagina Prenotazioni</h1>

	<%
		CrudViaggi cv = new CrudViaggi();
		Viaggi v = new Viaggi();
		ArrayList<Viaggi> lista = cv.listaViaggi(v);
	%>

	<table border="1" cellpadding="10">
		<tr>
			<td>Id</td>
			<td>Destinazione</td>
			<td>Prezzo</td>
			<td>Disponibilità</td>
			<td>Data inizio</td>
			<td>Data fine</td>
			<td>Quantità</td>
		</tr>
		<%
			int flag = 3;
			for (Viaggi l : lista) {
				if (l.getDisponibilita() == 1 && l.getQuantità() >= 1) {
		%>
		<tr>
			<td><%=l.getIdViaggio()%></td>
			<td><%=l.getDestinazione()%></td>
			<td><%=l.getPrezzo()%></td>
			<td><%=l.getDisponibilita()%></td>
			<td><%=l.getDataininzio()%></td>
			<td><%=l.getDatafine()%></td>
			<td><%=l.getQuantità()%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
	<form action="menuCliente?y=2" method="post">
		<label>Destinazione</label><input type="text" name="idViaggio"
			list="list" autocomplete="off" required>
		<datalist id="list">
			<%
				for (Viaggi l : lista) {
					if (l.getDisponibilita() == 1) {
			%>
			<option><%=l.getIdViaggio()%></option>
			<%
				}
				}
			%>
		</datalist>
		<input type="number" name="quantitapersone" min="1" max="10" required>
		<input type="submit" value="conferma">
	</form>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
