package com.company;

import java.util.*;

class Task6 {
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

    static boolean isLinearSpectrumMatches(ArrayList<Long> spectrum, ArrayList<Long> peptide) {
        ArrayList<Long> twoPeptides = new ArrayList<>(peptide);
        int s = twoPeptides.size();
        ArrayList<Long> tmp2 = new ArrayList<>(spectrum);
        for (int iter = 0; iter < s; iter++) {  //calculating cyclospectrum
            for (int iter1 = 1; iter1 < s; iter1++) {
                if (iter + iter1 <= s) {
                    List<Long> acid = twoPeptides.subList(iter, iter + iter1);
                    Long res = new Long(0);
                    for (int k1 = 0; k1 < acid.size(); k1++) {
                        res += acid.get(k1);
                    }
                    if (tmp2.contains(res)) {
                        tmp2.remove(tmp2.indexOf(res));
                    } else {
                       return false;
                    }
                }
            }
        }
       return true;
    }

    public static void main(String[] args) {
        Integer[] arr = { 57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186 };
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        ArrayList<String> sp = new ArrayList<>(Arrays.asList(input.split(" ")));
        ArrayList<Long> spectrum = new ArrayList<>();
        for (String s : sp) {
            spectrum.add(Long.valueOf(s));
        }
        ArrayList<ArrayList<Long>> set = new ArrayList<>();
        for (Integer integer : arr) {
            if (spectrum.contains(integer.longValue())) {
                ArrayList<Long> temp = new ArrayList<>();
                temp.add(integer.longValue());
                set.add(temp);
            }
        }
        for(int i = 0; i < spectrum.size(); i++) {
            int size = set.size();
            for(int j = 0; j < size; j++) {
                ArrayList<Long> tmp = set.get(j);
                int addedElems = 0;
                for (int k = 0; k < arr.length; k++) {
                    if (spectrum.contains(arr[k].longValue())) {
                        tmp.add(arr[k].longValue());
                        boolean flag = true;
                        if (tmp.size() > 2) {
                             flag = isLinearSpectrumMatches(spectrum, tmp);
                        }
                        Long sum = new Long(0);
                        if (flag) {
                            for (Long integer : tmp) {
                                sum += integer;
                            }
                            if(spectrum.contains(sum)) {
                                set.add(new ArrayList<>(tmp));
                                addedElems++;
                            }
                        }
                        tmp.remove(tmp.size() - 1);
                    }
                }
                if(addedElems != 0) {
                    set.remove(j);
                    j--;
                    size--;
                }
            }

        }
        for (int i = 0; i < set.size(); i++) {
            if (!spectrum.equals(getCyclopeptide(set.get(i)))){
                set.remove(i);
                i--;
            }
        }
        int maxSize = set.get(set.size() - 1).size();
        for (int p = 0; p < set.size(); p++) {
            if(set.get(p).size() != maxSize){
                set.remove(p);
                p--;
            }
        }
        StringBuilder out = new StringBuilder();
        for (ArrayList<Long> integerArrayList : set) {
                for (Long integer : integerArrayList) {
                    out.append(integer).append("-");
                }
                out.deleteCharAt(out.length() - 1);
                out.append(" ");
                System.out.printf(out.toString());
                out = new StringBuilder();
            }

    }
}
