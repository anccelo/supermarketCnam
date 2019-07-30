package services;

import java.io.Serializable;
import java.util.*;

import mesInterfaces.InterfaceStructure;
import utils.DateUser;

public class UneCommande<TypeNumeroCommande> implements InterfaceStructure<LigneDeCommande, Integer>, Serializable {

	private Vector <LigneDeCommande> commande = new Vector <LigneDeCommande>();
	
	private DateUser dateCde   = new DateUser();///on à la date du jour//ajouté dans cette serie22
	private TypeNumeroCommande numCde; //commande number 
	private boolean facturee = false; // invoiced or not invoiced  (fatturato o  non fatturato) 
	private DateUser dateFacturation = null;
	
	private double TVA = 19.6/100;
	
//*******************************	CONSTRUCTORS	*******************************
	
	public UneCommande() {
		
	}
	
	public  UneCommande (DateUser dat , TypeNumeroCommande numCde , Vector<LigneDeCommande> cde ){
		this.dateCde = dat;
		this.numCde = numCde;
		this.commande = cde;
		
		
	}
	
	

//***********************	GETTERS, SETTERS AND ToSTRING	************************
	
	public Vector<LigneDeCommande> getCommande() {
		return commande;
	}

	public void setCommande(Vector<LigneDeCommande> commande) {
		this.commande = commande;
	}
	
	public TypeNumeroCommande getNumeroCommande(){
		return numCde;
	}
	
	public DateUser getDateCde() {
		return dateCde;
	}

	public void setDateCde(DateUser dateCde) {
		this.dateCde = dateCde;
	}

	public void setNumeroCommande(TypeNumeroCommande num){
		numCde = num;
	}
	
	public boolean isFacturee() {
		return facturee;
	}

	public void setFacturee(boolean facturee) {
		this.facturee = facturee;
	}

	public DateUser getDateFacturation() {
		return dateFacturation;
	}

	public void setDateFacturation(DateUser dateFacturation) {
		this.dateFacturation = dateFacturation;
	}

	public String toString(){
		
		String str = "\nCONTENU DE LA  COMMANDE NUMERO: '"+ numCde+"'\n";
		str= str + "date commande :" + dateCde + " \n\n";
		for(LigneDeCommande ldc: commande )
		{
			str =  str + ldc.toString() + "\n";
		}
		return str;
	}
	
//***************************	"OBLIGATORY" METHODS	**************************
	
	
	public int taille(){
		return  commande.size();
		
	}
	
	public void ajouter(LigneDeCommande ldc){
		commande.addElement(ldc);
	}
	
	public LigneDeCommande retourner(Integer code){
		for (LigneDeCommande ldc : commande ){
			if (ldc.getCode() == code )
				return ldc;
		}
		return null; 
	}
	
	public void  supprimer(Integer code){
		for (LigneDeCommande ldc : commande ){
			if(ldc.getCode() == code )
				commande.remove(code);
		}
	}
	
	
	
//*****************************		SPECIFIC METHODS	***************************
	
	
	public  String facturer(TableArticles tabArt){
		
		String entete = "    *********   FACTURE COMMANDE N° " + numCde +"   *******\n"+
						"    du: "+ dateCde +"\n"+
						"	  etat facture:"+ this.facturee +"\n"+
						"    date de facturation: " + this.dateFacturation +"\n\n"+ 
						"CODE 		 DESIGNATION 		 QUANTITE  		PU(HT) 		 TOTALE(HT)  \n "+ 
						"_____________________________________________________________________________________\n";
		
		String detail="";
		float prixTotaleCommandeHT=(float) (0*100/100.00);
		for(LigneDeCommande ldc: commande){
			ArticleAbstraite art = tabArt.retourner(ldc.getCode());
			detail= detail + ldc.facturer(tabArt)+ "";
			prixTotaleCommandeHT = prixTotaleCommandeHT + ldc.prixFacture(art);//((ArticlePromo41)tabArt.retourner(code))
		}
		
		String pied="_____________________________________________________________________________________\n"+ 
				  "			 	 ***** TOTALE HORS TAXE: " + prixTotaleCommandeHT 
				+ "\n			 ***** TVA (19.6):       " + (int)(prixTotaleCommandeHT*TVA*100)/100.00 // methodo per avere solo 2 cifre decimali
				+ "\n			 ***** TOTALE TOUT TAXE: " + (int)(prixTotaleCommandeHT*100 + prixTotaleCommandeHT*TVA*100)/100.00+ "\n\n";
		
		return entete + detail + pied; 
		
	}
	
	
	 public void purge( int code){
		//for (UneCommande23 ldc :commande)
		 for(LigneDeCommande ldc :  commande){
			if (ldc.getCode() == code) {
				commande.remove(ldc);
				break;	
			}
		}
	 }
	 
	
}
