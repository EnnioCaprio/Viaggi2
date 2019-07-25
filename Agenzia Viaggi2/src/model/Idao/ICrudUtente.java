package model.Idao;

import java.sql.ResultSet;

import model.Clienti;
import model.Viaggi;

public interface ICrudUtente 
{
//public ResultSet infoutente(Clienti c);
	
	public Clienti modificaInformazioni(Clienti c);

	public Viaggi ricercaViaggio(Viaggi v);

}
