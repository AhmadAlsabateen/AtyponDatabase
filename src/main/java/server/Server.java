package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket serverSocket=null;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(80);

            while (true){
                Socket client = serverSocket.accept();
                System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                ClientHandler.getInstance(client).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
