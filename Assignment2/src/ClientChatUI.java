import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

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
public class ClientChatUI extends JFrame {

	/**
	 * {@value #serialVersionUID} final String used to set the error message on the
	 * calculator for any NaN errors
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * {@value #PORTS} final String used to set the error message on the
	 * calculator for any NaN errors
	 */
	private final String [] PORTS = {" ","8089","6500","65535"};

	
	/**
	 * @author Johnathon Cameron Constructor used set the frame title and run the client GUI
	 */
	public ClientChatUI(String title) {
		
		super.setTitle(title);
		runClient();
		
	}
	
	/**
	 *@author Johnathon Cameron 
	 *@return JPanel
	 *Purpose: Method used to create the Chat Client GUI.
	 */
	public JPanel createClientUI() {
		/**Top Layer Panels */
		//top level panel
		JPanel contentPane = new JPanel(new BorderLayout());
		//setting top level panel borders
		contentPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		//second layer panel for host and messages
		JPanel hostMessagePane = new JPanel( new BorderLayout());
		//second layer display panel
		JPanel displayPane = new JPanel(new BorderLayout());
		
		/****3rd Layer Panel creation******/
		//3rd layer panel host panel
		JPanel hostPanel = new JPanel(new BorderLayout());
		//creating a titled border
		TitledBorder connTitle = new TitledBorder("CONNECTION");
		//creating lined border
		Border connBorder = new LineBorder(Color.RED, 10);
		//setting title to the left
		connTitle.setTitleJustification(TitledBorder.LEFT);
		//setting title to the top
	    connTitle.setTitlePosition(TitledBorder.TOP);
	    //add border thickness and color to titled border
	    connTitle.setBorder(connBorder);
	    //set Host Panel borders
		hostPanel.setBorder(connTitle);
		
		/***4th Layer PANEL( HOST PANEL)  ***/
		//create a FlowLayout panel for host
		JPanel hostFlowPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//Text Field with localhost as default
		JTextField hostTxt = new JTextField("localhost",45);
		hostTxt.setMargin(getInsets());
		//setting text alignment
		hostTxt.setHorizontalAlignment(JTextField.LEFT);
		//setting background to white
		hostTxt.setBackground(Color.WHITE);
		//set editable
		hostTxt.setEditable(true);
		//request focus on host Txt
		hostTxt.requestFocusInWindow();
		//Setting insets to a 5pixels gap from the left of the textfield border to the text
		hostTxt.setMargin(new Insets(0,5,0,0));
		
		//Host Label prop
		JLabel hostLabel = new JLabel("Host:");
		//setting label dimensions
		hostLabel.setPreferredSize(new Dimension(35,30));
		//setting label for hostTxt field
		hostLabel.setLabelFor(hostTxt);
		//setting mnemonic H
		hostLabel.setDisplayedMnemonic('H');
	
		
		//adding components to host panel
		hostFlowPane.add(hostLabel, BorderLayout.WEST);
		hostFlowPane.add(hostTxt,BorderLayout.CENTER);
		/***HOST PANEL END***/
	
		
		/*** 4th layer PORT PANEL ***/
		//creating connection button
		JButton connButton = new JButton("Connect");
		connButton.setMnemonic('C');
		connButton.setPreferredSize(new Dimension(100,20));
		connButton.setBackground(Color.RED);
		
		connButton.setOpaque(true);
		//portPanel with border layout
		JPanel portPanel = new JPanel(new BorderLayout());
	
		//creating 5th layer panel
		JPanel portFlowPane = new JPanel(new FlowLayout());
		//create combo box for port numbers
		JComboBox <String> port = new JComboBox(PORTS); 
		port.setPreferredSize(new Dimension(100,20));
		port.setEditable(true);
		port.setBackground(Color.WHITE);
		//combo box label
		JLabel portLabel = new JLabel("Port:");
		//setting label size
		portLabel.setPreferredSize(new Dimension(35,30));
		//setting label for combo box
		portLabel.setLabelFor(port);
		//set mnemonic
		portLabel.setDisplayedMnemonic('P');
		
		//Adding components to portComponentPane
		portFlowPane.add(portLabel,BorderLayout.WEST);
		portFlowPane.add(port,BorderLayout.CENTER);
		portFlowPane.add(connButton,BorderLayout.EAST);
		
		//portComponentPane to main port panel
		portPanel.add(portFlowPane, BorderLayout.WEST);
		/**PORT PANEL END***/
		
		
		
		/***4th Layer MESSAGE PANEL *******/
		//message main panel
		JPanel msgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//5th layer Panel
		
		//creating title
		TitledBorder msgTitle = new TitledBorder("MESSAGE");
		Border msgBorder = new LineBorder(Color.BLACK, 10);
		msgTitle.setTitleJustification(TitledBorder.LEFT);
	    msgTitle.setTitlePosition(TitledBorder.TOP);
	    //add border thickness and color to titled border
	    msgTitle.setBorder(msgBorder);
	    //set Host Panel borders
		msgPanel.setBorder(msgTitle);
		//Txt area for user to write message
		JTextField msgTxt = new JTextField("Type Message",41);
		//Setting text alignment to left
		msgTxt.setHorizontalAlignment(JTextField.LEFT);
		
		//Send button
		JButton sendButton = new JButton("Send");
		//setting preferred size
		sendButton.setPreferredSize(new Dimension(80,19));
		//Setting mnemonic
		sendButton.setMnemonic('S');
		//disabled at launch
		sendButton.setEnabled(false);
		//adding to Panel that holds components
		msgPanel.add(msgTxt);
		//adding the button
		msgPanel.add(sendButton);
		
		
		/****END OF MESSAGE PANEL****/
		
		/***3rd Layer CHAT DISPLAY PANEL**/
		//Setting 3rd Layer Panel and borders
		JPanel chatDisplay = new JPanel(new BorderLayout());
		JPanel innerPanel = new JPanel( new BorderLayout());
		//create titled border
		TitledBorder chatTitle = new TitledBorder("CHAT DISPLAY");
		//Creating lined border
		Border chatBorder = new LineBorder(Color.BLUE, 10);
		//chat title
		chatTitle.setTitleJustification(TitledBorder.CENTER);
	    chatTitle.setTitlePosition(TitledBorder.TOP);
	    //add border thickness and color to titled border
	    chatTitle.setBorder(chatBorder);
	    //set Host Panel borders
		chatDisplay.setBorder(chatTitle);
		//Display components
		JTextArea chatText = new JTextArea(30,45);
		//add to inner panel
		innerPanel.add(chatText);
		chatText.setEditable(false);
		//Scroll bar panel
		JScrollPane scrollBarPane = new JScrollPane(innerPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//Add scroll pane to chat display
		chatDisplay.add(scrollBarPane);
		
		/**END OF CHAT DISPLAY PANEL***/
		
		/*****Adding to 2nd Layer Panels****/
		//adding host and port to one panel
		hostPanel.add(hostFlowPane, BorderLayout.NORTH);
		hostPanel.add(portPanel,BorderLayout.SOUTH);
		
		//adding host and message panel to a top level Panel
		hostMessagePane.add(hostPanel,BorderLayout.NORTH);
		hostMessagePane.add(msgPanel);
		//adding chat panel to top level panel
		displayPane.add(chatDisplay);
		
		
		/**Adding Top layer Panel**/
		//adding panels to main content panel 
		contentPane.add(hostMessagePane,BorderLayout.NORTH);
		contentPane.add(displayPane);

		
		return contentPane;
	 }
	/**
	 *@author Johnathon Cameron 
	 *Purpose: Method used to set the content pane of frame and addWindowListener to the frame.
	 */
	public void runClient() {
		setContentPane(createClientUI());
		addWindowListener(new WindowController());
	}
	
	
	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see WindowController
	 * @since 1.8 (Java 8)
	 */
	private class WindowController extends WindowAdapter {
		
		
		
	}
	
	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see Controller
	 * @since 1.8 (Java 8)
	 */
	private class Controller implements  ActionListener{
		
		/**
		 * @param ActionEvent
		 *            Method used to perform a action event depending on what the user
		 *            clicks(send button)
		 */
		@Override
		public void actionPerformed(ActionEvent ace) {
					

		}
		
	}

}
