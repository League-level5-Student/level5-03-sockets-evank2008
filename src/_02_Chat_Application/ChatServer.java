package _02_Chat_Application;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ChatServer extends Display{
	
	int port;
	String ip = "[ip_address]";

	ServerSocket seSock;
	ArrayList<ClientPack> clients=new ArrayList<ClientPack>();
	
	Thread acceptClient = new Thread(()->{
		while(true) {
			synchronized(clients) {
			try {
				clients.add(new ClientPack(seSock.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	});
@Override
void recieveMessage(String message) {
	
	JLabel lab = new JLabel(username+": "+message);
	lab.setPreferredSize(new Dimension(WIDTH-50,20));
	panel.add(lab);
	frame.pack();
}

@Override
void setup() {
	username="Server";
	
	port=inputPort();
	
	try {
		seSock = new ServerSocket(port,100);
		acceptClient.start();
		ip=InetAddress.getLocalHost().getHostAddress();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	label.setText("Chat Server hosted at "+ip+" on port "+port);
}
int inputPort() {
	try {
	int num = Integer.parseInt(JOptionPane.showInputDialog("Enter port number"));
	return num;
	} catch(NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
	return inputPort();
	}
}
}
