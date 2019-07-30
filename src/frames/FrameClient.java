package frames;

import javax.swing.JFrame;

import IPane.*;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisieException;
import services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameClient  extends JFrame implements ActionListener{
	
	private JButton gestArticles = new JButton("gestion des articles");
	private JButton gestCommandes = new JButton("gestion des commandes");
	private JButton gestFactures = new JButton("gestion des factures");
	private JButton fin = new JButton(" FIN ");
	
	private static ES es = new ES();
	
	GestionTableDesArticles gta = new GestionTableDesArticles("TABLE_ARTICLES.data");
	TableArticles tabArt = gta.recuperer(); //recovering data from the articles table
	
	GestionTableDesCommande gtc = new GestionTableDesCommande("TABLE_DES_COMMANDES.data");
	TableDesCommandes tabCmd = gtc.recuperer();
	
	GestionTableDesFactures gtf = new GestionTableDesFactures("TABLE_DES_FACTURES.data");
	TableDesFactures tabFct = gtf.recuperer();
	
	public FrameClient(){
			
		this.setLayout(new GridLayout(4, 1));
		this.setSize(300,300);
		this.setTitle(" Gestion de la Superette ");
		
		this.add(gestArticles);//aggiungo al frame  questo bottone
		gestArticles.addActionListener(this);
		
		this.add(gestCommandes);
		gestCommandes.addActionListener(this);
		
		this.add(gestFactures);
		gestFactures.addActionListener(this);
		
		fin.addActionListener(this);
		this.add(fin);
		
		this.setLocationRelativeTo(null);//porta la frame al centro alla sua aperture anziché in alto a sinistra
		this.setVisible(true);
	}
	
	
	public  void actionPerformed(ActionEvent evt){
		
		Object obj = null;
		
		if(evt.getSource() == gestArticles){
			es.affiche("gestion table des ariticles appellé ");
			try{gta.menuGeneral(tabArt, tabCmd,null);
			}catch (Exception e){}
		}
		if(evt.getSource() == gestCommandes){
			es.affiche("gestion table des acommandes appellé ");
			try{ gtc.menuGeneral(tabCmd, tabArt,null);
			}catch (Exception e){}
		}
		
		if(evt.getSource() == gestFactures){
			es.affiche("gestion table des factures appellé ");
			try{ gtf.menuGeneral(tabFct, tabCmd,tabArt);
			}catch (Exception e){}
		}
			
		if(evt.getSource() == fin){
			gta.sauvegarder(tabArt);
			gtc.sauvegarder(tabCmd);
			gtf.sauvegarder(tabFct);
			es.affiche("Aurevoir!");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	public static void main(String[] args) {

		
		
		int choix;
		do {
			try {
				choix = menuChoix();
				switch (choix) {
				case 1: gta.menuGeneral(tabArt, tabCmd); break;
				case 2: gtc.menuGeneral(tabCmd, tabArt); break;
				case 3: gtf.menuGeneral(tabFct, tabCmd, tabArt); break;
				}
			}catch (ErreurSaisieException e) {
				choix = 0;
			}catch (AbandonException ae){
				choix = 0;
			}
			
		} while (choix != 0);
		es.affiche(" *** SAUVEGARDE DES FICHIERS  EN COURS ****\n");
		gta.sauvegarder(tabArt);// data backup
		gtc.sauvegarder(tabCmd);
		gtf.sauvegarder(tabFct);
		es.affiche("AU REVOIR!");
	}
	// ***********************************************************************************************

	public static int menuChoix() throws AbandonException, ErreurSaisieException {//questo throws vuol dire che nn tratto l'eccezione ma la propago
																//al metodo che ha chiamato questo
		int choix;
		String menu = "    BIENVENU  SUR SUPERETTE \n\n" 
					+ " GESTION DES ARTICLES.........1\n"
					+ " GESTION DES COMMANDE ........2\n" 
					+ " GESTION DES FACTURES ........3\n" 
					+ " FIN..........................0\n"
					+ "                     VOTRE CHOIX:\n " ;
					
		choix = es.lire(menu, 0, 3);
		return choix;
	}
	

	*/
}


