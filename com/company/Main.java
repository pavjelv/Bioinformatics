package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main1(String[] args) {
	// write your code here
        String genome = new String();
        StringBuilder pattern = new StringBuilder();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a genome ");
        genome = reader.next();
        //reader.close();
        System.out.println("Enter a pattern ");
        pattern.append(reader.next());
        reader.close();
        int i = 0;
        int j = 0;
        do{
            i = pattern.indexOf(genome,i);
            if (i != -1) {
                j++;
                if (i < pattern.length() - 1)
                    i++;
            }

        } while (i != -1);
        System.out.println(j);
    }

    public static void main2(String[] args) {
        StringBuilder pattern = new StringBuilder();
        Scanner reader = new Scanner(System.in);
        pattern.append(reader.next());
        int quantity = reader.nextInt();
        reader.close();
        ArrayList <String> arr = new ArrayList<>();
        int num = 0;
        int currnum = 0;
        for (int i = 0; i < pattern.length() - quantity - 1; i++){
            String str = pattern.substring(i,i + quantity);
            currnum = 0;
            for (int j = 0; j < pattern.length() - quantity - 1; j++){
                String newStr = pattern.substring(j,j + quantity);
                if (str.equals(newStr)) currnum ++;
            }
            if (currnum > num) {
                num = currnum;
                arr.clear();
                arr.add(str);
            }
            if (currnum == num){
               int k = 0;
                for (String s : arr) {
                    if (s.equals(str)) k++;
                }
                if (k == 0) arr.add(str);
            }
        }
        for (String s : arr) {
            System.out.printf(s + " ");
        }
    }

    public static void main(String[] args) {
        StringBuilder pattern = new StringBuilder();
        Scanner reader = new Scanner(System.in);
        pattern.append(reader.next());
        reader.close();
        pattern.reverse();
        for (int i = 0; i < pattern.length(); i++){
                char c = pattern.charAt(i);
                switch (c){
                    case 'A': c = 'T'; break;
                    case 'T': c = 'A'; break;
                    case 'G': c = 'C'; break;
                    case 'C': c = 'G'; break;
                }
                pattern.replace(i,i+1,Character.toString(c));
        }
        System.out.println(pattern);
    }
}
