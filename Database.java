package Soni;

import java.util.ArrayList;

public class Database {
	private ArrayList<Tabelle> tables = new ArrayList<Tabelle>();
	public void createtable(String name){
		tables.add(new Tabelle(name));
	}
}
