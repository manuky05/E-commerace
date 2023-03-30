package LambdaA;

import java.awt.FlowLayout;

import javax.swing.*;

public class MYWindow {
	public static void main(String[] args) {
		
		JFrame frame =new JFrame ("My Window");
		frame.setSize(400,400);
		frame.setLayout(new FlowLayout());
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
