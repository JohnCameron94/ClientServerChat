import java.net.Socket;
import javax.swing.JFrame;

/**
 * @author Johnathon Cameron
 * @version 1.5
 * @see Server
 * @since 1.8 (Java 8)
 */
public class Server {

	/**
	 *@author Johnathon Cameron 
	 *
	 *Purpose: Method used to lauch the Server GUI
	 */
	public static void launchClient(String title, Socket socket) {
		JFrame frame = new ServerChatUI(null);
		frame.setTitle(title);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	/**
	 *@author Johnathon Cameron 
	 *Purpose:Main Method used to create the server GUI.
	 */
	public static void main(String [] args) {
		
		launchClient("Cameron's Friend ServerChatUI",null);
		
	}

}
