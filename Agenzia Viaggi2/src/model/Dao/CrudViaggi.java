package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import control.Connessione;
import model.Prenotazioni;
import model.Viaggi;
import model.Idao.ICrudViaggi;

public class CrudViaggi extends Connessione implements ICrudViaggi 
{
	Connection conn=null;
	Statement s=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String query;

	ArrayList<Viaggi> lista = new ArrayList<>();

	@Override
	public Viaggi inserisciViaggio(Viaggi v) 
	{
		try 
		{
			conn=connetti();
			query="Insert into viaggi (destinazione,prezzo,disponibilit‡,datainizio,datafine,quantita) values(?,?,?,?,?,?)";
			ps=conn.prepareStatement(query);
			ps.setString(1, v.getDestinazione());
			ps.setDouble(2, v.getPrezzo());
			ps.setInt(3, v.getDisponibilita());
			ps.setString(4, v.getDataininzio());
			ps.setString(5, v.getDatafine());
			ps.setInt(6, v.getQuantit‡());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return v;



	}
	@Override
	public ArrayList<Viaggi> listaViaggi(Viaggi v) 
	{

		try {
			conn=connetti();
			query="Select * from viaggi";
			s=conn.createStatement();
			rs=s.executeQuery(query);
			while(rs.next()) {
				Viaggi v1 = new Viaggi();
				v1.setIdViaggio(rs.getInt(1));
				v1.setDestinazione(rs.getString(2));
				v1.setPrezzo(rs.getDouble(3));
				v1.setDisponibilita(rs.getInt(4));
				v1.setDataininzio(rs.getString(5));
				v1.setDatafine(rs.getString(6));
				v1.setQuantit‡(rs.getInt(7));
				lista.add(v1);
			}
		} catch (SQLException e) 
		{

			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public Viaggi eliminaViaggio(Viaggi v) 
	{
		try {
			conn = connetti ();
			query = "Delete from viaggi where idviaggi=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1,v.getIdViaggio());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
		return v;

	}
	@Override
	public Viaggi aggiungiViaggio(Viaggi v) 
	{
		try
		{
			conn=connetti();
			query="Update viaggi set quantita=? + quantita where idviaggi=?";
			ps=conn.prepareStatement(query);
			ps.setInt(1, v.getQuantit‡());
			ps.setInt(2, v.getIdViaggio());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return v;


	}
	
	@Override
	public Viaggi diminuisciViaggio(Viaggi v) 
	{
		try
		{
			conn=connetti();
			query="Update viaggi set quantita = quantita - ? where idviaggi=?";
			ps=conn.prepareStatement(query);
			ps.setInt(1, v.getQuantit‡());
			ps.setInt(2, v.getIdViaggio());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return v;


	}
	
	public Viaggi controlloVuoto(Viaggi v) {
		try {
			conn = connetti();
			String queryR = "select * from viaggi";
			s = conn.createStatement();
			rs = s.executeQuery(queryR);
			while(rs.next()) {
				int quantit‡Pa = rs.getInt(7);
				if(quantit‡Pa == 0) {
					String query = "update viaggi set disponibilit‡ = ? where quantita = ?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, 0);
					ps.setInt(2, quantit‡Pa);
				}
			}
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v;
		
	}


}




