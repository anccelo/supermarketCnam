package services;

import IPane.*;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;

public class Client {
	private static ES es = new ES();

	public static void main(String[] args) {

		GestionTableDesArticles gta = new GestionTableDesArticles("TABLE_ARTICLES.data");
		TableArticles tabArt = gta.recuperer(); //recovering data from the articles table
		
		GestionTableDesCommande gtc = new GestionTableDesCommande("TABLE_DES_COMMANDES.data");
		TableDesCommandes tabCmd = gtc.recuperer();
		
		GestionTableDesFactures gtf = new GestionTableDesFactures("TABLE_DES_FACTURES.data");
		TableDesFactures tabFct = gtf.recuperer();
		
		int choix;
		do {
			try {
				choix = menuChoix();
				switch (choix) {
				case 1: gta.menuGeneral(tabArt, tabCmd,null); break;
				case 2: gtc.menuGeneral(tabCmd, tabArt,null); break;
				case 3: gtf.menuGeneral(tabFct, tabCmd, tabArt); break;
				}
			}catch (ErreurSaisieException e) {
				choix = 0;
			}catch (AbandonException ae){
				choix = 0;
			}
			
		} while (choix != 0);
		es.affiche(" *** SAUVEGARDE DES FICHIERS  EN COURS ****\n");
		gta.sauvegarder(tabArt);// data backup
		gtc.sauvegarder(tabCmd);
		gtf.sauvegarder(tabFct);
		es.affiche("AU REVOIR!");
	}
	// ***********************************************************************************************

	public static int menuChoix() throws AbandonException, ErreurSaisieException {//questo throws vuol dire che nn tratto l'eccezione ma la propago
																//al metodo che ha chiamato questo
		int choix;
		String menu = "    BIENVENU  SUR SUPERETTE \n\n" 
					+ " GESTION DES ARTICLES.........1\n"
					+ " GESTION DES COMMANDE ........2\n" 
					+ " GESTION DES FACTURES ........3\n" 
					+ " FIN..........................0\n"
					+ "                     VOTRE CHOIX:\n " ;
					
		choix = es.lire(menu, 0, 3);
		return choix;
	}

	
}


