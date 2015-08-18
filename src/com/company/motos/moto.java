package com.company.motos;

import java.util.Arrays;
//import java.lang.String;


/**
 * Created by compaurum on 19.05.2015.
 */
public class moto {
    public static void main(String [] args){
        int [] motos = new int[100];
        Arrays.fill(motos, 100);

        int x = 2;
        int y = 2;
        char a = 'a';
       "qwdqd".toLowerCase().toCharArray();
        System.out.println((int)a - 97);
        System.out.println();
        System.out.println(Math.abs(Math.log(x) + 5 + x * (1/x) -y) / Math.sqrt(Math.pow(1/x, 2) + 1));

        int sum = 0;
        for (int i = 99; i > 1; i--) {
            int part = 100 - i;
            sum += part;
           // System.out.println(part);
        }

        String [] [] k = new String[10][10];
        System.out.println(k.length);

       // System.out.println(k.substring(0, 4));
        //System.out.println(k.substring(4,8));
        //System.out.println(k.substring(8,12));
       // System.out.println(k.substring(12,16));

        int [][] array = new int [3][3];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = i*10+j;
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}
