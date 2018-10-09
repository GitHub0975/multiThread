
import java.net.*;
import java.io.*;

public class MultiServer {
	private static ServerSocket serverSocket;
	private static final int PORT=1234;
    public static void main(String[] args) throws IOException {
       
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1234.");
            System.exit(-1);
        }

        while (listening)
        	new MultiServerThread(serverSocket.accept()).start();

        serverSocket.close();
    }
}
