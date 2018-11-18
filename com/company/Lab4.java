package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Lab4 {

    public static HashMap<String, String> codonTable = new HashMap<>();

    public static void initializeMap () {
        codonTable.put("AAC","N");
        codonTable.put("AAU","N");
        codonTable.put("AAA","K");
        codonTable.put("AAG","K");
        codonTable.put("ACU","T");
        codonTable.put("ACC","T");
        codonTable.put("ACA","T");
        codonTable.put("ACG","T");
        codonTable.put("AGU","S");
        codonTable.put("AGC","S");
        codonTable.put("AGA","R");
        codonTable.put("AGG","R");
        codonTable.put("AUU","I");
        codonTable.put("AUC","I");
        codonTable.put("AUA","I");
        codonTable.put("AUG","M");
        codonTable.put("CAU","H");
        codonTable.put("CAC","H");
        codonTable.put("CAA","Q");
        codonTable.put("CAG","Q");
        codonTable.put("CCU","P");
        codonTable.put("CCC","P");
        codonTable.put("CCA","P");
        codonTable.put("CCG","P");
        codonTable.put("CGU","R");
        codonTable.put("CGC","R");
        codonTable.put("CGA","R");
        codonTable.put("CGG","R");
        codonTable.put("CUU","L");
        codonTable.put("CUC","L");
        codonTable.put("CUA","L");
        codonTable.put("CUG","L");
        codonTable.put("GAU","D");
        codonTable.put("GAC","D");
        codonTable.put("GAA","E");
        codonTable.put("GAG","E");
        codonTable.put("GCU","A");
        codonTable.put("GCC","A");
        codonTable.put("GCA","A");
        codonTable.put("GCG","A");
        codonTable.put("GGU","G");
        codonTable.put("GGC","G");
        codonTable.put("GGA","G");
        codonTable.put("GGG","G");
        codonTable.put("GUU","V");
        codonTable.put("GUC","V");
        codonTable.put("GUA","V");
        codonTable.put("GUG","V");
        codonTable.put("UAU","Y");
        codonTable.put("UAC","Y");
        codonTable.put("UAA","");
        codonTable.put("UAG","");
        codonTable.put("UCU","S");
        codonTable.put("UCC","S");
        codonTable.put("UCA","S");
        codonTable.put("UCG","S");
        codonTable.put("UGU","C");
        codonTable.put("UGC","C");
        codonTable.put("UGA","");
        codonTable.put("UGG","W");
        codonTable.put("UUU","F");
        codonTable.put("UUC","F");
        codonTable.put("UUA","L");
        codonTable.put("UUG","L");

    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String pattern = reader.next();
        StringBuilder dna = new StringBuilder(pattern);
        pattern = reader.next();
        String peptide = pattern;
        String genCode = new String();
        initializeMap();
        for (int i = 0; i < dna.length() - peptide.length() * 3 + 1; i++) {
           // String rna = pattern.substring(i,i + 3);
            String rna = transcript(dna.substring(i, i + peptide.length() * 3));
            String reverserna = transcript(toRNA(dna.substring(i, i + peptide.length() * 3)));

            if (peptide.equals(translate(rna)) || peptide.equals(translate(reverserna)))
            {
                //genCode += dna.substring(i, i + peptide.length() * 3) + '\n';
                System.out.println(dna.substring(i, i + peptide.length() * 3));
            }
        }

        //System.out.printf(genCode);
    }

    public static String translate (String pattern) {
        String result = new String();
        for (int i = 0; i < pattern.length(); i+= 3) {
            result += codonTable.get(pattern.substring(i, i + 3));
        }
        return result;
    }


    public static String toRNA (String ptrn) {
        StringBuilder pattern = new StringBuilder(ptrn);
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
       return pattern.toString();
    }

    public static String transcript(String dna) {
        StringBuilder rna = new StringBuilder(dna);
        for(int i = 0; i < rna.length(); i++) {
            char c = rna.charAt(i);
            if (c == 'T') rna.replace(i, i + 1, Character.toString('U'));
        }
        return rna.toString();
    }


}
