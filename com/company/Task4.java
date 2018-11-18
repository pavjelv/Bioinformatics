package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

class Task4 {

    public static HashMap<String,Integer> mass;

    public static void initializeMap(){
        mass = new HashMap<>();
        mass.put("G", 57);
        mass.put("A", 71);
        mass.put("S", 87);
        mass.put("P", 97);
        mass.put("V", 99);
        mass.put("T", 101);
        mass.put("C", 103);
        mass.put("I", 113);
        mass.put("L", 113);
        mass.put("N", 114);
        mass.put("D", 115);
        mass.put("K", 128);
        mass.put("Q", 128);
        mass.put("E", 129);
        mass.put("M", 131);
        mass.put("H", 137);
        mass.put("F", 147);
        mass.put("R", 156);
        mass.put("Y", 163);
        mass.put("W", 186);
    }

    public static void main(String[] args) {
        initializeMap();
        ArrayList<Long> output = new ArrayList<>();
        Integer zero = 0;
        output.add(zero.longValue());
        Scanner scanner = new Scanner(System.in);
        String peptide = scanner.next();
        scanner.close();
        String clone = new String (peptide);
        int size = clone.length();
        peptide += clone;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                CharSequence acid = peptide.subSequence(i,i + j);
                long res = 0;
                for (int k = 0; k < acid.length(); k++) {
                    res += mass.get(Character.toString(acid.charAt(k)));
                }
                output.add(res);
            }
        }
        long res = 0;
        for (int i = 0; i < size; i++) {
            res+= mass.get(Character.toString(clone.charAt(i)));
        }
        output.add(res);
        output.sort(Comparator.naturalOrder());
        for (Long aLong : output) {
            System.out.println(aLong + " ");
        }
    }
}
