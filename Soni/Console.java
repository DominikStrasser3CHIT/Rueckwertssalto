package Soni;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
		option.addOption("o", true, "RMFilename");
		option.addOption("e", true, "DotFilename");
		option.addOption("c", true, "der pfad wo das exe file liegt");

		try {
			CommandLine cmd = parser.parse(option, args);
			BufferedWriter bw = null;
			BufferedWriter bw2 = null;
			/*
			 *lesen aus der console
			 * und die einzelnen options getrennt speichern um sie später zu verwalten
			 */
			String servername = cmd.getOptionValue("h");
			String username = cmd.getOptionValue("u");
			String passwort = cmd.getOptionValue("p");
			String datenbankname = cmd.getOptionValue("d");//muss gesetzt werden
			String rmFilename = cmd.getOptionValue("o");
			String dotFilename = cmd.getOptionValue("e");
			String exePath = cmd.getOptionValue("c");
			if (cmd.hasOption("h")==false) {
				servername = "localhost";
			}
			if (cmd.hasOption("u")==false) {
				username = System.getProperty("user.name");
			}
			if (cmd.hasOption("p")==false) {
				passwort = "";
			}
			if (cmd.hasOption("o")==false) {
				rmFilename = "RmTest";
			}
			if (cmd.hasOption("e")==false) {
				dotFilename = "ERDTest";
			}
			ArrayList<String> res= Conect.rmdotString(servername, username, passwort,datenbankname);
			FileWriter fw = new FileWriter(rmFilename + ".txt");
			bw = new BufferedWriter(fw);
			bw.write(res.get(1));
			bw.close();
			FileWriter fw2 = new FileWriter(dotFilename + ".dot");
			bw2 = new BufferedWriter(fw2);
			bw2.write(res.get(0));
			bw2.close();
			System.out.println("\n"+"Ihr Rm wurde als "+rmFilename + ".txt gespeichert"+"\n"+"Ein dot file " +dotFilename+".dot wurde fuer sie erstellt.");
			if (cmd.hasOption("c")) {
				GraphViz gv = new GraphViz();
				gv.setDot(exePath);
				gv.add(res.get(0));
				gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), "pdf" ), System.getProperty("user.dir")+"/"+dotFilename+".pdf" );
				System.out.println("Ein Diagram ihrer Datenbank im PDF fromat "+dotFilename+".pdf  wurde erstellt."+"\n"+
				"Das Pdf kann auserhalb dieses Ordners entstehen");
			}else{
				System.out.println("Bitte verwenden sie graphviz oder ein anliches tool um ihr erd daraus zu generieren.");
			}

		}catch (ParseException | IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Useable options"+"\n"+"-h ... Hostname des DBMS. Standard: localhost"+"\n"+
					"-u ... Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzter"+"\n"+
					"-p ... Passwort. Alternativ kann ein Passwortprompt angezeigt werden. Standard: keins"+"\n"+
					"-d ... Name der Datenbank(Pflicht)"+"\n"+
					"-o ... Name des RMFiles"+"\n"+
					"-d ... Name des für das erd benötige dotfile name"+"\n"+
					"-c ... Graphviz notwendig: Der pfad ihres dot.exe"+"\n"+
					"Syntax \"Pfad\"");
		}




	}
}
