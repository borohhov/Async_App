package com.dmitri.asyncapplication;

public class ZeroFinder {
    public static void main(String[] args) {
        System.out.println("Number of zeroes by strings: " + findZeroesByString(112400123l));
        System.out.println("Number of zeroes by division: " + findZeroesByDivision(112400123l));

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            findZeroesByString(112400123l);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time by string method: " + (endTime - startTime));
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {

            findZeroesByDivision(112400123l);
        }

        long endTime2 = System.currentTimeMillis();
        System.out.println("Time by division method: " + (endTime2 - startTime2));

    }

    public static int findZeroesByString(long inputNumber) {
        int numOfZeroes = 0;
        String inputString = String.valueOf(inputNumber);
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.substring(i, i + 1).equals("0")) {
                numOfZeroes++;
            }
        }
        return numOfZeroes;
    }

    public static int findZeroesByDivision(long inputNumber) {
        int numOfZeroes = 0;
        long num = inputNumber;
        while (num > 10) {
            if (num % 10 == 0) numOfZeroes++;
            num = num / 10;
        }
        return numOfZeroes;
    }


}
