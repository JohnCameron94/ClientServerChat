import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


/**
 * @author Johnathon Cameron
 * @version 1.5
 * @see Client
 * @since 1.8 (Java 8)
 */
public class ClientChatUI extends JFrame implements Accessible {

	/**
	 * {@value #serialVersionUID} final String used to set the error message on the
	 * calculator for any NaN errors
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * {@value } final String used to set the error message on the calculator
	 * for any NaN errors
	 */
	private final String[] PORTS = { " ", "8089", "6500", "65535" };
	/**
	 * JTextField used to write a message in the chat
	 */
	private JTextField message;
	/**
	 * JButtton used to send the message on the chat
	 */
	private JButton sendButton;
	
	/**
	 *JButton used to connect to the Server 
	 */
	private JButton connButton;
	
	/**
	 * JTextField used to enter host text value
	 */
	private JTextField hostTxt;
	/**
	 * JComboBox of type string used for the ports
	 */
	private JComboBox<String> portBox;
	/**
	 * JTextArea used to display the chat
	 */
	private JTextArea display;
	/**
	 * Output stream used to output the message through the connections
	 */
	private ObjectOutputStream outputStream;
	/**
	 * Socket object used to maintain the socket connection
	 */
	private Socket socket;
	/**
	 * ConnectionWrapper
	 */
	private ConnectionWrapper connection;
	/**
	 * Controller object from inner class
	 */
	private Controller ctrl = new Controller();

	/**
	 * Gets the display field
	 * @return the display field
	 */
	@Override
	public JTextArea getDisplay() {
		return this.display;
	}

	/**
	 * @author Johnathon Cameron Constructor used set the frame title and run the
	 *         client GUI
	 */
	public ClientChatUI(String title) {

		super.setTitle(title);
		runClient();

	}

	/**
	 * If the socket is not closed the method tries to close the connection. Then it calls
	 * enableConnectButton()
	 */
	@Override
	public void closeChat() {
		// try to close connection
		// if connection is not closed, close it
		try {
			// attempt to close connection
			connection.closeConnection();
			// dispose of the frame
			enableConnectButton();
		} catch (IOException e) {
			// print in command prompt if exceptions are thrown.
			System.out.println("Failed close Connection");
			e.printStackTrace();
		}
	}

	/**
	 * @author Johnathon Cameron
	 * @return JPanel Purpose: Method used to create the Chat Client GUI.
	 */
	public JPanel createClientUI() {
		/** Top Layer Panels */
		// top level panel
		JPanel contentPane = new JPanel(new BorderLayout());
		// setting top level panel borders
		contentPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		// second layer panel for host and messages
		JPanel hostMessagePane = new JPanel(new BorderLayout());
		// second layer display panel
		JPanel displayPane = new JPanel(new BorderLayout());

		/**** 3rd Layer Panel creation ******/
		// 3rd layer panel host panel
		JPanel hostPanel = new JPanel(new BorderLayout());
		// creating a titled border
		TitledBorder connTitle = new TitledBorder("CONNECTION");
		// creating lined border
		Border connBorder = new LineBorder(Color.RED, 10);
		// setting title to the left
		connTitle.setTitleJustification(TitledBorder.LEFT);
		// setting title to the top
		connTitle.setTitlePosition(TitledBorder.TOP);
		// add border thickness and color to titled border
		connTitle.setBorder(connBorder);
		// set Host Panel borders
		hostPanel.setBorder(connTitle);

		/*** 4th Layer PANEL( HOST PANEL) ***/
		// create a FlowLayout panel for host
		JPanel hostFlowPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// Text Field with localhost as default
		this.hostTxt = new JTextField("localhost", 45);
		this.hostTxt.setMargin(getInsets());
		// setting text alignment
		this.hostTxt.setHorizontalAlignment(JTextField.LEFT);
		// setting background to white
		this.hostTxt.setBackground(Color.WHITE);
		// set editable
		this.hostTxt.setEditable(true);
		// request focus on host Txt
		this.hostTxt.requestFocusInWindow();
		// Setting insets to a 5pixels gap from the left of the textfield border to the
		// text
		this.hostTxt.setMargin(new Insets(0, 5, 0, 0));

		// Host Label prop
		JLabel hostLabel = new JLabel("Host:");
		// setting label dimensions
		hostLabel.setPreferredSize(new Dimension(35, 30));
		// setting label for hostTxt field
		hostLabel.setLabelFor(this.hostTxt);
		// setting mnemonic H
		hostLabel.setDisplayedMnemonic('H');

		// adding components to host panel
		hostFlowPane.add(hostLabel, BorderLayout.WEST);
		hostFlowPane.add(this.hostTxt, BorderLayout.CENTER);
		/*** HOST PANEL END ***/

		/*** 4th layer PORT PANEL ***/
		// creating connection button
		this.connButton = new JButton("Connect");
		this.connButton.setMnemonic('C');
		this.connButton.setPreferredSize(new Dimension(100, 20));
		this.connButton.setBackground(Color.RED);
		this.connButton.setActionCommand("C");
		this.connButton.addActionListener(ctrl);

		this.connButton.setOpaque(true);
		// portPanel with border layout
		JPanel portPanel = new JPanel(new BorderLayout());

		// creating 5th layer panel
		JPanel portFlowPane = new JPanel(new FlowLayout());
		// create combo box for port numbers
		this.portBox = new JComboBox<String>(PORTS);
		this.portBox.setPreferredSize(new Dimension(100, 20));
		this.portBox.setEditable(true);
		this.portBox.setBackground(Color.WHITE);
		// combo box label
		JLabel portLabel = new JLabel("Port:");
		// setting label size
		portLabel.setPreferredSize(new Dimension(35, 30));
		// setting label for combo box
		portLabel.setLabelFor(portBox);
		// set mnemonic
		portLabel.setDisplayedMnemonic('P');

		// Adding components to portComponentPane
		portFlowPane.add(portLabel, BorderLayout.WEST);
		portFlowPane.add(portBox, BorderLayout.CENTER);
		portFlowPane.add(this.connButton, BorderLayout.EAST);

		// portComponentPane to main port panel
		portPanel.add(portFlowPane, BorderLayout.WEST);
		/** PORT PANEL END ***/

		/*** 4th Layer MESSAGE PANEL *******/
		// message main panel
		JPanel msgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 5th layer Panel

		// creating title
		TitledBorder msgTitle = new TitledBorder("MESSAGE");
		Border msgBorder = new LineBorder(Color.BLACK, 10);
		msgTitle.setTitleJustification(TitledBorder.LEFT);
		msgTitle.setTitlePosition(TitledBorder.TOP);
		// add border thickness and color to titled border
		msgTitle.setBorder(msgBorder);
		// set Host Panel borders
		msgPanel.setBorder(msgTitle);
		// Txt area for user to write message
		message = new JTextField("Type Message", 41);
		// Setting text alignment to left
		message.setHorizontalAlignment(JTextField.LEFT);

		// Send button
		this.sendButton = new JButton("Send");
		// setting preferred size
		this.sendButton.setPreferredSize(new Dimension(80, 19));
		// Setting mnemonic
		this.sendButton.setMnemonic('S');
		// disabled at launch
		this.sendButton.setEnabled(false);
		this.sendButton.setActionCommand("S");
		this.sendButton.addActionListener(ctrl);
		// adding to Panel that holds components
		msgPanel.add(message);
		// adding the button
		msgPanel.add(this.sendButton);

		/**** END OF MESSAGE PANEL ****/

		/*** 3rd Layer CHAT DISPLAY PANEL **/
		// Setting 3rd Layer Panel and borders
		JPanel chatDisplay = new JPanel(new BorderLayout());
		JPanel innerPanel = new JPanel(new BorderLayout());
		// create titled border
		TitledBorder chatTitle = new TitledBorder("CHAT DISPLAY");
		// Creating lined border
		Border chatBorder = new LineBorder(Color.BLUE, 10);
		// chat title
		chatTitle.setTitleJustification(TitledBorder.CENTER);
		chatTitle.setTitlePosition(TitledBorder.TOP);
		// add border thickness and color to titled border
		chatTitle.setBorder(chatBorder);
		// set Host Panel borders
		chatDisplay.setBorder(chatTitle);
		// Display components
		this.display = new JTextArea(30, 45);
		// add to inner panel
		innerPanel.add(this.display);
		this.display.setEditable(false);
		// Scroll bar panel
		JScrollPane scrollBarPane = new JScrollPane(innerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Add scroll pane to chat display
		chatDisplay.add(scrollBarPane);

		/** END OF CHAT DISPLAY PANEL ***/

		/***** Adding to 2nd Layer Panels ****/
		// adding host and port to one panel
		hostPanel.add(hostFlowPane, BorderLayout.NORTH);
		hostPanel.add(portPanel, BorderLayout.SOUTH);

		// adding host and message panel to a top level Panel
		hostMessagePane.add(hostPanel, BorderLayout.NORTH);
		hostMessagePane.add(msgPanel);
		// adding chat panel to top level panel
		displayPane.add(chatDisplay);

		/** Adding Top layer Panel **/
		// adding panels to main content panel
		contentPane.add(hostMessagePane, BorderLayout.NORTH);
		contentPane.add(displayPane);

		return contentPane;
	}

	/**
	 * @author Johnathon Cameron Purpose: Method used to set the content pane of
	 *         frame and addWindowListener to the frame.
	 */
	public void runClient() {
		setContentPane(createClientUI());
		addWindowListener(new WindowController());
	}

	/**
	 * @author Johnathon Cameron Method used to enable the connect button when there
	 *         is not connections to the server
	 */
	private void enableConnectButton() {
		this.connButton.setEnabled(true);
		this.connButton.setBackground(Color.RED);
		this.sendButton.setEnabled(false);
		hostTxt.setRequestFocusEnabled(true);

	}

	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see WindowController
	 * @since 1.8 (Java 8)
	 */
	private class WindowController extends WindowAdapter {

		/**
		 * The method using the outputStream tries to write the following object:
		 * ChatProtocolConstants.CHAT_TERMINATOR
		 * If exception occurs it calls System.exit(0); otherwise it calls System.exit(0).
		 * @param e the window event
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				//output stream will never be initialized until the server is started, check for null pointer
				if(outputStream !=null) outputStream.writeObject(ChatProtocolConstants.CHAT_TERMINATOR);
			} catch(IOException ioe) {
				System.exit(0);
			}
		}
	}

	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see Controller
	 * @since 1.8 (Java 8)
	 */
	private class Controller implements ActionListener {

		/**
		 * @param ace
		 * Method used to perform a action event depending on what the user clicks(send button)
		 */
		@Override
		public void actionPerformed(ActionEvent ace) {
			//TODO Check for RunTime error
			boolean connected = false;
			String host;
			int port = 0;

			if (ace.getActionCommand() == "C") {
				host = hostTxt.getText();
				port = Integer.parseInt(Objects.requireNonNull(portBox.getSelectedItem()).toString());

				connected = connect(host, port);
				if (!connected) return;
				connButton.setEnabled(false);
				connButton.setBackground(Color.BLUE);
				sendButton.setEnabled(true);
				message.setRequestFocusEnabled(true);

				Runnable runnable = new ChatRunnable(ClientChatUI.this, connection);
				Thread thread = new Thread(runnable);
				thread.start();

			} else if (ace.getActionCommand() == "S") {
				send();
			}
		}

		/**
		 * The method gets the text from the message text field assigns it to a local variable
		 * sendMessage, appends it to the display adding a line terminator, and then uses the
		 * outputStream to write an String
		 */
		private void send() {
			String sendMessage = message.getText();
			display.append(sendMessage + '\n');
			try {
				outputStream.writeObject(ChatProtocolConstants.DISPLACMENT + sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
			} catch(IOException ioe) {
				enableConnectButton();
				display.append(ioe.getMessage() + '\n');
			}
		}

		private boolean connect(String host, int port) {
			try {
				//getting the address of the host name
				InetAddress addr = InetAddress.getByName(host);
				//creating a socket address
				SocketAddress sockaddr = new InetSocketAddress(addr, port);
				//creating a new socket
				Socket socket = new Socket();
				//time out variable 5sec
				int timeout = 5000;
				//create a timeout socket to the socket address
				socket.connect(sockaddr, timeout);
				
				//set the socket
				if(socket.getSoLinger()!= -1) socket.setSoLinger(true,5);
				if(!socket.getTcpNoDelay()) socket.setTcpNoDelay(true);
				
				//creating final string connection
				final String append = "Connecting to a client Socket ["+socket.getInetAddress()+",port= "+socket.getPort()+", localport="+socket.getLocalPort() +"]\n";
	                   
				//append to display
				display.append(append);
				//creating new connection wrapper of socket reference
				connection = new ConnectionWrapper(socket);
				//create istream and ostream
				connection.createStreams();
				//assign outstream filed to connection.getOutputstream throw IOException
				outputStream = connection.getOutputStream();
				
				return true;
				
				//catch any thrown exception IO or unknown host
			} catch (UnknownHostException unknownHost) {
				display.append("ERROR: Connection Refused: server is not available. Check port or restart server");
			} catch (IOException io) {
				display.append("ERROR: Connection Refused: server is not available. Check port or restart server");
			}
			
			
			return false;
		}
	}
}
