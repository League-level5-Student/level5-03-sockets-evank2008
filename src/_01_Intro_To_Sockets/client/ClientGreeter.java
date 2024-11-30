package _01_Intro_To_Sockets.client;

import java.net.*;

import javax.swing.JOptionPane;

import _00_Click_Chat.networking.Server;

import java.io.*;

public class ClientGreeter {

   public static void main(String [] args) {
	   System.out.println("main runs");
	   String ipAddress=null;
	  //1. Create a String for the ip address of the server. 
	  // If you don't know how to find a computer's ip address, ask about ifconfig on linux/mac and ipconfig on windows.
     try {
	 ipAddress = InetAddress.getLocalHost().getHostAddress();
	 System.out.println("ip dressed");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
      //2. Create an integer for the server's port number
      int portNumber = 8080;
      //3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
    try {
    	 //4. Create an object of the Socket class. When initializing the object, pass in the ip address and the port number
 Socket sock = new Socket(ipAddress,portNumber);
 
 System.out.println("socked");
         //5. Create a DataOutputStream object. When initializing it, use the Socket object you created in step 4 to call the getOutputStream() method.
         DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
         //6. Use the DataOutputStream object to send a message to the server using the writeUTF(String message) method
         outStream.writeUTF(JOptionPane.showInputDialog("message to server here"));
         System.out.println("written");
         //7. Create a DataInputStream object. When initializing it, use the Server object you created in step 4 to call the getInputStream() method.
         DataInputStream inStream = new DataInputStream(sock.getInputStream());
         //8. Use the DataInputStream object to print a message from the server using the readUTF() method.
         System.out.println("server message: "+inStream.readUTF());
         System.out.println("read");
         //9. Close the client's server object
         sock.close();
         System.out.println("closed");
    } catch(Exception e) {
    	e.printStackTrace();
    }

   }
}
