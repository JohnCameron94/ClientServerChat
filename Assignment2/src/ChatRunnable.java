import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JTextArea;
//TODO test
/**
 * @author Johnathon Cameron and Andrew Palmer
 * @version 1.5
 * @see ChatRunnable
 * @since 1.8 (Java 8)
 */
public class ChatRunnable<T extends JFrame & Accessible> implements Runnable {

	/**
	 * final Generic T used to hold the user interface of the Chat UI
	 */
	private final T ui;

	/**
	 * final Socket Object used to hold the
	 * TCP connection of the current user session.
	 */
	private final Socket socket;

	/**
	 * final ObjectInputStream used to hold the
	 * sockets input stream
	 */
	private final ObjectInputStream inputStream;

	/**
	 * final ObjectOutputStream used to hold the
	 * sockets output stream
	 */
	private final ObjectOutputStream outputStream;

	/**
	 * final JTxtArea used to hold the current users chat display
	 */
	private final JTextArea display;
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @param ui the generic type
	 * @param connection the connection wrapper
	 * The constructor uses the connection parameter and its get methods to initialize the
	 * socket, inputStream, and outputStream fields. It uses the ui parameter to initialize the
	 * display and the ui fields.
	 */
	public ChatRunnable(T ui, ConnectionWrapper connection) {
		// instantiate the socket connection
		this.socket = connection.getSocket();
		// instantiate input stream from socket
		this.inputStream = connection.getInputStream();
		// instantiate output stream from socket
		this.outputStream = connection.getOutputStream();
		// initialize the UI field
		this.ui = ui;
		// instantiate the JTextArea field display
		this.display = ui.getDisplay();
	}

	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * Used to implement the run() method in the Class Runnable.
	 * Implements a endless loop while the input/output stream and 
	 * sockets are open, reading the input and appending the outputstream 
	 * to the ui display. If at anytime the loop breaks and the socket is not closed it 
	 * will output the error stream string.
	 */
	@Override
	public void run() {
		// Local Date time assigned to the current date and time
		LocalDateTime dT = LocalDateTime.now();
		// date and time formatter to example: October 31, 8:00 AM
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM dd, HH:mm a");
		// input string
		String strin = "";

		//endless loop
		while (true) {

			// if socket isClosed break loop
			if (socket.isClosed()) break;

			// Reading String from object
			try {
				try {
					strin = (String) this.inputStream.readObject();
				} catch(EOFException eof) {
					//handle EOFException
				}

				if (strin.trim() == ChatProtocolConstants.CHAT_TERMINATOR) {
					// final String if line terminator is reached
					final String terminate = ChatProtocolConstants.DISPLACMENT + dT.format(fmt)
							+ ChatProtocolConstants.LINE_TERMINATOR + strin;
					// append the terminate string to the display
					this.display.append(terminate);
					// break endless loop
					break;
				}

			//if any exception are caught break loop	
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				break;
			}
			//if strin.trim() is not = to CHAT_TERMINATOR
			final String append = ChatProtocolConstants.DISPLACMENT + dT.format(fmt)
					+ ChatProtocolConstants.LINE_TERMINATOR + strin;
			this.display.append(append);

		}

		//if loop breaks and socket is not closed write to output stream
		if (!this.socket.isClosed()) {
			//String object
			String errstrm = ChatProtocolConstants.DISPLACMENT.getClass().getName() + "+"
					+ ChatProtocolConstants.CHAT_TERMINATOR.getClass().getName()
					+ ChatProtocolConstants.LINE_TERMINATOR.getClass().getName();

			try {
				//Writing string object to output stream 
				this.outputStream.writeObject(errstrm);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// closing chat
		ui.closeChat();
	}

}
