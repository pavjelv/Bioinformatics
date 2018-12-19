package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Task9 {
    static Integer diffCount (String p1, String p2) {
        if(p1.length() != p2.length())
            return -1;
        Integer err = 0;
        for (int i = 0; i < p1.length(); i++) {
            if(!p1.substring(i, i + 1).equals(p2.substring(i, i + 1))) {
                err++;
            }
        }
        return err;
    }
    static ArrayList<String> getPatterns (String kMer, Integer d) {
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
        for (int i = 0; i < kMer.length() - 1; i++) {
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
                    Integer errors = diffCount(kMer, pattern);
                    patterns.add(str);
                    //if(errors <= d && errors >= 0) {
                    if(pattern.length() == kMer.length())
                        result.add(pattern);
                    //}
                }
                patterns.remove(j);
                j--;
                size--;
            }
        }
//        Integer s = patterns.size();
//        for (int i = 0; i < s; i++) {
//            String str = patterns.get(i).toString();
//            if(diffCount(kMer, str) > d){
//                patterns.remove(i);
//                i--;
//                s--;
//            }
//
//        }
       return result;
    }

    static HashSet<String> motifEnumeration (ArrayList<String> dnaList, int k, int d) {
        ArrayList<String> patterns = new ArrayList<>();
        for (String dna : dnaList) {
            for (int i = 0; i < dna.length() - k + 1; i++) {
                String kMer = dna.substring(i, i + k);
                ArrayList<String> ptr = getPatterns(kMer, d);
                for (String pattern : ptr) {
                    Integer err = diffCount(pattern,kMer);
                    if(err <= d && err >= 0) {
                        Integer count = 0;
                        for (String _dna : dnaList) {
                            for (int j = 0; j < _dna.length() - k + 1; j++) {
                                Integer errors = diffCount(_dna.substring(j, j + k), pattern);
                                if (errors <= d && errors >= 0) {
                                    count++;
                                    break;
                                }
                            }
                        }
                        if (count == dnaList.size()) {
                            patterns.add(pattern);
                        }
                    }
                }
            }
        }
        return new HashSet<>(patterns);
    }

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //getPatterns("CACTGATCGACTTATC", 3);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String in = sc.readLine();

        //sc.close();

        List<String> rowValues = Arrays.asList(in.split(" ", 2));
        Integer k = Integer.valueOf(rowValues.get(0));
        Integer d = Integer.valueOf(rowValues.get(1));
        ArrayList<String> dna = new ArrayList<>();
        String input;
        while ((input = sc.readLine())!= null && !input.isEmpty()) {
            //if ()
            //if(!input.isEmpty())
                dna.add(input);
            //else break;
        }
        sc.close();
        HashSet<String> result = motifEnumeration(dna, k, d);
        for (String s : result) {
            System.out.printf(s + " ");
        }
    }

}
