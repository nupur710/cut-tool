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

    public void cutTsv(int n, char c) throws IOException {
        String text = readFile();
        int rows= rowCount(text);
        int colums= columnCount(text, c);
        List<String> str= buildArray(text, c);
        print(str, colums, n);
    }

    private int rowCount(String text) {
        int rows= 0;
        for(int i= 0; i < text.length(); i++) {
            if(text.charAt(i) == '\n') rows++;
        } return rows+1;
    }

    private int columnCount(String text, char c) {
        int column= 0;
        if (c == 't') {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\t') column++;
                if (text.charAt(i) == '\n') break;
            }
        } else if (c == 'c') {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ',') column++;
                if (text.charAt(i) == '\n') break;
            }
        } return column+1;
    }

    private List<String> buildArray(String text, char c) {
        ArrayList<String> arr= new ArrayList<>();
        String[] s2= null;
        if(c == 't') {
            String[] s1= text.split("\n");
            for(String s : s1) {
                s2= s.split("\t");
                arr.addAll(Arrays.asList(s2));
            } }
        else if(c == 'c') {
            String[] s1= text.split("\r\n");
            for(String s : s1) {
                s2 = s.split(",");
                arr.addAll(Arrays.asList(s2));
            } }
        return arr;
    }

//    private List<String> buildArray(String text, char c) {
//        ArrayList<String> arr= new ArrayList<>();
//        String[] s1= text.split("\n");
//        String[] s2= null;
//        for(String s : s1) {
//            if(c == 'c') s2= s.split(",");
//            else if(c == 't') s2= s.split("\t");
//            arr.addAll(Arrays.asList(s2));
//        }  return arr;
//    }

    private void print(List<String> str, int columns, int n) {
        if(n > columns) throw new IllegalArgumentException(n + " exceeds columns");
        for(int i= n; i < str.size(); ) {
            System.out.println(str.get(i));
            i= i+(columns);
        }
    }
}
