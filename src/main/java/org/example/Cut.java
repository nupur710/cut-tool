package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cut {
    private BufferedReader br;
    private File file;

    Cut(File file) throws FileNotFoundException {
        this.file= file;
        br= new BufferedReader(new FileReader(file));
    }

    private List<String> readFromBr(String d) throws IOException {
        List<String> tt= new ArrayList<>();
        String line;
        br.mark(100000);
        while((line = br.readLine()) != null) {
            String[] x= line.split(d);
            tt.addAll(Arrays.asList(x));
        }
        return tt;
    }

    private List<String> readFromBr() throws IOException {
        List<String> tt= new ArrayList<>();
        String line;
        br.mark(100000);
        while((line = br.readLine()) != null) {
            String[] x= line.split("\t");
            tt.addAll(Arrays.asList(x));
        }
        return tt;
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


    public void cut(int n) throws IOException {
        List<String> jj= readFromBr(",");
        int columnCount= columnCount();
        //print(jj, columnCount , n);
        List<String> s1= add(jj, columnCount , n, 8);
        printSingle(s1);
    }

    private void printSingle(List<String> s1) {
        for(int i= 0; i<s1.size(); i++) System.out.println(s1.get(i));
    }

    public List[] cut(String head, int... n) throws IOException {
        List<String> jj= readFromBr(",");
        int columnCount= columnCount();
        ArrayList<String>[] arr= new ArrayList[n.length];
        int h= Integer.parseInt(head);
        for(int i= 0; i < arr.length; i++) {
            arr[i]= add(jj, columnCount, n[i], h);
        }
        String[][] s= structure(arr);
        printMultiple(s, n.length, arr[0].size());
        return arr;
    }

    private String[][] structure(List[] data) {
        int n= data.length;
        int m= data[0].size();
        Object[][] fill= new String[data.length][data[0].size()];
        for(int i= 0; i < data.length; i++) {
            for(int j= 0; j < data[0].size(); j++) {
                fill[i][j]= data[i].get(j);
            }
           // printMultiple((String[][])fill, n, m);
        } return (String[][]) fill;
    }
    private int columnCount() throws IOException {
        br.reset();
        String column= br.readLine();
        int count= 0;
        for(int i= 0; i<column.length(); i++) {
            if(column.charAt(i) == '\t' || column.charAt(i) == ',') count++;
        } return count+1;
    }

    private void printMultiple(String[][] s, int r, int c) {
        for(int j= 0; j < c; j++) {
            for(int i= 0; i < r; i++) {
                System.out.print(s[i][j] + "\t");
            }
            System.out.println();
        }
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

    private ArrayList<String> add(List<String> str, int columns, int n) {
        ArrayList<String> arr= new ArrayList<>();
        if(n > columns) throw new IllegalArgumentException(n + " exceeds columns");
        for(int i= n; i < str.size(); ) {
            arr.add(str.get(i));
            i= i+(columns);
        } return arr;
    }

    private ArrayList<String> add(List<String> str, int columns, int n, int head) {
        ArrayList<String> arr= new ArrayList<>();
        if(n > columns) throw new IllegalArgumentException(n + " exceeds columns");
        for(int i= n; i < head*columns; ) {
            arr.add(str.get(i));
            i= i+(columns);
        } return arr;
    }
}
