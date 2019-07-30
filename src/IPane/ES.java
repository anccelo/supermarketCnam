package IPane;

import mesInterfaces.InterfaceES;

import javax.swing.*;//necessario per IPane

import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;

public class ES  implements InterfaceES{
	
	public  int lire(String message, int inf, int sup) throws AbandonException{
		//non c'é + bisogno di gestire gli erroi alla console
		do{
			try{
				String saisie= JOptionPane.showInputDialog(message);
				if (saisie == null) throw new AbandonException();
				
				if(saisie.equals("")) affiche("il faut saisier quelquechose svp!");
				else{
					int ent = Integer .parseInt(saisie);	
					if (ent  >= inf && ent <= sup ) return ent;	
					throw new ErreurSaisieException();
				}
			}catch(ErreurSaisieException er){
				affiche("SAISIE HORS  INTERVALLE entre [" + inf + " : "+ sup + "]");
			}catch(NumberFormatException nfe){
				affiche("SAISIE NON NUMERIQUE, RESAISISAIZ SVP!");
			}catch(AbandonException abe){
				if (saisieOuiNon("voulez vous abbandoner (O/N)")) throw abe;
			}catch (NullPointerException npe){
				affiche("rentrer un valeur correcte comme numero de commande");
			}
		}while(true);
	}
	
	
	public boolean  saisieOuiNon(String mes ){
		//return (JOptionPane.showConfirmDialog(null, mes)==0);//nn ho capito il null , dice che é un pointeur verso se stesso (verso il frame);
		return (JOptionPane.showConfirmDialog(null, mes, "fenetre de confirmation pour la sortie" , JOptionPane.YES_NO_OPTION)== 0);
	}

	
	public float lire(String message, float inf, float sup) throws AbandonException {

		do{
			try{
				String saisie= JOptionPane.showInputDialog(message);
				if (saisie == null) throw new AbandonException();
				
				if(saisie.equals("")) affiche("il faut saisier quelquechose svp!");
				else{
					float ent = Float.parseFloat(saisie);	
					if (ent  >= inf && ent <= sup) return ent;	
					throw new ErreurSaisieException();
				}
			}catch(ErreurSaisieException er){
				affiche("SAISIE HORS  INTERVALLE entre [" + inf + " : "+ sup + "]");
			}catch(NumberFormatException nfe){
				affiche("SAISIE NON NUMERIQUE, RESAISISAIZ SVP!");
			}catch(AbandonException abe){
				if (saisieOuiNon("voulez vous abbandoner (O/N")) throw abe;
			}
		}while(true);

	}
	
	public  String lire(String mes){
		 return  JOptionPane.showInputDialog(mes);
		
	}
	
	public  void affiche(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	
	

}
