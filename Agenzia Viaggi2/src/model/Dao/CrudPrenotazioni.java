package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import control.Connessione;
import model.Clienti;
import model.Prenotazioni;
import model.Viaggi;
import model.Idao.ICrudPrenotazioni;

public class CrudPrenotazioni extends Connessione implements ICrudPrenotazioni{

	Connection conn = null;
	PreparedStatement ps = null;
	Statement s = null;
	ResultSet rs = null;
	String query;
	
	ArrayList<Prenotazioni> lista = new ArrayList<Prenotazioni>();
	
	@Override
	public Prenotazioni inserisciPrenotazioni(Clienti c, Viaggi v, Prenotazioni p) {
		try {
			conn = connetti();
			query = "insert into prenotazione(idcliente, idviaggi,quantitapersone, prezzototale) values (?,?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCliente());
			ps.setInt(2, v.getIdViaggio());
			ps.setInt(3, p.getQuantitapersone());
			ps.setDouble(4, p.getPrezzototale());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/*@Override
	public Prenotazioni inserisciPrenotazioni2(Clienti c, Viaggi v, Prenotazioni p) {
		try {
			conn = connetti();
			String queryR = "select * from viaggi";
			s = conn.createStatement();
			rs = s.executeQuery(queryR);
			boolean flag = false;
			while(rs.next()) {
				int disponibilità = rs.getInt(7);
				if(disponibilità == 1) {
					flag = true;
				}else if (disponibilità == 0) {
					flag = false;
				}
			}
			if(flag == true) {
				query = "insert into prenotazione(idcliente, idviaggi,quantitapersone, prezzototale) values (?,?,?,?)";
				ps = conn.prepareStatement(query);
				ps.setInt(1, c.getIdCliente());
				ps.setInt(2, v.getIdViaggio());
				ps.setInt(3, p.getQuantitapersone());
				ps.setDouble(4, p.getPrezzototale());
				ps.executeUpdate();
			}
			else {
				System.out.println("nothing");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}*/
	
	public Viaggi moddarePrenotazione(Viaggi v) {
		try {
			conn = connetti();
			query =  "update viaggi\r\n" + 
					"set quantita = quantita - 1\r\n" + 
					"where idviaggi = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, v.getIdViaggio());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
		
	@Override
	public ArrayList<Prenotazioni> listaPrenotazioni() {
		try {
			conn = connetti();
			query ="Select prenotazione.idprenotazione, viaggi.destinazione, viaggi.datainizio, viaggi.datafine, prenotazione.prezzototale, prenotazione.quantitapersone, viaggi.prezzo from clienti\r\n" + 
					"inner join prenotazione\r\n" + 
					"on clienti.idcliente=prenotazione.idcliente\r\n" + 
					"inner join viaggi\r\n" + 
					"on prenotazione.idviaggi=viaggi.idviaggi";
			s = conn.createStatement();
			rs = s.executeQuery(query);
			while(rs.next()) {
				Viaggi v = new Viaggi();
				Prenotazioni p = new Prenotazioni();
				p.setIdPrenotazioni(rs.getInt(1));
				v.setDestinazione(rs.getString(2));
				v.setDataininzio(rs.getString(3));
				v.setDatafine(rs.getString(4));
				p.setPrezzototale(rs.getDouble(5));
				p.setViaggi(v);
				p.setQuantitapersone(rs.getInt(6));
				lista.add(p);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Prenotazioni annullaPrenotazioni(Prenotazioni p) {
		try {
			conn = connetti();
			query = "delete from prenotazione where idprenotazione = ?";
			ps=conn.prepareStatement(query);
			ps.setInt(1, p.getIdPrenotazioni());
			ps.executeUpdate();
			ps.close();
			conn.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return p;
		
		
	}
	
	
	
	

}
