package Soni;

import java.util.ArrayList;

public class Database {
	private String name;
	public Database(String name) {
		this.name= name;
	}
	private ArrayList<Tabelle> tables = new ArrayList<Tabelle>();
	public void createtable(String n){
		this.tables.add(new Tabelle(n));
	}
	public Tabelle getTable(int i){
		return this.tables.get(i);
	}
	public Tabelle getLastTable(){
		return this.tables.get(this.tables.size()-1);
	}
	public String rmFormat(){
		String ret=this.name+":"+"\n";
		for(int i=0;i<this.tables.size();i++){
			ret=ret+this.tables.get(i).rmFormat();
		}
		return ret;
	}
	public String dotFormat(){
		String ret="graph {graph[splines=ortho]";
		for(int i=0;i<this.tables.size();i++){
			ret=ret+this.tables.get(i).dotFormat(i);
				ArrayList<String> reft = this.tables.get(i).getReft();
				for(int lauf=0;lauf<reft.size();lauf++){
					ret=ret+i+lauf+"[shape=diamond,label=\" \"];"+this.tables.get(i).getName()+"--"+i+lauf+"[label=\"n\"];"+i+lauf+"--"+reft.get(lauf)+"[label=\"1\"];";
				}
			}
		ret=ret+"}";
		return ret;
	}
}
