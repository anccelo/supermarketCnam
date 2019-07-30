package services;



public class ArticlePromo  extends ArticleAbstraite{
	private int quantiteMini;
	private float reduction;
	
//*******************************	CONSTRUCTORS	*******************************
	
	public ArticlePromo(){}
	
	public ArticlePromo(int code,String Designation , float pu , int quantiteMini,float reduction){
		super(code , Designation, pu); // super da solo é un pointeur vers l'objet  de la super classe
		this.quantiteMini=quantiteMini;
		this.reduction=reduction;
	}
	
//***********************	GETTERS, SETTERS AND ToSTRING	************************
	
	public String toString(){
		return "\n** Code: " + code + " ** Designation: "+ designation + " ** pu: "+pu+ " € " + " ** quantité minimum pur promo " + quantiteMini +" ** reduction " + reduction + " % ";
	}

	public int getQuantiteMini() {
		return quantiteMini;
	}

	public void setQuantiteMini(int quantiteMini) {
		this.quantiteMini = quantiteMini;
	}

	public float getReduction() {
		return reduction;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}
	
//*****************************		SPECIFIC METHODS	***************************
	
	public float prixFacture(int quantite){
		if(quantite< quantiteMini) return  quantite*pu;
		else return (quantite*getPu())*(1 - reduction/100);
	}
	
	/*public String facturer( int quantite){
		return super.facturer(quantite) + " ( remise: "+  reduction +" %" + " si qtite cdee>= " + quantiteMini +")" ;
	}
	*/
	
	public String facturer( int quantite){
			return " " + code + "\t " + designation + "\t\t "+ quantite + "\t\t" + pu +"\t\t " + prixFacture(quantite) 
		 + " ( remise: "+  reduction +" %" + " si qtite cdee>= " + quantiteMini +")" ;
	}
	
	
	
	
	
}
