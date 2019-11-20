/**
 * @author Johnathon Cameron and Andrew Palmer
 * @version 1.0
 * @see ChatProtocolConstants
 * @since 1.8 (Java 8)
 */
public class ChatProtocolConstants {

	/**
	 * {@value #CHAT_TERMINATOR} final String used to inform the user that the 
	 * TCP connection is now closed.
	 */
	protected static final String CHAT_TERMINATOR="bye";
	/**
	 * {@value #DISPLACMENT} final String used to represent the Tab character
	 */
	protected static final String DISPLACMENT="\t\t";
	/**
	 * {@value #LINE_TERMINATOR} final string used to represent Line terminator
	 * characters
	 */
	protected static final String LINE_TERMINATOR="\r\n";
	/**
	 * {@value #HANDSHAKE} final String used to inform the user that 
	 * the  TCP Connection hand shake was done successfully
	 */
	protected static final String HANDSHAKE="hello";

}
