package Soni;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * 
 * @author Adaresh Soni
 * @versio 01.12.2015
 *
 * Ein Programm das mittels Aufruf in der Konsole Inhalt einer Datenbank zurueckgibt
 * Werde eine erweiterung auf ein factury pattern versuchen um neue optionen ein und aus zu tauschen
 */

public class Console {
	public static void main (String [] args) {
		CommandLineParser parser = new BasicParser();
		Options option = new Options();
		/*
		 * Alle notwendigen Optionen hinzufügen
		 */
		option.addOption("h", true, "Servername");
		option.addOption("u", true, "Username");
		option.addOption("p", true, "Passwort");
		option.addOption("d", true, "Datenbankname");
		
		try {
			CommandLine cmd = parser.parse(option, args);
			BufferedWriter bw = null;
			/*
			 *lesen aus der console
			 * und die einzelnen options getrennt speichern um sie später zu verwalten
			 */
			String servername = cmd.getOptionValue("h");
			String username = cmd.getOptionValue("u");
			String passwort = cmd.getOptionValue("p");
			String datenbankname = cmd.getOptionValue("d");//muss auch gesetzt werden
			/*
			 * lesen ob etwas drinnen ist und falls nicht auf default wert setzten bzw ignorieren
			 */
			if (cmd.hasOption("h")==false) {
				servername = "localhost";
			}
			if (cmd.hasOption("u")==false) {
				username = System.getProperty("user.name");
			}
			if (cmd.hasOption("p")==false) {
				passwort = "";
			}
			/*
			 * Fehler * im cmd deswegen keyword gesetzt
			 */
			String res= Conect.select(servername, username, passwort,datenbankname);
			System.out.println(res);
		}catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println("Useable options"+"\n"+"-h ... Hostname des DBMS. Standard: localhost"+"\n"+
			"-u ... Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzter"+"\n"+
			"-p ... Passwort. Alternativ kann ein Passwortprompt angezeigt werden. Standard: keins"+"\n"+
			"-d ... Name der Datenbank(Pflicht)");
		}
		
		
		
		
	}
}
