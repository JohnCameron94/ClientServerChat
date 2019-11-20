import javax.swing.JTextArea;
/**
 * @author Johnathon Cameron and Andrew Palmer
 * @version 1.0
 * @see Accessible
 * @since 1.8 (Java 8)
 */
public interface Accessible {
	
	/**
	 *@author Johnathon Cameron and Andrew Palmer
	 *@return JTextArea
	 *Purpose:Method used to get JTextArea (display)
	 */
	public JTextArea getDisplay();
	
	/**
	 *@author Johnathon Cameron and Andrew Palmer
	 *Purpose: Method used to close chat.
	 */
	public void closeChat();
		

}
