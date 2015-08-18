package com.company;

import java.io.IOException;

public class Main {

    static int i = 5;
    static {i = 6;}
    public static void main(String[] args) throws IOException {

        int i = 9;
        while (i < 10){
            System.out.println(i-=1000);
        }

        String str = "spagetti";
        PigLatin pig = new PigLatin();
        System.out.println(pig.translate(str));
    }

    public static class PigLatin{

        String translate(String str){
            str = str.toLowerCase();
            String newStr;
            //good code
            char [] array = str.toCharArray();
            int i;
            for (i = 0; i < array.length || isVowel(array[i]); i++);
            return null; /// should be some  other valuem not null
        }

        boolean isVowel(char a){

            if(a == ('a') || a == ('e') || a == ('i') || a == ('o') || a == ('u') || a == ('y'))
                return true;
            else
                return false;
        }
    }
}
