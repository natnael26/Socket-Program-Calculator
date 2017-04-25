/**
 * Server interprets the expression, executes calculation, then send the result back to client.
 * Author : Cho Seo Hyung.
 * Socket program 1.
 * Last changed : October 06, 2015.
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	public static void main (String args[])
	{
		ServerSocket echoServer=null;
		String line;
		DataInputStream is;
		PrintStream os;
		Socket clientSocket=null;
		try{
			echoServer=new ServerSocket(222);
		}catch(IOException e){
			System.out.println(e);
		}
		System.out.println("The server started. To stop it press <CTRL><C>.");
		try{
			clientSocket=echoServer.accept(); //connection with client.
			is=new DataInputStream(clientSocket.getInputStream()); //to get request from client.
			os= new PrintStream(clientSocket.getOutputStream()); //to send reply to client.
			while(true)
			{
				line = is.readLine(); //get a linear equation
				Calculator myCal = new Calculator(); //make Calculater object;
				int ans1, ans2;
				ans1 = myCal.except(line);
				ans2 = myCal.postfix(line);

				if (ans1==0) //check exception
				{
					os.println("Error : Incorrect format.");
				}
				else if (ans2==0) //check exception
				{
					os.println("Error : Incorrect format.");
				}
				else{
					if(myCal.calculate()==0){ //check exception
						os.println("Error : divided by zero.");
					}
					else
						os.println(myCal.calculate()); //server replies to client.
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
