package services;

import IPane.ES;
import connexionFichier.Connexion;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;
import mesInterfaces.InterfaceGestion;
import utils.DateUser;



public class GestionTableDesCommande implements InterfaceGestion< TableDesCommandes, TableArticles, TableDesFactures> {
	
	private ES es = new ES();
	Connexion<TableDesCommandes > tabConnectee ;
	
	public GestionTableDesCommande(String nomPhisique){
    	tabConnectee = new Connexion(nomPhisique);
    }
	
	 public TableDesCommandes recuperer(){
    	 TableDesCommandes tabCmd = tabConnectee.lire();
    	 if(tabCmd == null ){
    		 es.affiche("  FICHIER NOUVEAU *** TABLEDESCOMMANDE PAR DEFAULT **\n");
    		 tabCmd = new TableDesCommandes();
    	 } 
    	 return tabCmd;
     }
	 
	 public void  sauvegarder(TableDesCommandes tabCmd){//non sono sicuro
    	 tabConnectee.ecrire(tabCmd);
     }

	public  void menuGeneral(TableDesCommandes tabCmd, TableArticles tabArt, TableDesFactures tabNull) throws ErreurSaisieException, AbandonException{//prof 22/0/17
		int choix ;
		do{
			choix = menuChoix(tabCmd, tabArt);
			switch (choix){
			case 1: creer(tabCmd, tabArt, null); break;
			//case 2 :modifier(tabCmd, tabArt); break;
			case 3: supprimer(tabCmd, tabArt); break;
			case 4 :afficher( tabCmd); break;
			case 5 : afficherToutesLesCommandes(tabCmd); break;
			case 6 : facturerLesCommandes(tabCmd, tabArt); break;
			
			case 0: break;
			} 
		}while (choix !=0);
	}
	
	public  int menuChoix(TableDesCommandes tabCmd, TableArticles tabArt) throws AbandonException, ErreurSaisieException{ ///prof 22/03
		String menu="\t    GESTION DES COMMANDES  \n\n" 
				 + "\t GESTION DE LA COMMANDE NUMERO '"+ nmCommandeaCreer(tabCmd)+ "'...1\n"
			     + "\t MODIFIER UNE COMMANDE(suspendu).....2 \n"
			     + "\t SUPPRIMER UNE COMMANDE .............3 \n"
			     + "\t AFFICHER UNE COMMANDE...............4 \n"
			     + "\t AFFICHER LES COMMANDE ..............5 \n"
			     + "\t EDITER LA FACTURER D'UNE COMMANDE...6 \n" 
			     + "\t FIN.................................0 \n"
			     + "\t\t\t VOTRE CHOIX:\n";
	return es.lire(menu,0,6);
	}
	
	// reality follow method is commandes's gestione
	public  void creer(TableDesCommandes tabCmd, TableArticles tabArt,TableDesFactures tabNull) throws  AbandonException, ErreurSaisieException{
		UneCommande<String> cde =  new UneCommande<String>();
		int numero =  premierNumeroDisponible(tabCmd);
		cde.setNumeroCommande(""+cde.getDateCde().getAnnee()+cde.getDateCde().getMois()+cde.getDateCde().getJour()+numero);
		
		GestionUneCommande guc = new GestionUneCommande();
		guc.menuGeneral(cde ,tabArt,cde.getNumeroCommande());
		if(cde.taille() != 0 ){
			tabCmd.ajouter(cde);
		}
	}
	
	public String nmCommandeaCreer( TableDesCommandes tabCmd) throws AbandonException, ErreurSaisieException{
		int numero =  premierNumeroDisponible(tabCmd);
		DateUser du = new DateUser();
		return ""+du.getAnnee()+du.getMois()+du.getJour()+numero;
	}
	public void modifier(TableDesCommandes tabCmd, TableArticles tabArt) throws AbandonException, ErreurSaisieException{}
	/*
	public void modifier(TableDesCommandes52 tabCmd, TableArticles52 tabArt) throws AbandonException, ErreurSaisieException{
		if (tabCmd.taille() == 0){
			es.affiche("pas de commande en course\n");
		}else {
			//String choix = es.lire(tabCmd.cle()+"\nVous voulez modifier quelle commande?",1 ,tabCmd.taille());
			String choix = es.lire("\nVous voulez modifier quelle commande?"+tabCmd.cle());
		
			//String code = es.lire(tabCmd.retourner(choix).toString()+"\nrentré le code de l'article corridpondent à la ligne de commande à supprimer", 1, Integer.MAX_VALUE);
			tabCmd.retourner(choix).purge(choix);
		}
	}*/
	
	//ajouter en parametre le numero de commande
	public void  supprimer(TableDesCommandes tabCmd, TableArticles tabArt) throws  AbandonException, ErreurSaisieException{
		if (tabCmd.taille() == 0){
			es.affiche("pas de commande en course\n");
		}else {
			try{
			String choix = es.lire("\nVous voulez supprimer quelle commande?"+tabCmd.cle());
			if(tabCmd.getTabCde().containsKey(choix)){
				if(tabCmd.getTabCde().get(choix).isFacturee()){
					es.affiche("vous pouvez pas supprimer une commande deja facturée");
				}
				else{
					tabCmd.supprimer(choix);
					es.affiche( "Vous avez suprimez la commande  numéro "+choix);
				}
			}else{
				es.affiche("la commande nb. " + choix + " n'existe pas.\n\n");
			}
			}catch (NullPointerException npe){	}
		}
	}
	
	public void afficher(TableDesCommandes tabCmd) throws AbandonException, ErreurSaisieException
	
		{
		try{
			if (tabCmd.taille() == 0){
				es.affiche("pas de commande en course\n");
			}else {
				String choix = es.lire("\nVous voulez afficher quelle commande?"+tabCmd.cle());
				//int choix = es.lire("Vous voulez afficher quelle commande?",1 ,tabCmd.taille());
				es.affiche(tabCmd.retourner(choix).toString());
			}
		}catch (NullPointerException npe){}
	}
	
	private  void afficherToutesLesCommandes(TableDesCommandes tabCmd){
		if(tabCmd.taille() == 0){
			es.affiche("\n *** AUCUNE COMMANDE EN COURS**** \n");
		}else{
			es.affiche("\n ***les commande en cours ****\n"+ tabCmd.toString());
		}
	}
	

	private  void facturerLesCommandes(TableDesCommandes tabCmd , TableArticles tabArt) throws NullPointerException, AbandonException, ErreurSaisieException{
		if(tabCmd.taille() == 0){
			es.affiche("\n *** AUCUNE COMMANDE A FACTUER**** \n");
		}else {
			try{
			String choix1 = es.lire(tabCmd.cle()+"\nVous voulez éditer la FACTURE de quelle commande?\n\n"
					+ "(attention editer une facture ne change pas l'etat de la commande en facturée.\n"
					+ "Pour cette operatione retoruné dans le menu principale e faitte le choix numero 3) ");
			es.affiche(tabCmd.getTabCde().get(choix1).facturer(tabArt));
			}catch (NullPointerException npe){
				es.affiche("rentrer un valeur correcte comme numero de commande");
			}
		}
	}
	
	private  int premierNumeroDisponible(TableDesCommandes tabCmd) throws AbandonException, ErreurSaisieException{
		int conteur=1;
		DateUser dateCde   = new DateUser();
		String numCommande = " "+dateCde.getAnnee()+dateCde.getMois()+dateCde.getJour();
		for (String cle : tabCmd.getTabCde().keySet()){
			String dataCle =cle.substring(0,cle.length()-1);
			String dataNumCommande = numCommande.substring(1, numCommande.length());
			if(dataCle.equals(dataNumCommande) ){
				 conteur++;
			}
		}
		return conteur;

	}
	
	
	
	
}
