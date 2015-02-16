package Soni;

import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.UnrecognizedOptionException;

public class Command {
	/**
	 * @param option -die optionen welche zu verfügung stehen
	 * @param args 
	 * @return gibt ein geparstes CommandLine zurück fals es keinen fehler gibt
	 */
	public static CommandLine getCommandLine(Options option,String[] args){
		Options all= option;
		CommandLineParser parser=new PosixParser();
		CommandLine cmdline=null;
		try {
			cmdline=parser.parse(all,args);
		}
		catch (AlreadySelectedException ase) {
			System.err.println("Argument already selected: " +ase.getMessage());// Funktioniert nicht? Keine Ahnung wiso
		}
		catch (MissingArgumentException mae) {
			System.err.println("Missing argument: "+mae.getMessage());
		}
		catch (MissingOptionException moe) {
			System.err.println("Missing Option: " +moe.getMessage());
		}
		catch (UnrecognizedOptionException uoe) {
			System.err.println("Unknown argument: " +uoe.getMessage());// Funktioniert Teilweise nicht? Keine Ahnung wiso
		}
		catch (ParseException pe) {
			System.err.println(pe.getMessage());
		}
		return cmdline;
	}

}
