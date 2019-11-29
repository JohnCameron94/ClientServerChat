import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

/**
 * @author Johnathon Cameron
 * @version 1.5
 * @see Server
 * @since 1.8 (Java 8)
 */
public class Server {
	
	private static final int DEFAULT_PORT = 65535;
	/**
	 *@author Johnathon Cameron 
	 *
	 *Purpose: Method used to lauch the Server GUI
	 */
	public static void launchClient(String title, Socket socket) {
		JFrame frame = new ServerChatUI(socket);
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
		
		//integer variable holding the boud port #
		int port;
		ServerSocket server = null;
		//check if there is a command line argument
		if(args.length >0) {
			//Possible number format exception TODO
				//assign port to args
				port = Integer.parseInt(args[0]);
				System.out.println("Using port: " + port);
		//if not cmd line argument, assign port to default
		}else {
			port = DEFAULT_PORT;
			System.out.println("Using default port: " + port);
		}
		
		
		try {
			//ServerSocket bound to specific port.
			server = new ServerSocket(port);
				
		} catch (IOException e) {
			System.out.println("Failed to create Server at port: "+port);
			e.printStackTrace();
		}
		//local friend variable
		int friend = 0;
		
		//endless loop
		while(true) {
			System.out.println("Waiting For client Request");
			//server.accept returns socket onces connection is found.
			try {
				//accepting new connection
				Socket socket = server.accept();

				if(socket != null) {
					if (socket.getSoLinger() != -1) socket.setSoLinger(true, 5);
					if (!socket.getTcpNoDelay()) socket.setTcpNoDelay(true);

					//print socket info to console
					System.out.printf("Connecting to a client Socket[addr=%s, port=%d, localport=%d]\n",
							socket.getInetAddress(), socket.getPort(), socket.getLocalPort());
					//increment friend variable
					friend++;
					//declaring final string
					final String title = "Johnathon's Friend" + friend;
					//launching client
					launchClient(title, socket);
				}
			} catch (IOException e) {
				System.out.println("Failed To Accept new Connection "+e);
			}
			
		}
	}
}


