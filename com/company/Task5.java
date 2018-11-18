package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {
        Integer[] arr = { 57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186 };
        ArrayList<Integer> massSet = new ArrayList<>(Arrays.asList(arr));
        HashMap<Integer,Long> N = new HashMap<>();
        Integer one = 1;
        N.put(0,one.longValue());
        Scanner scanner = new Scanner(System.in);
        Integer mass = scanner.nextInt();
        scanner.close();
        for (int i = 57; i <= mass; i++) {
            one = 0;
            N.put(i,one.longValue());
            for (int j = 0; j < 18; j++) {
                if(N.containsKey(i - massSet.get(j))) {
                    Long res = N.get(i) + N.get(i - massSet.get(j));
                    N.put(i,res);
                }
            }
        }
        System.out.println(N.get(mass));
    }
}
