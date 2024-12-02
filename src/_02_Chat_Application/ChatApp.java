package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */
public class ChatApp{
	static final int WIDTH = 800;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Chat Room");
	JTextField field = new JTextField();
public static void main(String[] args) {
	new ChatApp().run();
}
void run() {
	new Display().run();
}

}
