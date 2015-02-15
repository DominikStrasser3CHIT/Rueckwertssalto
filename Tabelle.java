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
	 * @param reft = Ein Tabelen name mit den eine Beziehung eingegangen werden soll.
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
	public void addacReft(){
		for(int i=0;i<this.colum.size();i++){
			if(this.colum.get(i).getRealt()!=null){
				if(this.isNewreft(this.colum.get(i).getRealt())){
					this.reft.add(this.colum.get(i).getRealt());
				}
			}
		}
	}
	public Tabelle(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void  addColum(String a){
		this.colum.add(new Colum(a));
	}
	public String rmFormat(){
		String ret=this.name+"(";
		for(int i=0;i<this.colum.size();i++){
			ret=ret+colum.get(i).rmFormat();
		}
		ret=ret+");"+"\n";
		return ret;
	}
	public Colum getLastColum(){
		return colum.get(this.colum.size()-1);
	}
	public boolean isNewColum(String name){
		for(int i=0;i<this.colum.size();i++){
			if(name.equals(colum.get(i).getName())){
				return false;
			}
		}
		return true;
	}
	public String dotFormat(int l){
		this.addacReft();
		String ret=this.name+" [shape=box];";
		for(int i=0;i<this.colum.size();i++){
			String label=" ";
			if(colum.get(i).isItPk()&&colum.get(i).isItFk()){
				label=" [label=<<i><u>"+colum.get(i).getName()+"</u></i>>];";
			}else{
				if(colum.get(i).isItPk()){
					label=" [label=<<u>"+colum.get(i).getName()+"</u>>];";
				}
				if(colum.get(i).isItFk()){
					label=" [label=<<i>"+colum.get(i).getName()+"</i>>];";
				}
				if((colum.get(i).isItPk()==false&&(colum.get(i).isItFk()==false))){
					label=" [label="+colum.get(i).getName()+"];";
				}
			}
			ret=ret+colum.get(i).getName()+i+l+label;
			ret=ret+this.name+"--"+colum.get(i).getName()+i+l+";";
		}
		ret=ret+"\n";
		return ret;
	}
	public Colum getColum(int i){
		return colum.get(i);
	}
	public Colum getColum(String name){
		for(int i=0;i<this.colum.size();i++){
			if(name.equals(colum.get(i).getName())){
				return colum.get(i);
			}
		}
		return null;
	}
	public int getColumSize(){
		return colum.size();
	}
}
