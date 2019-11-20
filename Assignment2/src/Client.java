import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Johnathon Cameron
 * @version 1.5
 * @see Client
 * @since 1.8 (Java 8)
 */
public class Client {
	/**
	 * 
	 * @param args
	 * Main Displays and creates the client chat ui 
	 */
	public static void main(String[] args) {
		
	       JFrame frame = new ClientChatUI("Cameron ClientChatUI");
	       frame.setLocationByPlatform(true);
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setMinimumSize(new Dimension(588, 500));
           frame.setResizable(false);
           frame.setVisible(true);
	 

	}
}


