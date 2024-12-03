package _02_Chat_Application;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ChatClient extends Display{
	static int clientNumber=0;
@Override
void recieveMessage(String message) {
	
	JLabel lab = new JLabel(username+": "+message);
	lab.setPreferredSize(new Dimension(WIDTH-50,20));
	panel.add(lab);
	frame.pack();
}

@Override
void setup() {
	username="Client";
	//todo: make username change depending on how many are connected. have to ask server.
	
}
}
