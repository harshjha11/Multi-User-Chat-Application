package com.harshproject.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread==worker
//Worker need a job to perform 
//for job u give  a runnable
//once a job is created via runnable so write the job logic inside a run function 
//assign the job to a thread
//public class ServerWorker implements Runnable{
public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException {
		this.server=server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream();//client data read
		out= clientSocket.getOutputStream();//client data write
		System.out.println("New Client Comes");
	}
	@Override
	public void run() {
		//job to perform
		//logic
		//read data from the client and broadcast tha data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true) {
			
			line = br.readLine(); // needs \n
			if (line == null) {
			    System.out.println("Client disconnected or sent null");
			    break;
			}
			System.out.println("Line Read..." + line);
			if (line.equalsIgnoreCase("quit")) {
			    break; // client chat ended
			}
				
				//out.write(line.getBytes());//clinet send
				//broadcast to all client
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());//broadcast
				}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}}
	}
	
}
