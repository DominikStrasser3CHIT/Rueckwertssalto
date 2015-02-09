import java.sql.*;


public class Exporter {
	public static void main (String [] args) throws ClassNotFoundException {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String servername = "localhost";
		String datenbankname = "premiere";
		String username = "root";
		String passwort = "Dstrasser1996";
		String liste = "*";
		String tabelle = "sender";
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+servername+"/"+datenbankname+"?" +
                    "user="+username+"&password="+passwort);
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT "+liste+" FROM "+tabelle);
			
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			stm.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}
