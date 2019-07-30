package mesInterfaces;

public interface InterfaceStructure<TypeMetier,TypeCode> {
	
   //abstract puo' esserci o no 
	public int taille() throws Exception;
	
	public TypeMetier retourner(TypeCode code) throws Exception;
	
	public void supprimer (TypeCode code) throws Exception;
	
	public void ajouter (TypeMetier metier) throws Exception;
}
