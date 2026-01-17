# Multi-User Chat Application

A Java Swing–based desktop chat application that allows multiple users to register, log in, and communicate with each other in real time using a client–server architecture.

---

## 📌 Project Overview

This project implements a real-time multi-user chat system using **Java Sockets** and **Multithreading**.  
Users must register and log in before accessing the chat interface.  
All messages sent by a user are broadcast to every connected client.

---

## ✨ Features

- User registration and login
- Secure authentication using encrypted passwords
- Real-time multi-user chat
- GUI built using Java Swing
- Client–server architecture using TCP sockets
- Multi-threaded server to handle multiple clients simultaneously
- MySQL database integration using JDBC
- Configuration-based setup using properties file

---

## 🛠️ Technologies Used

- **Java SE**
- **Swing (JFrame, JTextArea, JTextField, JButton)**
- **TCP/IP Sockets (ServerSocket, Socket)**
- **Multithreading**
- **JDBC**
- **MySQL**
- **MD5 Encryption**

---

## 🧱 Project Architecture

src/
└── com.harshproject.chatapp
├── dao → Database access layer
├── dto → Data Transfer Objects
├── network → Client & Server socket logic
├── utils → Configuration & encryption utilities
└── views → Swing GUI screens


---

## 🔐 Authentication Flow

1. User registers with username & password  
2. Password is encrypted using MD5 before storing in database  
3. On login, credentials are validated from MySQL  
4. Successful login opens the chat dashboard  

---

## 💬 Chat Flow

1. Server starts and listens on a port  
2. Each client connection runs on a separate thread  
3. Messages from one client are broadcast to all connected clients  

---

## 🗄️ Database

- MySQL database used for user authentication
- JDBC used for database connectivity
- DAO pattern implemented for clean separation of logic

---

## 🔮 Future Enhancements

- Private (one-to-one) messaging
- Online/offline user status
- Stronger encryption (SHA-256 / BCrypt)
- File sharing support
- Migration to Maven/Gradle

---

## 👨‍💻 Author

**Harsh Jha**  
GitHub: https://github.com/harshjha11

---

## 📄 License

This project is for learning and educational purposes.
