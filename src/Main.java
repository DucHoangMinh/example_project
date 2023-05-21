import java.awt.Color;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(100, 100, 1000, 1000);
		obj.setTitle("Tank 2D");	
		obj.setBackground(Color.gray);
		obj.setResizable(false);
		
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		obj.setVisible(true);
	}

}
