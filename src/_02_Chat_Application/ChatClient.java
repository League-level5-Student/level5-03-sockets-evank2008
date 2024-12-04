package _02_Chat_Application;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ChatClient extends Display{
	String ip;
	int port;
	Socket connection;
	ObjectOutputStream os;
	ObjectInputStream is;
	Thread messageReciever = new Thread(()->{
		while (connection.isConnected()) {
			try {
				recieveMessage((String)is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				recieveMessage("Server closed");
				return;
			}
			
		}	System.out.println("disconnected");
	});
	void sendMessage(String message) {
		message=username+": "+message;
		recieveMessage(message);
		try {
			os.writeObject(message);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
void recieveMessage(String message) {
	if(messagesReceived<16) {
			messagesReceived++;
	} else {
		//remove the top message
		panel.remove(2);
	}
	JLabel lab = new JLabel(message);
	lab.setPreferredSize(new Dimension(WIDTH-50,20));
	panel.add(lab);
	frame.pack();
	if(message.equals("Server is in use.")) {
		JOptionPane.showMessageDialog(null, "Wait for the current client to leave!");
		System.exit(0);
	}
}

@Override
void setup() {
	username="Client";
	//todo: make username change depending on how many are connected. have to ask server.
	try {
		port=inputPort();
		ip=JOptionPane.showInputDialog("Enter IP address");
		
		connection = new Socket(ip, port);

		os = new ObjectOutputStream(connection.getOutputStream());
		is = new ObjectInputStream(connection.getInputStream());

		os.flush();
		messageReciever.start();
		label.setText("Connected to Chat Server at " + ip + " on port " + port);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Could not connect");
		setup();
	}
}
}
