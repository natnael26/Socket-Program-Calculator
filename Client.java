/**
 * Client sends an expression to the server.
 * Author : Cho Seo Hyung.
 * Socket program 1.
 * Last changed : October 06, 2015.
 */
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
	public static void main (String[] args)
	{
		Socket clientSocket=null;
		DataInputStream is = null;
		PrintStream os = null;
		DataInputStream inputLine=null;
		Scanner readFile = null;
		try{ //read data file.
			readFile = new Scanner(new File("serverinfo.dat")); //read "serverinfo.dat" file.
			int portnumber = readFile.nextInt();
			readFile.nextLine();
			String address = readFile.nextLine();
			clientSocket= new Socket(address,portnumber); //make a client socket.
			os= new PrintStream(clientSocket.getOutputStream()); //to send request to server.
			is= new DataInputStream(clientSocket.getInputStream()); //to get reply from server.
			inputLine=new DataInputStream(new BufferedInputStream(System.in));
		}catch(UnknownHostException e){ //catch error when unknown host access.
			System.err.println("Don't know about host.");
		}catch(IOException e){ //catch error when unconnected.
			System.err.println("Couldn't get I/O for the connection to host.");
		}catch(Exception e){
			System.err.println("error");
		}
		if(clientSocket!=null&&os!=null&&is!=null){
			try{
				System.out.println("The client started. Type any text. To quit it type 'OK'.");
				String responseLine;
				os.println(inputLine.readLine()); //send string to server for calculate.
				while((responseLine=is.readLine())!=null)
				{
					System.out.println(responseLine);//get response from server.
					if(responseLine.indexOf("OK")!=-1)
					{
						break; //quit the program when I input "OK".
					}
					os.println(inputLine.readLine());
				}
				os.close(); //close to send request to server.
				is.close(); //close to send reply from server.
				clientSocket.close(); //close client socket.
			}catch(UnknownHostException e){ //catch exceptions.
				System.err.println("Trying to connect to unknown host: "+e);
			}catch(IOException e){
				System.err.println("IOExcption: "+e);
			}
		}
	}
}
