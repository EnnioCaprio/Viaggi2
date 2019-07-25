package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.Connessione;
import model.Clienti;
import model.Viaggi;
import model.Idao.ICrudUtente;

public class CrudUtente extends Connessione implements ICrudUtente
{
	Connection conn=null;
	PreparedStatement ps=null;
	Statement s=null;
	ResultSet rs=null;
	/*@Override
	public ResultSet infoutente(Clienti c) {
		try {
			conn=connetti();
			String query="select * from clienti where idcliente=?";
			ps=conn.prepareStatement(query);
			ps.setInt(1, c.getIdCliente());
			ps.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}*/
	@Override
	public Clienti modificaInformazioni(Clienti c) {
		try {
			conn = connetti();
			String query = "update clienti set nome=?, cognome=?, telefono=?, username=?, password=? where idCliente = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, c.getTel());
			ps.setString(4, c.getUsername());
			ps.setString(5, c.getPassword());
			ps.setInt(6, c.getIdCliente());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}
	
	@Override
	public Viaggi ricercaViaggio(Viaggi v) {
		try {
			conn = connetti();
			String query = "select * from viaggi where idviaggi = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, v.getIdViaggio());
			rs = ps.executeQuery();
			while(rs.next()) {
				v.setIdViaggio(rs.getInt(1));
				v.setDestinazione(rs.getString(2));
				v.setPrezzo(rs.getDouble(3));
				v.setDisponibilita(rs.getInt(4));
				v.setDataininzio(rs.getString(5));
				v.setDatafine(rs.getString(6));
				v.setQuantità(rs.getInt(7));
			}
			ps.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

}
