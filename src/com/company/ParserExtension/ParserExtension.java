package com.company.ParserExtension;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            in.nextLine();
            map.put(EXT, MT);
        }

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            String extension = FNAME.substring(FNAME.lastIndexOf(".")+1, FNAME.length());
            System.out.println(extension);
            System.out.println(FNAME.lastIndexOf("."));
            if (map.containsKey(extension))
                System.out.println(map.get(extension));
            else
                System.out.println("UNKNOWN");
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        //System.out.println("UNKNOWN"); // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
    }
}