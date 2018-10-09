import java.io.*;
import java.net.*;

public class Client {

	private static InetAddress host;
	private static final int PORT=1234;
    public static void main(String[] args) throws IOException {

        Socket link = null;
        PrintWriter output = null;
        BufferedReader in = null;

        try {
        	host=InetAddress.getLocalHost();
        	link = new Socket(host, PORT);
        	output = new PrintWriter(link.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Host.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        do {
			System.out.println("Enter transfered message:");
			fromUser=stdIn.readLine();
			output.println(fromUser);
			fromServer=in.readLine();
			System.out.println("SERVER>"+fromServer);
		}while(!fromUser.equals("***close***"));
        
     

        output.close();
        in.close();
        stdIn.close();
        link.close();
    }
}
