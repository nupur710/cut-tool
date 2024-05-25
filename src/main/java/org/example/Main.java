package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String fields= args[1];
        System.out.println(fields);

        Cut cut= new Cut(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\fourchords.csv"));
//        cut.cut(0);
        cut.cut("5", 0,1);
    }
}