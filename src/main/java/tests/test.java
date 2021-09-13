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

public class test implements Runnable {


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
            query.input.fields.put("username","ahmad");
            query.input.fields.put("password","1234");
            query.input.fields.put("role","admin");



            query.type="create";
            query.Table="users";
            String request=objectMapper.writeValueAsString(query);
            out.println("{\"type\":\"create\",\"Table\":\"users\",\"filter\":{\"fields\":{}},\"input\":{\"fields\":{\"password\":\"1235\",\"role\":\"admin\",\"username\":\"ahmad\"}},\"table\":\"users\"}");
            System.out.println(request);
            System.out.println(Thread.currentThread().getId()+in.readLine());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
