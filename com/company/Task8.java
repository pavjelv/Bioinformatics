package com.company;


import java.util.*;

class Task8 {

    static int linearScore(ArrayList<Integer> peptide, ArrayList<Integer> spectrum) {
        ArrayList<Integer> tmp2 = new ArrayList<>(spectrum);
        int s = peptide.size();
        int result = 1;
        for (int iter = 0; iter < s; iter++) {
            for (int iter1 = 1; iter1 < s; iter1++) {
                if (iter + iter1 <= s) {
                    List<Integer> acid = peptide.subList(iter, iter + iter1);
                    Integer res = 0;
                    for (int k1 = 0; k1 < acid.size(); k1++) {
                        res += acid.get(k1);
                    }
                    if (tmp2.contains(res)) {
                        tmp2.remove(tmp2.indexOf(res));
                        result ++;
                    }
                }
            }
        }
        return result;
    }

    static void trim (ArrayList<ArrayList<Integer>> leaderboard, ArrayList<Integer> spectrum, Integer N) {
        if (leaderboard.size() < N)
            return;
        leaderboard.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (linearScore(o1, spectrum) > linearScore(o2, spectrum))
                    return -1;
                else if (linearScore(o1, spectrum) < linearScore(o2, spectrum))
                    return 1;
                else return 0;
            }
        });
        boolean flag = true;
        int i = 0;
        while (flag) {
            if (N + i == leaderboard.size())
                flag = false;
            else if (linearScore(leaderboard.get(N - 1 + i), spectrum) != linearScore(leaderboard.get(N + i), spectrum)){
                flag = false;
            }
            else i++;
        }

        int s = leaderboard.size();
        for (int j = N - 1 + i; j < s; j++) {
            leaderboard.remove(j);
            j --;
            s--;
        }
    }

    static void expand (ArrayList<ArrayList<Integer>> leaderboard, Integer [] arr) {
        int size = leaderboard.size();
        for(int i = 0; i < size; i++) {
            ArrayList<Integer> peptide = leaderboard.get(i);
            for(int j = 0; j < arr.length; j++) {
                ArrayList<Integer> newPeptide = new ArrayList<>(peptide);
                newPeptide.add(arr[j]);
                leaderboard.add(newPeptide);
            }
            leaderboard.remove(i);
            i--;
            size--;
        }
    }

    static int parentMass (ArrayList<Integer> spectrum) {
        int parentMass = 0;
        for (Integer integer : spectrum) {
            if(integer > parentMass)
                parentMass = integer;
        }
        return parentMass;
    }

    static int mass (ArrayList<Integer> peptide) {
        int mass = 0;
        for (Integer integer : peptide) {
            mass += integer;
        }
        return mass;
    }



    public static void main(String[] args) {
        Integer[] arr = { 57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186 };
        Scanner scanner = new Scanner(System.in);
        Integer N = Integer.valueOf(scanner.nextLine());
        String input = scanner.nextLine();
        scanner.close();
        ArrayList<String> sp = new ArrayList<>(Arrays.asList(input.split(" ")));
        ArrayList<Integer> spectrum = new ArrayList<>();
        for (String s : sp) {
            spectrum.add(Integer.valueOf(s));
        }
        ArrayList<ArrayList<Integer>> leaderboard = new ArrayList<>();
        ArrayList<Integer> leaderPeptide = new ArrayList<>();
        leaderboard.add(new ArrayList<>());

        while (leaderboard.size() > 0){
            expand(leaderboard, arr);
            int s = leaderboard.size();
            for (int i = 0; i < s; i++) {
                ArrayList<Integer> peptide = leaderboard.get(i);
                if (mass(peptide) == parentMass(spectrum)){
                    if(linearScore(peptide,spectrum) > linearScore(leaderPeptide, spectrum)){
                        leaderPeptide = peptide;
                        leaderboard.remove(peptide);
                        i--;
                        s--;
                    }
                }
                else if (mass(peptide) > parentMass(spectrum)) {
                    leaderboard.remove(peptide);
                    i--;
                    s--;
                }
            }
            trim(leaderboard,spectrum, N);
        }

        StringBuilder output = new StringBuilder();
        for (Integer integer : leaderPeptide) {
           output.append(integer).append("-");
        }
        output.deleteCharAt(output.length() - 1);
        System.out.println(output);
    }
}
