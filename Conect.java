package Soni;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Conect {
	/**
	 * @param servername Der hostname
	 * @param username Der username welcher auf die datenbank zugreifen soll
	 * @param passwort	Das passwort des users welcher zugreifensoll (fals nicht vorhanden bitte " " einfügen)
	 * @param datenbankname	Die datenbank auf welche zu gegriefen werden soll
	 * @param sfeld	Der Inhanlt der ORDER BY Klausel (fals nicht vorhanden bitte " " einfügen)
	 * @param srichtung	Die sotiert richtung nur sinvoll fals auch in sfeld etwas vorhanden
	 * @param bedingung Der Inhanlt der WHERE Klausel 
	 * @param trennzeichen Ein zeichen mittels dem getrennt werden soll
	 * @param liste	Der Wert welcher ummittel bar nach select kommt
	 * @param tabellenname Name der Tabelle welche abgefragt werden soll
	 * @return den gesamten Output des eingegeben 
	 */
	public static String select(String servername,String username,String passwort,String datenbankname){
		String ret="";
		Connection con = null;
		Statement stm = null;
		ResultSet rsimport = null;
		ResultSet rstable = null;
		ResultSet rspk = null;
		ResultSet rscol = null;
		ArrayList<String> pk = new ArrayList<String>(); 
		ArrayList<String> fk = new ArrayList<String>(); 
		DatabaseMetaData metadata= null;
		try {
			//String driverName = "org.gjt.mm.mysql.Driver";
			//Class.forName(driverName);
			//String url = "jdbc:mysql://" + servername + "/" + datenbankname;
			//con = DriverManager.getConnection(url, username,passwort);
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName(servername);
			ds.setPassword(passwort);
			ds.setUser(username);
			ds.setDatabaseName(datenbankname);

			con = ds.getConnection();
			stm = con.createStatement();
			//ganz normal die query gesetz
			metadata = con.getMetaData();
			rstable= metadata.getTables(con.getCatalog(), con.getSchema(), null, null);
			while (rstable.next()) {
				rsimport = metadata.getImportedKeys(con.getCatalog(), con.getSchema(), rstable.getString(3));
				rspk = metadata.getPrimaryKeys(con.getCatalog(), con.getSchema(), rstable.getString(3));
				rscol= metadata.getColumns(con.getCatalog(), null, rstable.getString(3), null);
				ret=ret+"\n"+rstable.getString(3)+"(";
				while (rscol.next()) {
				while (rsimport.next()) {
					while (rspk.next()) {
						pk.add(rspk.getString(4));
					}
					fk.add(rsimport.getString(8));
				}
				if(pk.contains(rscol.getString(4))){
					if(fk.contains(rscol.getString(4))){
						ret=ret+"<pk><fk>"+rscol.getString(4)+"</fk></pk>,";
					}else{
						ret=ret+"<pk>"+rscol.getString(4)+"</pk>,";
					}
				}else{
					if(fk.contains(rscol.getString(4))){
						ret=ret+"<fk>"+rscol.getString(4)+"</fk>,";
					}else{
						ret=ret+rscol.getString(4)+",";
					}
				}
			}
				ret=ret+")";
			}
			stm.close();
			con.close();
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
			
		
		return ret;
	}
}

