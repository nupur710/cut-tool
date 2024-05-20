package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Cut cut= new Cut(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\file.tsv"));
        cut.cutTsv(1, 't');
    }

//        for (int i = 0; i < abc.length(); ) {
//            int j = i;
//            while (j < abc.length() && abc.charAt(j) != '\t' && abc.charAt(j) != '\n') {
//                j++;
//            }
//            tbsv.add(abc.substring(i, j));
//            if (j < abc.length() && (abc.charAt(j) == '\t' || abc.charAt(j) == '\n')) {
//                i = j + 1;
//            } else {
//                break;
//            }

}