package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Filter;
import dto.Input;
import dto.Query;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket serverSocket = null;

    public static void main(String[] args) {

        addRootAdmin();
        try {
            serverSocket = new ServerSocket(80);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                ClientHandler.getInstance(client).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addRootAdmin() {

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    addRootAdminP();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    private static boolean adminExist() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Query query = new Query();
            query.filter = new Filter();
            query.input = new Input();
            query.type = "retrieve";
            query.Table = "users";
            query.filter.fields.put("username", "ahmad");
            query.filter.fields.put("role", "admin");
            String request = objectMapper.writeValueAsString(query);
            out.println(request);
            String response = in.readLine();
            User[] responseList = objectMapper.readValue(response, User[].class);
            socket.close();

            if (responseList.length == 0) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private static void addRootAdminP() throws IOException {
        if (adminExist()) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Socket socket = null;
        try {

            socket = new Socket("localhost", 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Query query = new Query();
            query.filter = new Filter();
            query.input = new Input();
            query.input.fields.put("username", "ahmad");
            query.input.fields.put("password", "1234");
            query.input.fields.put("role", "admin");

            query.type = "create";
            query.Table = "users";
            String request = objectMapper.writeValueAsString(query);
            out.println(request);
            System.out.println(request);
            System.out.println(Thread.currentThread().getId() + in.readLine());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
