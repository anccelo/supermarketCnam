package frames;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import IPane.ES;

public class FrameIdentification extends JFrame implements ActionListener {
	// tout ce qui est editable va dans les variable d'istance	
	private JTextField     login = new JTextField(15); //largeur de 20 caracters 
	private JPasswordField password = new JPasswordField(10);
	private JButton        valid = new JButton("VALIDEZ");
	
	private ES ES = new ES();
	
	//la frame se construi avec les contructeur
	public FrameIdentification(){
		
		this.setLayout((new GridLayout(4, 2)));//3pannel avec 2 elements ciascuno , e se ne ha uno o + di due fa nulla
		this.setSize(300, 400);//larghezza e lunghezza;
		this.setTitle(" IDENTIFICATION" );
		
		JPanel pan1, pan2, pan3, pan0;
		
		JLabel lab0 = new JLabel(getImage());
		JLabel lab1= new JLabel("login");
		JLabel lab2= new JLabel("password");
		
		pan0= new JPanel();
		pan0.setSize(300,300);
		pan0.add(lab0);
		this.add(pan0);
		
		pan1 = new JPanel();
		pan1.add(lab1);
		pan1.add(login);
		this.add(pan1);
		//login.addActionListener(this);
		
		pan2= new JPanel();
		pan2.add(lab2);
		pan2.add(password);
		this.add(pan2);
		//password.addActionListener(this);
		
		pan3 = new JPanel();
		pan3.add(valid);
		this.add(pan3);
		valid.addActionListener(this);
		
	
		this.setLocationRelativeTo(null);//porta la frame al centro alla sua aperture anziché in alto a sinistra
		this.setVisible(true);
			
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == valid ){
			String loginUser = login.getText();
			char[] passwordUser = password.getPassword(); // revnoie un tableaux de caractere
			
			String passwordUserString = conversion(passwordUser);
			
			if (verif(loginUser , passwordUserString)){
				ES.affiche("IDENTIFICATION OK");
				this.setVisible(false);
				new FrameClient();
			}else {
				ES.affiche("IDENTIFICATION ERRONEE.....");
				raz();//rimette in bianco i campi di login e password 
			}
		}
	}
	
	public String conversion(char[] tab){
		String conv="";
		for (int i=0 ; i<tab.length;i++){
			conv = conv +tab[i];
		}
		return conv;
	}
	
	public boolean verif (String login , String password){
		//return (login.equals("")) && password.equals("");
		return (login.equals("admin")) && password.equals("admin");
	}
	
	public void raz(){
		login.setText("");
		password.setText("");
	}
	
	public ImageIcon getImage(){
		return (new ImageIcon(ImageIcon.class.getResource("/thelma.JPG")));
	}


	
	
	

}
