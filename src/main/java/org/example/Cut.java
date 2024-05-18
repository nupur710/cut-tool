package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cut {

    private File file;

    Cut(File file) {
        this.file = file;
    }

    public String readFile() throws IOException {
        StringBuilder text = new StringBuilder();
        int c;
        FileReader fileReader = new FileReader(file);
        while ((c = fileReader.read()) != -1) {
            text.append((char) c);
        }
        return text.toString();
    }

    public void cutTsv(int n) throws IOException {
        String text = readFile();
        int rows= rowCount(text);
        int colums= columnCount(text);
        List<String> str= buildArray(text);
        print(str, colums, n);
    }

    private int rowCount(String text) {
        int rows= 0;
        for(int i= 0; i < text.length(); i++) {
            if(text.charAt(i) == '\n') rows++;
        } return rows+1;
    }

    private int columnCount(String text) {
        int column= 0;
        for(int i= 0; i< text.length(); i++) {
            if(text.charAt(i) == '\t') column++;
            if(text.charAt(i) == '\n') break;
        }
         return column+1;
    }

    private List<String> buildArray(String text) {
        ArrayList<String> arr= new ArrayList<>();
        String[] s1= text.split("\n");
        for(String s : s1) {
            String[] s2= s.split("\t");
            arr.addAll(Arrays.asList(s2));
        } return arr;
    }

    private void print(List<String> str, int columns, int n) {
        if(n > columns) throw new IllegalArgumentException(n + " exceeds columns");
        for(int i= n; i < str.size(); ) {
            System.out.println(str.get(i));
            i= i+(columns);
        }
    }
}
