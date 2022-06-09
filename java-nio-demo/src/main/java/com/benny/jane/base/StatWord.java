package com.benny.jane.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatWord {
    public static void main(String[] args) {

    }

    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return counter;
    }
}
