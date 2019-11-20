import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


/**
 * @author Johnathon Cameron
 * @version 1.5
 * @see SeverChatUI
 * @since 1.8 (Java 8)
 */
public class ServerChatUI extends JFrame {

	/**
	 * {@value #serialVersionUID} final String used to set the error message on the
	 * calculator for any NaN errors
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Controller Object created as inner class to.
	 */
	private Controller ctrl = new Controller();
	
	
	/**
	 *@author Johnathon Cameron 
	 *Purpose: Constructor of ServerChatUI used to set the sockety class
	 *set the frame to create the server UI and also to run the client UI
	 */
	public ServerChatUI(Socket socket) {
		socket = new Socket();
		setFrame(createUI());
		runClient();
	}
	
	
	/**
	 *@author Johnathon Cameron 
	 *@return JPanel
	 *Purpose: Method is used to create the server UI.
	 */
	public JPanel createUI() {
		/***TOP Level Panel***/
		
		JPanel serverPane = new JPanel(new BorderLayout());
		
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
		sendButton.setEnabled(true);
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
		// TODO Auto-generated constructor stub
		
		
		
		/**ADD to Top level Panel*/
		serverPane.add(msgPanel,BorderLayout.NORTH);
		serverPane.add(chatDisplay);
		//return content Pane
		return serverPane;
		
	}
	
	/**
	 *@author Johnathon Cameron 
	 *Purpose: Method used to set the content pane to the frame and the frame prop
	 *of the server UI
	 */
	public final void setFrame(JPanel contentPane) {
		setContentPane(contentPane);
		setResizable(false);
		setPreferredSize(new Dimension(588,500));
		addWindowListener(new WindowController());
	}
	
	
	/**
	 *@author Johnathon Cameron
	 *Purpose: Method that is going to be implemented in part 2 of the assignment
	 */
	private void runClient() {
		
	}
	
	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see WindowController
	 * @since 1.8 (Java 8)
	 */
	private class WindowController extends WindowAdapter {
		
		public void windowClosing() {
			System.exit(0);
		}	
	}
	
	
	/**
	 * @author Johnathon Cameron
	 * @version 1.5
	 * @see Controller
	 * @since 1.8 (Java 8)
	 */
	private class Controller implements  ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent ace) {
					

		}
		
	}
	

}
