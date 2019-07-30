package services;

import IPane.ES;
import connexionFichier.Connexion;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;
import mesInterfaces.InterfaceGestion;
import utils.DateUser;

public class GestionTableDesFactures implements InterfaceGestion<TableDesFactures, TableDesCommandes,TableArticles> { 
	
	private ES es = new ES();
	private Connexion<TableDesFactures > tabConnectee ;

//************************************  CONSTRUCTOR  ************************************************
	
    public GestionTableDesFactures(String nomPhisique){
    	tabConnectee = new Connexion(nomPhisique);
    }
 
 //************************************    METHODES  ************************************************   
     
    public TableDesFactures recuperer(){
    	 TableDesFactures tabFact = tabConnectee.lire();
    	 if(tabFact == null ){
    		 es.affiche("  FICHIER NOUVEAU *** TABLE des factures PAR DEFAULT **\n");
    		 tabFact = new TableDesFactures();
    	 } 
    	 return tabFact;
     }
     

     public void  sauvegarder(TableDesFactures tabFact){//non sono sicuro
    	 tabConnectee.ecrire(tabFact);
     }


	
	public void menuGeneral(TableDesFactures tabFct,TableDesCommandes tabCmd, TableArticles tabArt) throws  AbandonException, ErreurSaisieException {
		int choix;
		do {
			//try{
			choix = menuChoix(tabFct, tabCmd);
			switch (choix) {
				case 1: creer(tabFct, tabCmd,tabArt); break;
				case 2: afficher(tabFct); break;
				case 3: supprimer(tabFct, tabCmd); break;
				case 0: break;
			}
			//}catch (ErreurSaisieException er){
				//es.affiche("\n ABBANDON GESTION TABLES DES ARTICLES \n");
				//choix =0;
			//}
		}while (choix !=0);	
		
	}
	
	

	public int menuChoix(TableDesFactures tabFct,TableDesCommandes tabCmd)throws  AbandonException, ErreurSaisieException {
		String menuArticles = "\t    GESTION table DES FACTURES \n\n"
			    + "\t FACTURER UNE COMMANDE.........1\n"
				+ "\t AFFICHER UNE FACTURE..........2\n"
				+ "\t SUPPRIMER UNE FACTURES........3\n"
				+ "\t FIN...........................0\n"
				+ "\t\t\t VOTRE CHOIX:\n\n " ;
				
		return (es.lire(menuArticles, 0,3));
	}
	
	
	public void creer(TableDesFactures tabFct,TableDesCommandes tabCmd,TableArticles tabArt) throws  AbandonException, ErreurSaisieException {
		String numCommandeAfacturer = saisie(tabCmd);
		DateUser dateAjordHui = new DateUser();
		if (numCommandeAfacturer == "")
			es.affiche("il n'ya pas de commandes à  facturer");
		else{
			try{
				UneCommande<String>  cmdEnCours= tabCmd.getTabCde().get(numCommandeAfacturer);
				if (cmdEnCours.isFacturee()){
					es.affiche("la commande que vous avez saisie est deja facturé");
					ErreurSaisieException ese =  new  ErreurSaisieException();
				}
				else{
					cmdEnCours.setDateFacturation(dateAjordHui);
					cmdEnCours.setFacturee(true);
					Facture facture = new Facture(numCommandeAfacturer, dateAjordHui, cmdEnCours.facturer(tabArt));
					tabFct.ajouter(facture);
					es.affiche("la commande n°: " + numCommandeAfacturer + " a eté facturée et vous pouvez la consulter dans 'afficher une facture' ");
				}
			}catch (NullPointerException er){ es.affiche("vous avez pas saisie la bonne commande");
			}catch (Exception  er) {es.affiche("il y a un souci"); }
		}	
	}
	
	public String saisie(TableDesCommandes tabCmd) throws  AbandonException, ErreurSaisieException {
			if (tabCmd.numCmdPasFacturee() == null ) return "";
			else return es.lire(tabCmd.numCmdPasFacturee() + "rentrez le numero de la commande");	
	}
	
	public void afficher(TableDesFactures tabFct) {
		if(tabFct.taille() == 0 ) 
			es.affiche("***  AUCUNE FACTURE PRESENT   ****");
		else  {
			String choix = es.lire("\nVous voulez afficher quelle facture?"+tabFct.cle());
			try{
			es.affiche(tabFct.retourner(choix).toString());
			}catch (NullPointerException npe){
				es.affiche("n° de facture inesistente");
			}
		}
	}
	
	public void modifier(TableDesFactures tabFct,TableDesCommandes tabCmd) throws  AbandonException, ErreurSaisieException {
		//juste pour l'interface, mais un facture ne peux pas etre modifiée
	}
	
	public void supprimer(TableDesFactures tabFct,TableDesCommandes tabCmd)throws  AbandonException, ErreurSaisieException {
		if(tabFct.taille() == 0 ) 
			es.affiche("***  AUCUNE FACTURE PRESENT   ****");
		else  {
			String choix = es.lire("\nVous voulez supprimer quelle facture?"+tabFct.cle());
			try{
				DateUser today = new DateUser();
				DateUser dateFacturationChoix = tabFct.retourner(choix).dateFacturation;
				if(DateUser.joursEntreDates(dateFacturationChoix,today) > 7){
					tabFct.supprimer(choix); 
					es.affiche("la facture n° " + choix + ", a été supprimée");	
				}
				else es.affiche("la facture n° " + choix + ", poudra etre supprimée seulment  à partire du  "+  tabFct.retourner(choix ).datePossiblePourSuppression() );
			}catch (NullPointerException npe){
				es.affiche("n° de facture inesistente");
			}
		}
		
	}
	
	
	
	
	
}
