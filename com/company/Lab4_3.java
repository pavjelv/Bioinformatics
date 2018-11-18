package com.company;

import java.util.Scanner;

public class Lab4_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long length = scanner.nextInt();
        scanner.close();
        System.out.println(length * (length - 1));
    }
}
