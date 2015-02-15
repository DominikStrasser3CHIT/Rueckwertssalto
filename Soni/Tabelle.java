package Soni;

import java.util.ArrayList;

/**
 * @author Adi
 *
 */
public class Tabelle {
	private String name;
	private ArrayList<Colum> colum = new ArrayList<Colum>();
	private ArrayList<String> reft= new ArrayList<String>();
	/**
	 * @return gibe einen ArrayList<String> mit allen tabelen namen zu welchen es eine Beziehung aufbauen soll.(Nur für das dot file notwendig)
	 */
	public ArrayList<String> getReft() {
		return reft;
	}
	/**
	 * @param reft  Ein Tabelen name mit den eine Beziehung eingegangen werden soll.
	 * @return gibt true oder false zurück jenachdem ob diese Tabele schon gespeichet wurde.
	 */
	public boolean isNewreft(String reft){
		for(int i=0;i<this.reft.size();i++){
			if(reft.equals(this.reft.get(i))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Speichert von allen columns die fals sie auf einen tabelle referenzieren diese tabelle.
	 * (Einmal fals mehrere auf eins referenzieren)
	 */
	public void addacReft(){
		for(int i=0;i<this.colum.size();i++){
			if(this.colum.get(i).getRealt()!=null){
				if(this.isNewreft(this.colum.get(i).getRealt())){
					this.reft.add(this.colum.get(i).getRealt());
				}
			}
		}
	}
	/**
	 * @param name Name der Tabelle
	 */
	public Tabelle(String name) {
		this.name=name;
	}
	/**
	 * @return Gibt den namen der Tabelle zurück
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name -name des columns
	 * fügt der Tabelle eine Column (Spalte) hinzu.
	 */
	public void  addColum(String name){
		this.colum.add(new Colum(name));
	}
	/**
	 * @return Gibt die Tabelle im RMFormat zurück
	 */
	public String rmFormat(){
		String ret=this.name+"(";
		for(int i=0;i<this.colum.size();i++){
			ret=ret+colum.get(i).rmFormat();
		}
		ret=ret+");"+"\n";
		return ret;
	}
	/**
	 * @return Gibt den zuletzt hinzugefügten Column zurück
	 */
	public Colum getLastColum(){
		return colum.get(this.colum.size()-1);
	}
	/**
	 * @param name  -name des Columns wercher überprüft werden soll.
	 * @return	Gibt true zurück fals dieses column neu ist.
	 * Überprüft ob es sich nicht um ein bereits vorhandenes column handelt.
	 */
	public boolean isNewColum(String name){
		for(int i=0;i<this.colum.size();i++){
			if(name.equals(colum.get(i).getName())){
				return false;
			}
		}
		return true;
	}
	/**
	 * @return Gibt die tabelle im dot Format zurück.
	 */
	public String dotFormat(){
		this.addacReft();
		String ret=this.name+" [shape=box];";
		for(int i=0;i<this.colum.size();i++){
			String label=" ";
			if(colum.get(i).isItPk()&&colum.get(i).isItFk()){
				if(colum.get(i).isItNab()){
					label=" [label=<<i><u>"+colum.get(i).getName()+" :NOT NULL</u></i>>";
				}else{
					label=" [label=<<i><u>"+colum.get(i).getName()+"</u></i>>";
				}
			}else{
				if(colum.get(i).isItPk()){
					if(colum.get(i).isItNab()){
						label=" [label=<<u>"+colum.get(i).getName()+" :NOT NULL</u>>";
					}else{
						label=" [label=<<u>"+colum.get(i).getName()+"</u>>";
					}
				}
				if(colum.get(i).isItFk()){
					if(colum.get(i).isItNab()){
						label=" [label=<<i>"+colum.get(i).getName()+": NOT NULL</i>>";
					}else{
						label=" [label=<<i>"+colum.get(i).getName()+"</i>>";
					}
				}
				if((colum.get(i).isItPk()==false&&(colum.get(i).isItFk()==false))){
					label=" [label="+colum.get(i).getName();
				}
			}
			ret=ret+colum.get(i).getName()+i+this.name+label+"];";
			ret=ret+this.name+"--"+colum.get(i).getName()+i+this.name+";";
		}
		ret=ret+"\n";
		return ret;
	}
	/**
	 * @param i der wievielte column.
	 * @return Gibt den Column an der stelle i zurück.
	 */
	public Colum getColum(int i){
		return colum.get(i);
	}
	/**
	 * @param name -name des columns
	 * @return Gibt den Column mit dem namen name zurück
	 */
	public Colum getColum(String name){
		for(int i=0;i<this.colum.size();i++){
			if(name.equals(colum.get(i).getName())){
				return colum.get(i);
			}
		}
		return null;
	}
	/**
	 * @return Gibt die anzahl der columns zurück.
	 */
	public int getColumSize(){
		return colum.size();
	}
}
