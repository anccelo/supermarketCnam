package connexionFichier;

import java.io.*;
import IPane.ES;

public class Connexion <TypeStructure> {
	
	private String nomPhisique;
	private ES es = new ES();
	
	public Connexion(String nom){
		this.nomPhisique = nom ;
	}
	
	public TypeStructure lire(){
		TypeStructure obj = null;
		try{
			FileInputStream fis = new FileInputStream(nomPhisique);//fis = file imput straam
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			obj = (TypeStructure)ois.readObject();
		}
		catch(FileNotFoundException fne){es.affiche(" *** FICHIER NOUVEAU!! A CREER *** \n "); }
		catch(IOException ioe){es.affiche(" *** PROBLEME DE LECTURE *** \n "); }
		catch(ClassNotFoundException cnf){es.affiche(" *** 	OBJET LU NON  CORRESPONDANT *** \n "); }
		return obj;
	}
	
	public void ecrire(TypeStructure obj){
		try{
			//on douvrait ajouterun boolean , s on ouvre avec true, on veux ajuter, avec faulse , on ecrase le vieux et on reecrive
			FileOutputStream fos = new FileOutputStream(nomPhisique);//fos = file output stream
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		}
		//si le file n'existe pas , il le cree 
		catch(FileNotFoundException fnf){es.affiche(" *** FICHIER NOUVEAU SERA CREE *** \n "); }
		catch(IOException ioe){es.affiche(" *** PROBLEME D' ECRITURE *** \n ");
		ioe.printStackTrace();}
	}
	
	

}
