package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.Query;
import server.crud.CRUDFactory;
import server.crud.CrudOperation;
import server.crud.Retrieve;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private ObjectMapper objectMapper ;
    private PrintWriter  out;
    private BufferedReader in;

    private ClientHandler(Socket clientSocket) {
        System.out.println("working");
        this.clientSocket = clientSocket;
        try {
             out = new PrintWriter(clientSocket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            objectMapper= new ObjectMapper();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            String request=in.readLine();
            Query query=objectMapper.readValue(request, Query.class);
            CRUDFactory crudFactory = new CRUDFactory();
            CrudOperation operation = crudFactory.getInstance(query);
            ArrayList list= (ArrayList) operation.perform();
            if (list != null){
                String response = objectMapper.writeValueAsString(list);
                out.println(response);
            }
            clientSocket.close();

        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Thread getInstance(Socket client){

        return new Thread(new ClientHandler(client));
    }

}
