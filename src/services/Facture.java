package services;

import java.io.Serializable;

import utils.DateUser;

public class Facture implements Serializable {
	
	public  String nbFacture;
	public DateUser dateFacturation;
	public String corpFacture;
	
//*******************************	CONSTRUCTORS	*******************************	
	
	public Facture (){}
	
	public Facture (String nbFacture, DateUser dateFacturation,String corpFacture){
		this.nbFacture = nbFacture;
		this.dateFacturation = dateFacturation;
		this.corpFacture = corpFacture;	
	}

	
	
//***********************	GETTERS, SETTERS AND ToSTRING	************************

	public String getNbFacture() {
		return nbFacture;
	}

	public void setNbFacture(String nbFacture) {
		this.nbFacture = nbFacture;
	}

	public DateUser getDateFacturation() {
		return dateFacturation;
	}

	public void setDateFacturation(DateUser dateFacturation) {
		this.dateFacturation = dateFacturation;
	}

	public String getFacture() {
		return corpFacture;
	}

	public void setFacture(String corpFacture) {
		this.corpFacture = corpFacture;
	}
	
	public String toString(){
		return corpFacture ;
	}

	
//*****************************		SPECIFIC METHODS	***************************
	
	public   DateUser datePossiblePourSuppression(){
		DateUser datePourSuppression =this.dateFacturation;
		int conteur = 0;
		while(conteur <= 7){
			datePourSuppression = datePourSuppression.lendemain1();
			conteur++;
		}
		return datePourSuppression;
	}
}
