
package services;

import java.io.Serializable;
import java.util.TreeMap;

import mesInterfaces.InterfaceStructure;


//import serie21.ArticleAbstraite52;

public class TableArticles
implements InterfaceStructure<ArticleAbstraite,Integer>,Serializable{
	
	private TreeMap<Integer, ArticleAbstraite> tabArt= new TreeMap<Integer ,ArticleAbstraite>();
	
//*******************************	CONSTRUCTORS	*******************************
	
	public TableArticles(){
		ArticleAbstraite a1 = new Article(1, "DISQUE DUR", 10.0f);
		ArticleAbstraite a2 = new Article(4, "CARTE MERE", 1000f);
		ArticleAbstraite a3 = new Article(5, "CARTE RESEAU", 10.5f);
		ArticleAbstraite a4 = new Article(12, "BOITE 100CD", 75.5f);
		ArticleAbstraite a5 = new Article(18, "MEMOIRE FLASH", 17f);
		ArticleAbstraite a6 = new ArticlePromo(3,"CLE USB", 10f,5,20f);
		
        
		tabArt.put(a2.getCode(), a2);
		tabArt.put(a1.getCode(), a1);
		tabArt.put(a3.getCode(), a3);
		tabArt.put(a4.getCode(), a4);
		tabArt.put(a5.getCode(), a5);
		tabArt.put(a6.getCode(), a6);
	
	}
	
//***********************	GETTERS, SETTERS AND ToSTRING	************************

	public TreeMap<Integer, ArticleAbstraite> getTabArt() {
		return tabArt;
	}

	public void setTabArt(TreeMap<Integer, ArticleAbstraite> tabArt) {
		this.tabArt = tabArt;
	}
	
	//faire un constructeur avec parametre  (treemap
	
	public String toString(){
		 String st= "\n *** LISTE des ARTICLES EN CATALOGUE **** \n";
		 for(ArticleAbstraite art:tabArt.values()){
			 st=st + art.toString();
		 }
		 return st;
	}
	
//***************************	"OBLIGATORY" METHODS	**************************
	
	public int taille(){
		return tabArt.size();
	}
	
	public ArticleAbstraite retourner(Integer clee_codeArticle){//we use  treeMap method's
		return tabArt.get(clee_codeArticle);
	}
	

	public void ajouter(ArticleAbstraite art){
		if(! tabArt.containsKey(art.getCode())){// if table contain a Article with this CodeArticle(which is the key)
			tabArt.put(art.getCode(), art);
		}
	}
	
	public void supprimer(Integer code ){}//we need of this  for  interface's constraint
	
	public void supprimer(Integer code,TableDesCommandes tabCde){
		tabArt.remove(code);
		tabCde.purge(code);
	}

//*****************************		SPECIFIC METHODS	***************************
	
	public String cles(){//a  "toString" to  print just  the codes's (so the keys) articles
		String st = "\n *** VOICI LA LISTE DES CODES EXISTANTS ***\n\n";
		for (Integer code: tabArt.keySet()){ 
			st = st + "*** " + code+" "; 
		}
		st = st +"***\n";
		return st;
	}
	
	public String afficherPromo(){
		String st = "\n \t\t*** VOICI LA LISTE DES CODES EXISTANTS ***\n\n";
		for (ArticleAbstraite art : tabArt.values()){
			if(art instanceof ArticlePromo ){
				st = st + art.toString();
			}
		}
		return st;
	}


}


