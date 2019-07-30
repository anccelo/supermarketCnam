package services;
import java.io.Serializable;

import IPane.*;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;
import mesInterfaces.InterfaceGestion;



public class GestionUneCommande implements InterfaceGestion<UneCommande<String>, TableArticles, TableDesFactures> {
	
	private ES es = new ES();
	
	public void menuGeneral(UneCommande<String> cmd, TableArticles tabArt,TableDesFactures tanNull)throws Exception{} // necesarily for cntraint of InterfaceGestion
	public  int menuChoix (UneCommande<String> cmd, TableArticles tabArt)throws Exception{return 0;}
	
	public  void menuGeneral(UneCommande<String> cmd, TableArticles tabArt, String nCde) throws ErreurSaisieException, AbandonException {
		int choixCommande;
		do {
			 choixCommande =  menuChoix (nCde);
			switch (choixCommande) {
				case 1: creer(cmd, tabArt,null); break;//creer Une LDC
				case 2: afficher(cmd); break;
				case 3: modifier(cmd,tabArt);break;
				case 4: facturer(cmd, tabArt); break;
				
			}
		} while (choixCommande != 0);
	}

	private  int menuChoix (String numeroCde) throws ErreurSaisieException, AbandonException{
		String menuCommande = "\t      GESTION DE LA COMMANDE  NUMERO : " + numeroCde + "  \n\n" + 
				  "\t SAISIR UNE LIGNE DE COMMANDE ........... 1\n" +
				  "\t AFFICHER la COMMANDE en COURS .......... 2\n" +
				  "\t MODIFIER UNE LIGNE DE COMMANDE.......... 3\n" +
				  "\t EDITER la FACTURE....................... 4\n" +
	              "\t FIN .................................... 0\n" + 
				  "\t\t\t Choix: .......... ";		
		
		return es.lire(menuCommande,0,4);
	}
	
	//del prof 
	public void creer(UneCommande<String> cde ,TableArticles tabArt, TableDesFactures tabNull) throws ErreurSaisieException, AbandonException{
		LigneDeCommande ldc = saisie(cde,tabArt);
		if  (ldc != null) cde.ajouter(ldc);
		else es.affiche("\n *** CODE ARTICLE NON EXISTANT  ou DEJA COMMANDE' ***\n");
		}
	
	private LigneDeCommande saisie(UneCommande<String> cde , TableArticles tabArt) throws ErreurSaisieException, AbandonException{
		int code = es.lire("rentré le code de l'article à ajouterr", 1, Integer.MAX_VALUE);
		if(tabArt.retourner(code) == null ) return null ;//le code n'existe pas dans le catalogue
		if(cde.retourner(code)!=null) return null; // le code est deja presente dans a commande 
		if( tabArt.retourner(code) instanceof ArticlePromo){
			//ici on cast pour obtenir les methodes de ArticlePromo
			es.affiche("Cet article est en promotion!! Quantite minimum:" + ((ArticlePromo)tabArt.retourner(code)).getQuantiteMini());
		}
		int quantite = es.lire("rentré la quantité de l'article à commander", 1, Integer.MAX_VALUE);
		return new LigneDeCommande(code,quantite);
	}
	
	public void modifier(UneCommande<String> cmd ,TableArticles tabArt) throws AbandonException, ErreurSaisieException{
		es.affiche(cmd.toString());
		int code = es.lire("rentré le code de l'article corridpondent à la ligne de commande à supprimer", 1, Integer.MAX_VALUE);
		cmd.purge(code);
	}
	
	public void afficher(UneCommande<String> cmd){
		es.affiche(cmd.toString());
	}
	
	public void supprimer(UneCommande<String> cmd ,TableArticles tabArt) throws AbandonException, ErreurSaisieException{
		int code = es.lire("rentre le numero de commande à effacer", 0, Integer.MAX_VALUE);
		cmd.supprimer(code);
	}
	
	//del prof 
	private  void facturer(UneCommande<String> cmd, TableArticles tabArt){
		if(cmd.taille() == 0 )
			es.affiche(" ****** COMMANDE VIDE PAS DE FACTURE ASSOCIE'\n ");
		else 
			es.affiche(cmd.facturer(tabArt));
	}

	
	
	
	
}
