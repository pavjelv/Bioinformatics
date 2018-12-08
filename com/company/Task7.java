package com.company;

import java.util.*;

public class Task7 {
    static ArrayList<Long> getCyclopeptide(ArrayList <Long> spr) {
        ArrayList<Long> result = new ArrayList<>();
        ArrayList<Long> twoPeptides = new ArrayList<>(spr);
        twoPeptides.addAll(spr);
        int s = spr.size();
        result.add(new Long(0));
        for (int iter = 0; iter < s; iter++) {  //calculating cyclospectrum
            for (int iter1 = 1; iter1 < s; iter1++) {
                List<Long> acid = twoPeptides.subList(iter, iter + iter1);
                Long res = new Integer(0).longValue();
                for (int k1 = 0; k1 < acid.size(); k1++) {
                    res += acid.get(k1);
                }
                result.add(res);
            }
        }
        Long sum = new Long(0);
        for (Long lng : spr) {
            sum += lng;
        }
        result.add(sum);
        result.sort(Comparator.naturalOrder());
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> mass = new HashMap<>();
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

        Scanner scanner = new Scanner(System.in);
        String peptide = scanner.nextLine();
        String input = scanner.nextLine();
        scanner.close();
        ArrayList<String> sp = new ArrayList<>(Arrays.asList(input.split(" ")));
        ArrayList<Long> spectrum = new ArrayList<>();
        for (String s : sp) {
            spectrum.add(Long.valueOf(s));
        }

        ArrayList<Long> spr = new ArrayList<>();
        for (int i = 0; i < peptide.length(); i++) {
            spr.add(mass.get(peptide.substring(i,i+1)).longValue());
        }

        ArrayList<Long> res = getCyclopeptide(spr);
        int out = 0;
        for (Long re : res) {
            if(spectrum.contains(re)){
                spectrum.remove(spectrum.indexOf(re));
                out ++;
            }
        }
        System.out.println(out);
    }
}
