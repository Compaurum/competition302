package com.company.aAlogorithm;

import java.util.*;

/**
 * Created by compaurum on 26.05.2015.
 */
public class AAlgorithm {
    final static int MAXWIDTH = 10;
    final static int MAXLENGTH = 10;
    public static void main(String [] args){
        Date start = new Date();

        int [][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
        //System.out.println(table.length * table[0].length);
        Cell startPoint = new Cell(2, 1);
        Cell endPoint = new Cell(3, 5);
        HashMap<Integer, Cell> openMap = new HashMap<>();
        HashSet<Integer> closedSet = new HashSet<>();

        openMap.put(startPoint.getCellNumber(), startPoint);

        while (!openMap.isEmpty() && !openMap.containsKey(endPoint.getCellNumber())) {
            Iterator<Map.Entry<Integer, Cell>> iterator = openMap.entrySet().iterator();
            Cell min = iterator.next().getValue();
            while (iterator.hasNext()) {
                Cell tempCell = iterator.next().getValue();
                Integer tempInt = tempCell.F;
                if (min.F > tempInt) {
                    min = tempCell;
                }
            }
            //System.out.println(endPoint.getCellNumber() +"  " +min.getCellNumber() + "    " +min.x + " " + min.y);
            findIteration(min, openMap, closedSet, table, endPoint);
        }

        //System.out.println(openMap.isEmpty());
        if (openMap.containsKey(endPoint.getCellNumber())){
            //System.out.println("fiewjoifjweoije");
            printAnswer(openMap.get(endPoint.getCellNumber()), table);
        }
        Date end = new Date();

        for (int i = 0; i <table.length ; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
        System.out.println(end.getTime()-start.getTime());
    }

    public static void printAnswer(Cell cell, int[][] table){
        Cell tempCell = cell;
        while (tempCell.parent != null){
            System.out.println(tempCell.x + " " + tempCell.y);
            table[tempCell.x][tempCell.y] = 2;
            tempCell = tempCell.parent;
        }
        System.out.println(tempCell.x + " " + tempCell.y);
        table[tempCell.x][tempCell.y] = 2;
        System.out.println();
        System.out.println();
    }

    public static boolean findIteration(Cell cell, HashMap<Integer, Cell> open, HashSet close, int [][] table, Cell endPoint){

        for (int i = cell.x-1; i <= cell.x+1; i++) {
            for (int j = cell.y - 1; j <= cell.y + 1; j++) {
                try {
                    if (!(i >= 0 && i < MAXLENGTH && j >= 0 && j < MAXWIDTH ))
                        continue;
                    if (!(table[i][j] != 1 && (cell.x != i || cell.y != j)))
                        continue;
                    Cell tempCell = new Cell(i, j);
                    if (close.contains(tempCell.getCellNumber()))
                        continue;

                    int g = (int) (Math.sqrt((cell.x - i) * (cell.x - i) + (cell.y - j) * (cell.y - j)) * 10);
                    int h = (Math.abs(endPoint.x - tempCell.x) + Math.abs(endPoint.y - tempCell.y))*10;
                    if (!open.containsKey(tempCell.getCellNumber())) {
                        open.put(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                       // System.out.println( g + " " + h);
                        System.out.println("Put Cell "+tempCell.getCellNumber()+" in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    } else if (cell.G+g+h < open.get(tempCell.getCellNumber()).F) {
                        open.replace(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                        System.out.println("Replace cell "+tempCell.getCellNumber()+"in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println(i + " " + j + 34534534);
                }

            }
        }
        close.add(cell.getCellNumber());
        open.remove(cell.getCellNumber());

        return false;
    }
}

class Cell {
    int x;
    int y;
    int F = 0;
    int G = 0;
    Cell parent = null;

    public Cell(int x, int y, int g, int h,  Cell parent) {
        this.x = x;
        this.y = y;
        this.F = g + h;
        this.G += g;
        this.parent = parent;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCellNumber(){
        return this.x * AAlgorithm.MAXWIDTH + this.y;
    }
}
