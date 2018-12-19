package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task10 {

    static Integer hammingDistance (String p1, String p2, Integer k) {
        if(p1.length() != p2.length()) {
            return -1;
        }
        else {
            Integer error = 0;
            for (int i = 0; i < k; i++) {
                if(!p1.substring(i ,i + 1).equals(p2.substring(i, i + 1))) {
                    error++;
                }
            }
            return error;
        }
    }

    static Integer d (String pattern, String row, Integer k) {
        ArrayList<Integer> distances = new ArrayList<>();
        for (int i = 0; i < row.length() - k + 1; i++) {
            distances.add(hammingDistance(pattern, row.substring(i, i + k), k));
        }
        Integer minimum = distances.get(0);
        for (Integer distance : distances) {
            if(distance < minimum) {
                minimum = distance;
            }
        }
        return minimum;
    }

    static Integer bigD (ArrayList<String> dna, String pattern, Integer k) {
        Integer result = 0;
        for (String s : dna) {
            result += d(pattern, s, k);
        }

        return result;
    }
    static ArrayList<String> getPatterns (Integer sizeOfK) {
        ArrayList<ArrayList<String>> patterns = new ArrayList<>();
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("T");
        dictionary.add("G");
        dictionary.add("A");
        dictionary.add("C");
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            patterns.add(new ArrayList<>());
            patterns.get(i).add(dictionary.get(i));
        }
        for (int i = 0; i < sizeOfK - 1; i++) {
            Integer size = patterns.size();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < 4; k++) {
                    ArrayList<String> str = new ArrayList<>();
                    String pattern = new String();
                    for (int l = 0; l < patterns.get(j).size(); l++) {
                        str.add(patterns.get(j).get(l));
                        pattern += patterns.get(j).get(l);
                    }
                    str.add(dictionary.get(k));
                    pattern += dictionary.get(k);
                    patterns.add(str);
                    if(pattern.length() == sizeOfK)
                        result.add(pattern);
                }
                patterns.remove(j);
                j--;
                size--;
            }
        }
        return result;
    }

    static String medianString (ArrayList<String> dna, Integer k) {
        Integer dist = 10000000;
        ArrayList<String> patterns = getPatterns(k);
        String res = new String();
        for (String pattern : patterns) {
            Integer dst = bigD(dna, pattern, k);
            if (dist >= dst) {
                dist = dst;
                res = pattern;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String in = sc.readLine();
        Integer k = Integer.valueOf(in);
        ArrayList<String> dna = new ArrayList<>();
        String input;
        while ((input = sc.readLine())!= null && !input.isEmpty()) {
            dna.add(input);
        }
        sc.close();

        System.out.println(medianString(dna, k));
    }

}
