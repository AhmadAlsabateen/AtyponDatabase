package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Filter;
import dto.Input;
import dto.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class test2 implements Runnable {

    @Override
    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Query query= new Query();
            query.filter=new Filter();
            query.input = new Input();
            query.type="retrieve";
            query.Table="books";
            String request=objectMapper.writeValueAsString(query);
            out.println(request);
            System.out.println(Thread.currentThread().getId()+in.readLine());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
