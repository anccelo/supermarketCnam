package services;

public class Article extends ArticleAbstraite {

	// ******************************* CONSTRUCTORS *******************************
	public Article() {
	}

	public Article(int code, String designation, float pu) {
		super(code, designation, pu);
	}

	// *********************** GETTERS, SETTERS AND ToSTRING
	// ************************

	public String toString() {
		return "\n** Code: " + code + " ** Designation: " + designation + " ** pu: " + pu + " €";
	}

	// ***************************** SPECIFIC METHODS ***************************

	public float prixFacture(int quantite) {
		return quantite * pu;
	}

	public String facturer(int quantite) {
		return " " + code + "	" + designation + "		 " + quantite + "		 " + pu + "		 "
				+ prixFacture(quantite);
	}
}
