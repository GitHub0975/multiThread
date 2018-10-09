
import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
    private Socket socket = null;

    public MultiServerThread(Socket socket) {
    	super("MultiServerThread");
    	this.socket = socket;
    }

    public void run() {

    	try {
    		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
    		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    		String inputLine;
    		inputLine = input.readLine();
    		System.out.println("message recieved:"+inputLine);
    		while (!inputLine.equals("***close***")) {
    			String outputLine="";
    		    for(int i=0;i<inputLine.length();i++) {

    		    	if(inputLine.charAt(i)>='A'&& inputLine.charAt(i)<='Z') {
    		    		outputLine+=inputLine.substring(i,i+1).toLowerCase();

    		    	}
    		    	else if(inputLine.charAt(i)>='a'&& inputLine.charAt(i)<='z') {
    		    		outputLine+=inputLine.substring(i,i+1).toUpperCase();

    		    	}
    		    	else 
    		    		outputLine+=inputLine.substring(i,i+1);
    		     }
    		    output.println(outputLine);
    		    inputLine=input.readLine();
    		    System.out.println("message recieved:"+inputLine);
    		}
    		output.println("Bye Bye!");
    		System.out.println("One thread closed");
    		output.close();
    		input.close();
    		socket.close();

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
