package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Clienti;
import model.Viaggi;
import model.Dao.CrudAmministratore;
import model.Dao.CrudViaggi;

@WebServlet("/menuAdmin")
public class MenuAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x=Integer.parseInt(request.getParameter("x"));
		switch(x) {
		case 1:
			getServletContext().getRequestDispatcher("/admin/listaUtenti.jsp").forward(request, response);
			break;
		case 2:
			getServletContext().getRequestDispatcher("/admin/inserimentoViaggi.jsp").forward(request, response);
			break;
		case 3:
			getServletContext().getRequestDispatcher("/admin/listaViaggi.jsp").forward(request, response);
			break;
		case 4:
			getServletContext().getRequestDispatcher("/admin/ricercaUtenti.jsp").forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		Viaggi v=new Viaggi();
		Clienti c = new Clienti();
		CrudViaggi cv=new CrudViaggi();
		CrudAmministratore ca = new CrudAmministratore();
		int y=Integer.parseInt(request.getParameter("y"));
		switch(y) {
		case 1: 
			String destinazione=request.getParameter("destinazione");
			double prezzo=Double.parseDouble(request.getParameter("prezzo"));
			String datainizio=request.getParameter("datainizio");
			String datafine=request.getParameter("datafine");
			int quantit‡=Integer.parseInt(request.getParameter("quantit‡"));
			v.setDestinazione(destinazione);
			v.setPrezzo(prezzo);
			v.setDataininzio(datainizio);
			v.setDatafine(datafine);
			v.setQuantit‡(quantit‡);
			if(quantit‡ == 0) {
				v.setDisponibilita(0);
			}else if(quantit‡ > 0) {
				v.setDisponibilita(1);
			}
			cv.inserisciViaggio(v);
			getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp").forward(request, response);
			break;
		case 2:
			int idViaggio=Integer.parseInt(request.getParameter("idviaggio"));
			v.setIdViaggio(idViaggio);
			cv.eliminaViaggio(v);
			getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp").forward(request, response);
			break;
		case 3:
			idViaggio=Integer.parseInt(request.getParameter("idviaggio"));
			quantit‡=Integer.parseInt(request.getParameter("quantit‡"));
			v.setIdViaggio(idViaggio);
			v.setQuantit‡(quantit‡);
			cv.aggiungiViaggio(v);
			getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp").forward(request, response);
			break;
		case 4:
			ArrayList<Viaggi> lista = cv.listaViaggi(v);
			PrintWriter out = response.getWriter();
			idViaggio=Integer.parseInt(request.getParameter("idviaggio"));
			quantit‡=Integer.parseInt(request.getParameter("quantit‡"));
			v.setIdViaggio(idViaggio);
			v.setQuantit‡(quantit‡);
			int a = 3;
			for(Viaggi l : lista) {
				int quantit‡Pacchetto = l.getQuantit‡();
				if(quantit‡ < quantit‡Pacchetto) {
					out.println("Ciao");
					a = 1;
				}else {
					a = 2;
				}
			}
			if(a == 2) {
				cv.diminuisciViaggio(v);
			}
			
			getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp").forward(request, response);
			break;
		case 5:
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			c.setIdCliente(idCliente);
			//ArrayList<Clienti> listaUtente = ca.ricercaCliente(c);
			Clienti cc = ca.ricercaCliente(c);
			request.setAttribute("cc", cc);
			ca.ricercaCliente(c);
			getServletContext().getRequestDispatcher("/admin/risultatoRicerca.jsp").forward(request, response);
			break;
		}
	}
}