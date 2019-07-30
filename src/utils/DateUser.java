package utils;

import java.io.Serializable;
import java.util.Calendar;

public class DateUser implements Serializable{
	
	private int jour, mois, annee;
	
	public DateUser (int jour, int mois, int annee){
		this.jour = jour; this.mois=mois; this.annee = annee;
	}
	
	public DateUser(){
		Calendar calc = Calendar.getInstance();
		jour = calc.get(Calendar.DAY_OF_MONTH);
		mois = calc.get(Calendar.MONTH)+1;
		annee = calc.get(Calendar.YEAR);
	
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String toString(){
		return jour+"/"+mois+"/"+ annee;
	}
	
	public static boolean validDate (int jour, int mois, int annee) {
		return jour <= nMaxMois(mois,annee)&& (mois<=12 || mois >=1);
	}
	public static int nMaxMois(int mois, int annee) {
		switch(mois)
		{
		case 4: case 6: case 9: case 11: return 30;
		case 2: if (bisestile(annee))return 29; else return 28;
		default: return 31;}
	}
	
	public static boolean bisestile(int annee) {
		return ((annee%400)==0) || ((annee%100 !=0) && (annee %4 ==0));
	}
	
	public void hier(){
		jour--;
		if(jour ==0) {
			mois--;
			if(mois==0){
				annee--;
				mois =12;
			}
			jour = nMaxMois(mois, annee);
		}
	}
	
	public DateUser hier1() {
		DateUser hier = new DateUser(jour,mois,annee);
		hier.hier();
		return hier;
	}
	
	public void lendemain() {
		jour++;
		if(jour > nMaxMois(mois, annee)){
			jour =1;
			mois++;
			if(mois >12){ mois =1; annee++;}
		}
	}
	
	public DateUser lendemain1(){
		DateUser dat = new DateUser(jour,mois,annee);
	    dat.lendemain();
	    return dat;
	}
	
	public int zeller() {
		int mz,az,sz, an =annee;
		
		if(!this.avant(new DateUser(15,10,1582))){
		if(mois<3){
			mz=mois +10; an--;
		}
		else mz = mois -2;
		az=an%100;
		sz=(int)(an/100.0);
		int z = ((int)(2.6*mz-0.2)+ jour+ az + (int)(az/4) + (int)(sz/4)-2*sz)%7;
		
	return z;
		}
		else return 99;
		}
	public String jourDeSemaine(){
		return jour(zeller());
	}
	public static String jour(int zeller){
		switch(zeller) {
		case 0: return "dimanche";
		case 1: return "lundi";
		case 2: return "mardi";
		case 3: return "mercredi";
		case 4: return "jeudi";
		case 5: return "vendredi";
		case 6: return "samedi";
		default: return"";
		}
	}
	
	public boolean avant(DateUser d){
		if(this.annee < d.getAnnee()) { return true;}
		else if (this.annee == d.getAnnee()) {
			if(this.mois < d.getMois()) { return true;}
			else if(this.mois == d.getMois()){
				if (this.jour<d.getJour()) {return true;}
				else return false;
			}
			else return false;
		}
		else{return false;}
	}
	public int age(){
		return (new DateUser().getAnnee() - this.annee);
	}
	
	public static int joursEntreDates(DateUser date1 , DateUser date2){
		int conteur=0;
		DateUser datechek = date1;
		if(date1.avant(date2)){
			while(datechek.jour != date2.jour || datechek.mois != date2.mois ||  datechek.annee != date2.annee ){
			conteur++;
			datechek= datechek.lendemain1();
			}	
		}else{
			while (datechek.jour != date2.jour || datechek.mois != date2.mois || datechek.annee != date2.annee){
			conteur--;
			datechek= datechek.hier1();
			}
		}
		return conteur;
	}
	

}
