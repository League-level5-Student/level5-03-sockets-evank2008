package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
public class ClientPack{
	Socket socket;
	ObjectOutputStream os;
	ObjectInputStream is;
public ClientPack(Socket socket) {
	this.socket=socket;
	try {
		os = new ObjectOutputStream(socket.getOutputStream());
		is = new ObjectInputStream(socket.getInputStream());
		
		os.flush();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}
Object getData() {
	if (socket.isConnected()) {
		try {
			return((Object)is.readObject());
		}catch(EOFException e) {
			JOptionPane.showMessageDialog(null, "Connection Lost");
			return "/quit";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	return "/quit"; 

}
}
