package Soni;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Conect {
	/**
	 * @param servername Der hostname.
	 * @param username Der username welcher auf die datenbank zugreifen soll.
	 * @param passwort	Das passwort des users welcher zugreifensoll (fals nicht vorhanden bitte " " einf�gen).
	 * @param datenbankname	Die datenbank auf welche zu gegriefen werden soll.
	 */
	public static ArrayList<String> rmdotString(String servername,String username,String passwort,String datenbankname){
		String retRm="";
		String retdot="";
		Connection con = null;
		Statement stm = null;
		ResultSet rsimport = null;
		ResultSet rstable = null;
		ResultSet rspk = null;
		ResultSet rscol = null;
		ArrayList<String> ret = new ArrayList<String>(); 
		DatabaseMetaData metadata= null;
		Database d = new Database(datenbankname);
		try {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName(servername);
			ds.setPassword(passwort);
			ds.setUser(username);
			ds.setDatabaseName(datenbankname);

			con = ds.getConnection();
			stm = con.createStatement();
			metadata = con.getMetaData();
			rstable= metadata.getTables(con.getCatalog(), con.getSchema(), null, null);
			while (rstable.next()) {
				d.createtable(rstable.getString(3));
				rsimport = metadata.getImportedKeys(con.getCatalog(), con.getSchema(), rstable.getString(3));
				rspk = metadata.getPrimaryKeys(con.getCatalog(), con.getSchema(), rstable.getString(3));
				rscol= metadata.getColumns(con.getCatalog(), null, rstable.getString(3), null);
				while (rscol.next()) {
					while (rspk.next()) {
						d.getLastTable().addColum(rspk.getString(4));
						d.getLastTable().getLastColum().isPk();
					}	
					while (rsimport.next()) {
						if(d.getLastTable().isNewColum(rsimport.getString(8))){
							d.getLastTable().addColum(rsimport.getString(8));
							d.getLastTable().getLastColum().isFk(rsimport.getString(3)+"."+rsimport.getString(4),rsimport.getString(3));
						}else{
							d.getLastTable().getColum(rsimport.getString(8)).isFk(rsimport.getString(3)+"."+rsimport.getString(4),rsimport.getString(3));
						}
					}
					if(d.getLastTable().isNewColum(rscol.getString(4))){
						d.getLastTable().addColum(rscol.getString(4));
					}
					if(rscol.getString(18).equals("NO")){
						d.getLastTable().getColum(rscol.getString(4)).isNab();
					}
				}
			}
			retdot=d.dotFormat();
			ret.add(retdot);
			retRm=d.rmFormat();
			ret.add(retRm);
			stm.close();
			con.close();
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


		return ret;
	}
}

