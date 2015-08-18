package com.company.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by compaurum on 19.05.2015.
 */
public class Sea {
    public static void main(String [] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = reader.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int k = Integer.parseInt(strings[1]);


        if (Math.ceil((double) (n * n)/2) < k)
            System.out.println("NO");
        else {
            System.out.println("YES");
            int countIsland = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n ; j++) {
                    if ((countIsland < k) && (i % 2 == j % 2)) {
                        System.out.print("L");
                        countIsland++;
                    }
                    else System.out.print("S");
                }
                if (i < n) System.out.println("");
            }
        }
    }
}
