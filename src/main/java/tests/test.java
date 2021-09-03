package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.Filter;
import dto.Input;
import dto.Query;
import entities.Book;
import services.FileAccessService;
import services.FileAccessServiceFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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
            query.input.fields.put("name","World Navigation");
            query.input.fields.put("description","Navigation Book");
            query.input.fields.put("fileName","World_Navigation_Game_Code_Report.pdf");
            query.input.fields.put("authorId",1);
            query.input.fields.put("categeryId",1);



            query.type="create";
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
