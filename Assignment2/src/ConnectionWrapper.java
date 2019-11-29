import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Johnathon Cameron and Andrew Palmer
 * @version 1.0
 * @see ConnectionWrapper
 * @since 1.8 (Java 8)
 */
public class ConnectionWrapper {
	/**
	 * ObjectOutputStream  object used as the socket outputstream.
	 */
	private ObjectOutputStream outputStream;
	/**
	 * ObjectInputStream object used as the socket inputstream.
	 */
	private ObjectInputStream inputStream;
	/**
	 * Socket object used for the socket TCP connection
	 */
	private Socket socket;
	
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @param socket
	 * Constructor initializing the Socket field to the Socket parameter
	 */
	public ConnectionWrapper(Socket socket) {	
		this.socket = socket;
	}
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @return Socket 
	 * Getter method to get the private socket field
	 */
	public Socket getSocket() {
		return this.socket;
	}
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @return ObjectOutputStream
	 * Getter method to get the private ObjectOutputStream field
	 */
	public ObjectOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @return ObjectInputStream
	 * Getter method to get the private ObjectInputStream field
	 */
	public ObjectInputStream getInputStream() {
		return this.inputStream;
	}	
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @throws IOException
	 * @return ObjectInputStream
	 * The method instantiates an object of ObjectInputStream using the input stream of the
     * socket, assigns the reference to the inputStream field and returns the inputStream
     * reference.
	 */
	public ObjectInputStream createObjectIStreams() throws IOException{
		this.inputStream = new ObjectInputStream(socket.getInputStream());
		return this.inputStream;
	}
	
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @throws IOException
	 * @return ObjectOutputStream
	 * The method instantiates an object of ObjectOutputStream using the output stream of
	 * the socket , assigns the reference to the outputStream field and returns the
     * outputStream reference.
	 */
	public ObjectOutputStream createObjectOStreams() throws IOException{
		this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
		return this.outputStream;
	}
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @throws IOException
	 * @return ObjectOutputStream
	 * The method instantiates an object of ObjectOutputStream and assigns the reference to
	 * the outputStream field. Then it instantiates an object of ObjectInputStream and assigns
     * the reference to the inputStream field.
	 */
	public void createStreams() throws IOException{
		//TODO not sure if this is how it is done
		this.outputStream = createObjectOStreams();
		this.inputStream = createObjectIStreams();
	}
	
	/**
	 * @author Johnathon Cameron and Andrew Palmer
	 * @throws IOException
	 * method used to instantiate the ObjectOutputStream 
	 * to the socket output stream using the Socket getOutputStream() method.
	 */
	public void closeConnection()throws IOException{
		//check for null references
		if(this.outputStream !=null)
			this.outputStream.close();
		//check for null references
		if(this.inputStream !=null)
			this.inputStream.close();
		//check for closed socket and null reference
		if(!this.socket.isClosed() && this.socket !=null)
			this.socket.close();
		
	}

}
