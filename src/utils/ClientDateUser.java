package utils;

import java.util.Scanner;

public class ClientDateUser {
	static Scanner sc = new Scanner (System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateUser dat2 = new  DateUser();
		int j,m,a;
		
		j = lire("Jour :", 1,31);
		m = lire("Mois :", 1,12);
		a = lire("Annee :", 1, Integer.MAX_VALUE);
		
		if(DateUser.validDate(j, m, a)){
			dat2 = new DateUser(j,m,a);
			affiche("Date :"+dat2.jourDeSemaine()+" "+dat2.toString());
			affiche("Jour suivant :"+dat2.lendemain1().toString());
			affiche("Jour avant :"+dat2.hier1().toString());
			
		
		} else {affiche("DATE INVALIDE!"); }
		DateUser today =new DateUser();
		affiche("date du jour: "+today.jourDeSemaine()+" "+ today.toString());
		
		if(dat2.avant(today)){affiche("date donnez est anteriore a la date du jour");}
		else{affiche("date donnez n'est pas anteriore a la date du jour");}
		
		affiche("age :"+dat2.age());
		
		///tests per il contatore di giorni di differenza fra date
		
		int giorniDiDifferenza =DateUser.joursEntreDates(dat2, today);
		affiche("i giorni di differenza tra  il " + dat2 + " e il " + today + "sono: " +giorniDiDifferenza);
		

	}
	public static int lire(String message, int min, int max){
		int choix;
		do{ affiche(message);
			choix = sc.nextInt();
			sc.nextLine();
			if(choix >= min && choix <=max){
				return choix;
			}
			else{
				affiche("hors intervalle");
			}
			} while(true);
		
	}
	

// ajouter par moi (angelo) pour le "ClientS21";
	
	public static int lire(String message, int limitCode){
		int choix;
		 affiche(message);
			choix = sc.nextInt();
			sc.nextLine();
				return choix;	
	}
	
	public static String lireString(String message){
		String output;
		do{ affiche(message);
			output= sc.next();
			if(output.length() <= 30 ){
				return output;
			}
			else{
				affiche("designation trop longue");
			}
		} while(true);
		
	}
	
	public static float lireFloat(String message){
		float output;
		do{ affiche(message);
			output= sc.nextFloat();
			sc.nextLine();
			if(output>=0 ){
				return output;
			}
			else{
				affiche("le prix ne peux pas etre negative");
			}
		} while(true);
		
	}
	
	public static float lire(String message , float inf , float sup){
		
		 affiche(message);
		 float saisie;
		 
		 do{
			saisie= sc.nextFloat();
			sc.nextLine();
			if(saisie>=inf && saisie <=sup )
				return saisie;
				affiche("lsaisie hors intervalle.....rassaissez svp\n");
		} while(true);
		
	}
	
	public static void affiche(String message){
		System.out.println(message);
	}
	
	public static String lire(String mes){
		affiche(mes);
		return sc.nextLine();
	}
	
	
}
	
	


