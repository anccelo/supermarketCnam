package services;

import java.io.Serializable;
import java.util.TreeMap;

import mesInterfaces.InterfaceStructure;

public class TableDesFactures implements InterfaceStructure<Facture,String>,Serializable{
	
	private TreeMap<String, Facture> tabFct= new TreeMap<String ,Facture>();
	
//*******************************	CONSTRUCTORS	*******************************

	public TableDesFactures() {}
	
	public TableDesFactures(TreeMap<String, Facture> tabFct) {
		this.tabFct = tabFct;
	}
	

//***********************	GETTERS, SETTERS AND ToSTRING	************************
	
	public TreeMap<String, Facture> getTabFct() {
		return tabFct;
	}

	public void setTabFct(TreeMap<String, Facture> tabFct) {
		this.tabFct = tabFct;
	}

//***************************	"OBLIGATORY" METHODS	**************************	
	public int taille() {
		return tabFct.size();
	}

	

	public Facture retourner(String nmFacture) {
		return tabFct.get(nmFacture);
	}

	public void supprimer(String nmFacture) {
		tabFct.remove(nmFacture);
		
	}

	public void ajouter(Facture facture) {
		tabFct.put(facture.nbFacture, facture);
	} 
	
//***********************  OTHER METHODS ****************************************
	
	public String cle(){
		String st="\n **** LISTE DES NUMEROS DE FACTURES   ***\n";
				for (String num : tabFct.keySet()){
					st = st + "** " + num ;
				}
				return st;
	}

}
