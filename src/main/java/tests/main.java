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
import java.net.UnknownHostException;
import java.util.ArrayList;

public class main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Thread t1 = new Thread(new test());
        t1.start();

    }


}
