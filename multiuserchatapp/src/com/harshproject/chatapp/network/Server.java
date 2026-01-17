package com.harshproject.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.harshproject.chatapp.utils.configReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();//contains all the client socket
	
	public Server() throws IOException {
		int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server start and waiting for the clients to join...");
		handleClientRequest();
		
	}
	//Multiple client handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();//Handshaking
			//per client per thread
			ServerWorker serverWorker = new ServerWorker(clientSocket,this); // creating a new worker/thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	
	
	
	
	//Single client
//	public Server() throws IOException {
//		int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
//		serverSocket = new ServerSocket(PORT);
//		System.out.println("Server started and waiting for the client connection....");
//		Socket socket = serverSocket.accept();//Handshaking
//		System.out.println("Client joins the server");
//		InputStream in = socket.getInputStream();//read input from the network
//		byte arr[] = in.readAllBytes();
//		String str = new String(arr);//Bytes convert into string
//		System.out.println("Message Recieved from the Client: "+str);
//		in.close();
//		socket.close();
//	}
	public static void main(String[] args) throws IOException {
		Server server = new Server();
	}
}
