package services;
import java.io.*;

public abstract class ArticleAbstraite  implements Serializable {
	
	protected int code;
	protected String designation ;
	protected float pu;
	
//*******************************	CONSTRUCTORS	*******************************
	
	public ArticleAbstraite (){}
	
	public ArticleAbstraite ( int code , String designation, float pu){
		
		this.code = code;
		this.designation= designation;
		this.pu =(float) (pu*(100/100.00));
		}

//***********************	GETTERS, SETTERS AND ToSTRING	************************
	
	public int  getCode(){
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public float getPu() {
		return pu;
	}

	public void setPu(float pu) {
		this.pu = pu;
	}
	
	
	public  abstract String toString();

//*****************************		SPECIFIC METHODS	***************************
	
	public abstract float prixFacture(int quantinte);
	
	public abstract  String facturer( int quantite);
}
