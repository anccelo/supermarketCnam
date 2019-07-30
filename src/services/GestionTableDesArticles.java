package services;

import IPane.*;
import connexionFichier.*;
import mesExceptions.*;
import mesInterfaces.InterfaceGestion;


public class GestionTableDesArticles implements InterfaceGestion<TableArticles, TableDesCommandes,TableDesFactures > {
	private ES es = new ES();
	private Connexion<TableArticles > tabConnectee ;

//************************************  CONSTRUCTOR  ************************************************
	
    public GestionTableDesArticles(String nomPhisique){
    	tabConnectee = new Connexion(nomPhisique);
    }
    
 //************************************ METHODES  ************************************************   
     public TableArticles recuperer(){
    	 TableArticles tabArt = tabConnectee.lire();
    	 if(tabArt == null ){
    		 es.affiche("  FICHIER NOUVEAU *** TABLEARTCILES PAR DEFAULT **\n");
    		 tabArt = new TableArticles();
    	 } 
    	 return tabArt;
     }
     

     public void  sauvegarder(TableArticles tabArt){//non sono sicuro
    	 tabConnectee.ecrire(tabArt);
     }
	
     
	public void menuGeneral (TableArticles tabArt, TableDesCommandes tabCmd, TableDesFactures  tabNull)  throws  AbandonException, ErreurSaisieException
	{
		int choix;
		do {
			//try{
			choix = menuChoix(tabArt, tabCmd);
			switch (choix) {
				case 1:creer(tabArt, tabCmd,null); break;
				case 2:supprimer(tabArt, tabCmd); break;
				case 3:modifier (tabArt, tabCmd); break;
				case 4: afficher(tabArt); break;
				case 5: afficherPromo(tabArt); break;
				case 0: break;
			}
			//}catch (ErreurSaisieException er){
				//es.affiche("\n ABBANDON GESTION TABLES DES ARTICLES \n");
				//choix =0;
			//}
		}while (choix !=0);	
	}
						
	public  int menuChoix(TableArticles tabArt, TableDesCommandes tabCmd) throws  AbandonException, ErreurSaisieException{//prof 
		 String menuArticles = "\t    GESTION table DES ARTICLES \n\n"
				    + "\t CREER ARTICLE.................1\n"
					+ "\t ELIMINER ARTICLE .............2\n"
					+ "\t MODIFIER ARTICLE  ............3\n"
					+ "\t AFFICHER......................4\n"
					+ "\t AFFICHER ARTICLES EN PROMO   .5\n"
					+ "\t FIN...........................0\n"
					+ "\t\t\t VOTRE CHOIX:\n\n " ;
					
		 
		 return (es.lire(menuArticles, 0,5));
		}
		
	public void  afficher(TableArticles tabArt)  {//prof 
		if(tabArt.taille()== 0 ) 
			es.affiche("***  AUCUNE ARTICLE PRESENT DANS LE CATALOGUE  ****");
		else  
			es.affiche(tabArt.toString());	
	}
	
	public void creer (TableArticles tabArt, TableDesCommandes tabCmd,TableDesFactures tabNull) throws  AbandonException, ErreurSaisieException{//prof
		ArticleAbstraite art = saisie(tabArt);
		if(art != null){
			tabArt.ajouter(art);
		}
	}
	
	
	private  ArticleAbstraite saisie(TableArticles tabArt) throws AbandonException, ErreurSaisieException{
		int code = es.lire("  entré le code   " , 1 ,Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) == null){
			String designation  = es.lire("designation:");
			float prix = es.lire("prix unitaire? ", 0f, Float.MAX_VALUE);
			
			if(es.saisieOuiNon("PROMOTION? (O/N)")){
				int quantinteMini = es.lire("quantinté minimum",1, Integer.MAX_VALUE);
				float reduc= es.lire("reduction?" , 1f , 100f);
				return   new ArticlePromo(code,  designation, prix , quantinteMini, reduc);
			}
			
			/*ici je voudrait introduire un exceptione qui est soullavée dans l'eventualitée que le prix entroduit est
			 * un numero negative,er apres message rivien à la saisie su prix*/
			return new Article(code, designation , prix);
		}
		
		else {
			es.affiche("ARTICLE DE CODE " + code + " EXISTE DEJA! \n\n");
			return null;
		}
	}
	
	
	
	public   void modifier(TableArticles tabArt, TableDesCommandes tabCmd) throws  AbandonException, ErreurSaisieException{//angelo
		//es.affiche(tabArt.cles());
		int choix = es.lire(tabArt.cles() + "rentré le code de l'article à modifier", 1, Integer.MAX_VALUE );
		ArticleAbstraite art = tabArt.retourner(choix);
		if (art==null){
			es.affiche("Ce code n'est pas presente dans le catalogue");
		}else{
			es.affiche(art.toString());
			boolean  choixModifDesign = es.saisieOuiNon("voulez vous modifier la designation (O/N)");
			if (choixModifDesign) {
				String nouvelleDesignation = es.lire("Rentrez la nouvelle designation :");
				art.setDesignation(nouvelleDesignation);
			}
			boolean choixModifPrix = es.saisieOuiNon("voulez vous modifier le prix unitaire (O/N)");
			if(choixModifPrix){
				float nouveauxPrix = es.lire("rentrez le nouveaux prix", 0f,Float.MAX_VALUE);
				/*ici je voudrait introduire un exceptione qui est soullavée dans l'eventualitée que le prix entroduit est
				 * un numero negative,et apres message rivien à la saisie su prix*/
				art.setPu(nouveauxPrix);
			}
			if (!choixModifDesign && !choixModifPrix ) 
				es.affiche("vous n'avait apportée aucune modificatione à l'article de code: " + choix + " .\n");            
			else
				es.affiche("\n"+art.toString()+"\nmodificatione de l'article de code " + choix + " completée.\n");
				//es.affiche("modificatione de l'article de code: " + choix + " completée.\n");		             
		}
	}
	
	public  void supprimer(TableArticles tabArt, TableDesCommandes tabCmd) throws  AbandonException, ErreurSaisieException{
		//es.affiche(tabArt.cles());
		int code = es.lire(tabArt.cles()+" rentré le code de l'article à supprimer", 0,Integer.MAX_VALUE);
		if (tabArt.getTabArt().containsKey(code)){ 
			tabArt.supprimer(code,tabCmd);
			es.affiche(" L'ARTICLE DE CODE " + code + " EST SUPPRIME'.\n ");
		}
		else 
			es.affiche("le code " + code + " n'existe pas.\n\n");
	}
	
	
	private void afficherPromo( TableArticles tabArt){
		es.affiche(tabArt.afficherPromo());
	}
}