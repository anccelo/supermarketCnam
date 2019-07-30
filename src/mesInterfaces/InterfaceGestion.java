package mesInterfaces;

public interface InterfaceGestion <TypeTableGeree, TypeTab1, TypeTab2> {
	
	public void menuGeneral (TypeTableGeree tab, TypeTab1 tab1, TypeTab2 tab2) throws Exception;
	
	public int menuChoix(TypeTableGeree tab, TypeTab1 tab1) throws Exception;
	
	public void creer (TypeTableGeree tab, TypeTab1 tab1,TypeTab2 tab2) throws Exception;
	
	public void modifier (TypeTableGeree tab, TypeTab1 tab1) throws Exception;
	
	public void supprimer (TypeTableGeree tab, TypeTab1 tab1) throws Exception;
	
	public void afficher (TypeTableGeree tab)throws Exception;
	

}
