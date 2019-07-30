package services;

import java.io.Serializable;
import java.util.*;

import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;
import mesInterfaces.InterfaceStructure;

public class TableDesCommandes implements InterfaceStructure<UneCommande<String>, String>, Serializable {
	//public int numeroCommande =1; no more need after change numCde to Interger to String
	//the key is "numCde" of UneCommande22
	private Hashtable<String, UneCommande<String>> tabCde =
			new Hashtable<String ,UneCommande<String>>();   
			//é come una TreeMap

//*******************************	CONSTRUCTORS	*******************************
	
	public TableDesCommandes(){};
	
	public TableDesCommandes(Hashtable<String,UneCommande<String>> tabCde){
		this.tabCde=tabCde;
		
	}

//************************	GETTERS, SETTERS AND ToSTRING	***********************
	
	public Hashtable<String, UneCommande<String>> getTabCde() throws  AbandonException, ErreurSaisieException  {
		return tabCde;
	}

	public void setTabCde(Hashtable<String, UneCommande<String>> tabCde) {
		this.tabCde = tabCde;
	}
	
	public String toString(){
		String st= " \n**** LISTE DES COMMANDE  EN COURS *****\n";
		for(UneCommande<String> cde : tabCde.values()){
			st = st + cde.toString();
		}
		return st;
	}
	
//***************************	"OBLIGATORY" METHODS	**************************
	public int taille(){
		return tabCde.size();
	}
	
	public UneCommande<String> retourner(String numeroCde){
		return tabCde.get(numeroCde);
	}
	
	public void ajouter(UneCommande<String> cde){
		if(!tabCde.containsKey(cde.getNumeroCommande())){//si la commande n'est pas presente
			tabCde.put(cde.getNumeroCommande(), cde);
		}
	}
		
	public void supprimer(String numCde) throws  AbandonException, ErreurSaisieException  {
		try{
			tabCde.remove(numCde);
		}catch (NullPointerException npe){
			
		}
		}
	
//*****************************		SPECIFIC METHODS	***************************
	
	
	public String cle(){
		String st="\n **** LISTE DES NUMEROS DE COMMANDES   ***\n";
				for (String num : tabCde.keySet()){
					st = st + "** " + num ;
				}
				return st;
	}
	
	public String numCmdPasFacturee ()  {
		String st = "\n \t\t*** VOICI LA LISTE DES COMMANDE PAS ANCORE FACTUREE ***\n\n";
		boolean commandesAFacturer = false;
		for (UneCommande<String> cmd : tabCde.values()){
			if(!cmd.isFacturee()){
				st = st + cmd.getNumeroCommande()+"\n";
				commandesAFacturer = true;
			}
		}
		if( !commandesAFacturer) return null;
		else return st;
}
	

	public  void purge(int code){
		for (UneCommande<String> cde :  tabCde.values()){
			cde.purge(code);
		}
	}

}
