package Soni;



/**
 * @author Adi
 *
 */
public class Colum {
	private String name=null;
	private boolean ispk=false;
	private boolean isfk=false;
	private String real=null;
	private String realt=null;
	public Colum(String name) {
		this.name=name;
	}
	/**
	 * @return gibt den Namen des Column zur�ck
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return gibt fals es sich um einen Fremdschl�ssel handelt die dazu geh�rige Table und colum zur�ck im format tabelenname.columnname ansonsten null
	 * Nur f�r das rm gedacht
	 */
	public String getReal() {
		return this.real;
	}
	/**
	 * @return gibt fals es sich um einen Fremdschl�ssel handelt die dazu geh�rige Table zur�ck ansonsten null
	 */
	public String getRealt() {
		return this.realt;
	}
	/**
	 * @return gibt true zur�ck fals es ein primerschl�ssel ist sonst false
	 */
	public boolean isItPk() {
		return ispk;
	}
	/**
	 * Kennzeichnet diesen column fals es sich um einen PK handelt durch ein boolean
	 */
	public void isPk() {
		this.ispk = true;
	}
	/**
	 * @return gibt true zur�ck fals es ein Fremdschl�sel ist sonst false
	 */
	public boolean isItFk() {
		return isfk;
	}
	/**
	 * Kennzeichnet diesen column fals es sich um einen FK handelt durch ein boolean und die dazugeh�rigen Tabelen und column name
	 */
	public void isFk(String real,String realt) {
		this.isfk = true;
		this.real=real;
		this.realt=realt;
	}
	/**
	 * @return Gibt das Colum in einem rm Format zur�ck
	 * Wandel alle eingegeben Daten in ein Rm format f�r das column zur�ck
	 */
	public String rmFormat(){
		String ret=" ";
		if(this.isfk&&this.ispk){
			ret=ret+"<pk><fk>"+this.real+"</fk></pk>,";
		}else{
			if(this.ispk){
				ret=ret+"<pk>"+this.name+"</pk>,";
			}
			if(this.isfk){
				ret=ret+"<fk>"+this.real+"</fk>,";
			}
		}
		if(this.isfk==false&&this.ispk==false){
			ret=ret+this.name+",";
		}
		return ret;
	}
}
