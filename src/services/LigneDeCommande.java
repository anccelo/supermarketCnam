package services;

import java.io.Serializable;

//import serie21.Article23;



public class LigneDeCommande implements Serializable{
	
	private int code;
	private int quantite;
	
//*******************************	CONSTRUCTORS	*******************************
	
	public LigneDeCommande(){}
	
	public LigneDeCommande(int code, int quantite){
		this.code=code;
		this.quantite=quantite;
	}
	
//***********************	GETTERS, SETTERS AND ToSTRING	************************
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String toString(){
		return"Code:" + code + " ** Quantité:" + quantite ;
	}

//*****************************		SPECIFIC METHODS	***************************
	
	public  String facturer(TableArticles tabArt){
		ArticleAbstraite art = tabArt.retourner(code);
		String facture;
		//facture = art.prixFacture(quantite) + "\n";
		facture =art.facturer(quantite) + "\n";
		//art.prixFacture(quantite) é stato utilizato il poliformismo , sarà chimato il metodo in funzione dell'istanza dell'articolo
		return facture;
	}
	
	public float prixFacture(ArticleAbstraite art){
		float prix = art.prixFacture(quantite);
		return prix;
		// I tried the polyphormism and it works
	}

}
