package IConsole;

import java.util.*;

import mesExceptions.ErreurSaisieException;
import mesInterfaces.InterfaceES;

public class ES implements InterfaceES{
	
	private Scanner sc = new Scanner(System.in);
	
	public  int lire(String message, int inf, int sup) throws ErreurSaisieException{
		
		int choix;
		do{ 
			try{
				affiche(message);
				choix = sc.nextInt();
				sc.nextLine();
				if(choix >= inf && choix <=sup) return choix;
				throw new ErreurSaisieException();
				
			}catch(InputMismatchException e){
				affiche(" SASIE NON NUMERIC!!   RASSAISSISEZ SVP \n");
				sc.nextLine();//necessario per vider le buffer seno vado in boucle infinito .
								// me lo son fatto rispiegare ma nn ho capito benissimo. faccio un retoru au charriot o nonsocosa
			}
			catch(ErreurSaisieException er){
				boolean abandon = saisieOuiNon (" saisie hors intervalle ...voulez vous abandonner (O/N)");
				if(abandon) throw er;
			}
		} while(true);
		
	}
	
	
	public boolean  saisieOuiNon(String mes ){
		char reponse = lire(mes).charAt(0);//leggo il primo carattere del messaggio saisie
		return  (reponse=='o' || reponse=='O');
	}

	

	
	public float lire(String message, float inf, float sup) throws ErreurSaisieException {

		affiche(message);
		float saisie;
		do {
			try {
				saisie = sc.nextFloat();
				sc.nextLine();
				if (saisie >= inf && saisie <= sup)
					return saisie;
				throw new ErreurSaisieException();
			} catch (InputMismatchException e) {
				affiche(" SASIE NON NUMERIC!!   RASSAISSISEZ SVP \n");
				sc.nextLine();
			} catch (ErreurSaisieException er) {
				boolean abandon = saisieOuiNon(" saisie hors intervalle ...voulez vous abbandoner (O/N))");
				if (abandon) throw er;
				else affiche(message); 
			}
		} while (true);

	}
	
	public  String lire(String mes){
		affiche(mes);
		return sc.nextLine();
	}
	
	public  void affiche(String message){
		System.out.println(message);
	}
	

}
