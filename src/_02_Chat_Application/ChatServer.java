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

public class ChatServer extends Display {

	int port;
	String ip = "[ip_address]";
//1 thread for accepting new clients
//1 thread for running main code
//1 thread per client for message receiving, total-2
	// nah jk just make it 2person
	boolean stopRejection = true;
	boolean stopReciever=true;
	boolean stopAccept=true;
	ServerSocket seSock;
	ClientPack client;
	Thread fullRejector = new Thread(()->{
		//assume a client is connected
		stopRejection=false;
		while(!stopRejection) {
		try {
		ObjectOutputStream rejectOs = new ObjectOutputStream(seSock.accept().getOutputStream());
		rejectOs.flush();
		rejectOs.writeObject("Server is in use.");
		rejectOs.flush();
		recieveMessage("A client tried to join but the room is full!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	});
	Thread messageReciever = new Thread(() -> {
		// System.out.println("Server message reciever active");
		stopReciever=false;
		while (!stopReciever) {
			System.out.println("whiletrue runs");
			
			System.out.println("looping for a clientPack");
			if (client.socket.isConnected()) {
				System.out.println("detected a socket connected");
				try {
					System.out.println("trying to recieve message");
					recieveMessage((String) client.is.readObject());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}

			} else {
				recieveMessage("Client left!");
				client=null;
			}
			System.out.println("disconnected");
		}
	});
	Thread acceptClient = new Thread(() -> {
		stopAccept=false;
		while (!stopAccept) {
			try {
				if (client == null) {
					client = new ClientPack(seSock.accept());
					recieveMessage("Client connected!");
					fullRejector.start();
					messageReciever.start();
				} else {
					//kick client if they gone
					if(!client.socket.isConnected()) {
						recieveMessage("Client disconnected!");
						client=null;
						stopRejection=true;
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}});
	
	void recieveMessage(String message) {
		if(messagesReceived<16) {
			messagesReceived++;
	} else {
		//remove the top message
		panel.remove(2);
	}
		JLabel lab = new JLabel(message);
		lab.setPreferredSize(new Dimension(WIDTH - 50, 20));
		panel.add(lab);
		frame.pack();
	}

	void sendMessage(String message) {
		message = username + ": " + message;
		recieveMessage(message);
				try {
					if(client!=null) {
					client.os.writeObject(message);
					client.os.flush();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}

	@Override
	void setup() {
		username = "Server";

		port = inputPort();
		try {
			seSock = new ServerSocket(port, 100);
			ip = InetAddress.getLocalHost().getHostAddress();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setText("Chat Server hosted at " + ip + " on port " + port);
		recieveMessage("Started server");
		acceptClient.start();
	}

}
