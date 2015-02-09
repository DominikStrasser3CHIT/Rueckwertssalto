package Soni;

import java.util.ArrayList;

public class Tabelle {
	public Tabelle(String name) {
		this.name=name;
	}
	private ArrayList<String> pk = new ArrayList<String>(); 
	private ArrayList<String> fk = new ArrayList<String>();
	private ArrayList<String> col = new ArrayList<String>();
	private ArrayList<String> pkfk = new ArrayList<String>();
	private String name =" ";
	public void addcol(String col) {
		this.col.add(col);
	}
	public void addpk(String pk) {
		this.pk.add(pk);
	}
	public void addfk(String fk) {
		this.fk.add(fk);
	}
	public void addpkfk(String pkfk) {
		this.pkfk.add(pkfk);
	}
	public String toString(){
		String ret=" ";
		
	}
}
