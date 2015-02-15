package Soni;

import java.util.ArrayList;

public class Database {
	private String name;
	/**
	 * @param name -name der DatenBank
	 */
	public Database(String name) {
		this.name= name;
	}
	private ArrayList<Tabelle> tables = new ArrayList<Tabelle>();
	/**
	 * @param n Name der Tabelle
	 * Fügt eine Tabelle der Datenbank hinzu.
	 */
	public void createtable(String n){
		this.tables.add(new Tabelle(n));
	}
	/**
	 * @param i die wievielte Tabelle.
	 * @return Gibt die Tabelle an der stelle i zurück.
	 */
	public Tabelle getTable(int i){
		return this.tables.get(i);
	}
	/**
	 * @return Gibt die letzte der Datenbank hinzugefügte Tabelle zurück.
	 */
	public Tabelle getLastTable(){
		return this.tables.get(this.tables.size()-1);
	}
	/**
	 * @return Gibt die Datenbank im rm Format zurück.
	 */
	public String rmFormat(){
		String ret=this.name+":"+"\n";
		for(int i=0;i<this.tables.size();i++){
			ret=ret+this.tables.get(i).rmFormat();
		}
		return ret;
	}
	/**
	 * @return Gibt die Datenbank im dot Format zurück.
	 */
	public String dotFormat(){
		String ret="graph {graph[splines=ortho,overlap=false,rankdir=RL]";
		for(int i=0;i<this.tables.size();i++){
			ret=ret+this.tables.get(i).dotFormat();
				ArrayList<String> reft = this.tables.get(i).getReft();
				for(int lauf=0;lauf<reft.size();lauf++){
					ret=ret+i+lauf+"[shape=diamond,label=\" \"];"+this.tables.get(i).getName()+"--"+i+lauf+"[label=\"n\"];"+i+lauf+"--"+reft.get(lauf)+"[label=\"1\"];";
				}
			}
		ret=ret+"}";
		return ret;
	}
}
