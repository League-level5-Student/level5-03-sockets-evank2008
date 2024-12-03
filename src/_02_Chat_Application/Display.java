package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Display implements MouseListener, KeyListener{
	String username;
	static final int WIDTH = 800;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Chat Room");
	JTextField field = new JTextField();
	//how to detect when someone presses enter in field with text in it?
	void run() {
		setup();
		field.addKeyListener(this);
		frame.add(panel);
		frame.setPreferredSize(new Dimension(WIDTH,500));
		frame.setVisible(true);
		panel.addMouseListener(this);
		panel.add(label);
		field.setPreferredSize(new Dimension(WIDTH-50,30));
		System.out.println(field.getSize().width);
		panel.add(field);
		field.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	void setup() {
		
	}
	void sendMessage(String message) {
		recieveMessage(message);
	}
	void recieveMessage(String message) {
		JLabel lab = new JLabel(username+": "+message);
		lab.setPreferredSize(new Dimension(WIDTH-50,20));
		panel.add(lab);
		frame.pack();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10) {
//enter pressed
			if(field.getText().length()!=0) {
				sendMessage(field.getText());
				field.setText("");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
