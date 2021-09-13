package tests;

import java.io.IOException;

public class main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Thread t1 = new Thread(new test());
        t1.start();

    }


}
