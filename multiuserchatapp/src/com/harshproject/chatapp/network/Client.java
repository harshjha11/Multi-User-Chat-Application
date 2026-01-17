package com.harshproject.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.harshproject.chatapp.utils.configReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
		socket = new Socket(configReader.getValue("SERVER_IP"),PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
//		System.out.println("Client comes");
//		System.out.println("Enter the Message Send to the Server....");
//		Scanner scanner = new Scanner(System.in);
//		String message = scanner.nextLine();
//		socket.getOutputStream();//write ke liye
//		OutputStream out = socket.getOutputStream();//Write Bytes on network
//		out.write(message.getBytes());
//		System.out.println("Message Sent to the server");
//		scanner.close();
//		out.close();
		
		
//		socket.close();
	}
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	public void readMessages() {
		worker = new ClientWorker(in,textArea);//calling a read thread
		worker.start();
	}
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Client client = new Client();
//	}
}
