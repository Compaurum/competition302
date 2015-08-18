package com.company.Labyrinth;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    static char[][] wasThereTable;
    static int MAXWIDTH = 10;
    static int MAXLENGTH = 10;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.
        MAXWIDTH = C;
        MAXLENGTH = R;
        char[][] table = new char[R][C];
        wasThereTable = new char[R][C];
        for (int i = 0; i < wasThereTable.length; i++)
            Arrays.fill(wasThereTable[i], 'N');
        Cell startPoint = null;
        Cell endPoint = null;
        // game loop
        while (true) {
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.
            for (int i = 0; i < R; i++) {
                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                table[i] = ROW.toCharArray();
                if (ROW.indexOf("T") >= 0) {
                    startPoint = new Cell(i, ROW.indexOf("T"));
                }
                if (ROW.indexOf("C") >= 0) {
                    endPoint = new Cell(i, ROW.indexOf("C"));
                }
            }

            //int [][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
            //System.out.println(table.length * table[0].length);

            HashMap<Integer, Cell> openMap = new HashMap<>();
            HashSet<Integer> closedSet = new HashSet<>();

            openMap.put(startPoint.getCellNumber(), startPoint);

            if (endPoint != null) {
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
                if (openMap.containsKey(endPoint.getCellNumber())) {
                    //System.out.println("fiewjoifjweoije");
                    printAnswer(openMap.get(endPoint.getCellNumber()), table);
                }

            }

            else while (!openMap.isEmpty() ) {
                Iterator<Map.Entry<Integer, Cell>> iterator = openMap.entrySet().iterator();
                Cell min = iterator.next().getValue();
                //System.out.println(endPoint.getCellNumber() +"  " +min.getCellNumber() + "    " +min.x + " " + min.y);
                findIterationWithMemory(min, openMap, closedSet, table, endPoint);

                iterator = openMap.entrySet().iterator();
                min = iterator.next().getValue();
                while (iterator.hasNext()) {
                    Cell tempCell = iterator.next().getValue();
                    Integer tempInt = tempCell.F;
                    if (min.F > tempInt) {
                        min = tempCell;
                    }
                }
                printAnswer(min, table);
            }



            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    System.err.print(table[i][j]);
                }
                System.err.println();
            }

        }
    }

    public static void printAnswer(Cell cell, char[][] table){
        Cell tempCell = cell;
        ArrayList<Cell> arrayList = new ArrayList<Cell>();
        while (tempCell.parent != null){
            arrayList.add(tempCell);
            tempCell = tempCell.parent;
        }
        arrayList.add(tempCell);

        if (arrayList.size() > 0) {
            if (arrayList.get(0).x > arrayList.get(1).x)
                System.out.println("LEFT");
            else if (arrayList.get(0).x < arrayList.get(1).x)
                System.out.println("RIGHT");
            else if (arrayList.get(0).y > arrayList.get(1).y)
                System.out.println("UP");
            else if (arrayList.get(0).y < arrayList.get(1).y)
                System.out.println("DOWN");
        }

    }

    public static boolean findIteration(Cell cell, HashMap<Integer, Cell> open, HashSet close, char [][] table, Cell endPoint){

        for (int i = cell.x-1; i <= cell.x+1; i++) {
            for (int j = cell.y - 1; j <= cell.y + 1; j++) {
                try {
                    if ((i == cell.x-1 && j == cell.y - 1) || (i == cell.x-1 && j == cell.y + 1) || (i == cell.x+1 && j == cell.y + 1) || (i == cell.x+1 && j == cell.y - 1))
                        continue;
                    if (!(i >= 0 && i < MAXLENGTH && j >= 0 && j < MAXWIDTH ))
                        continue;
                    if (!(table[i][j] != '#' && (cell.x != i || cell.y != j)))
                        continue;
                    Cell tempCell = new Cell(i, j);
                    if (close.contains(tempCell.getCellNumber()))
                        continue;

                    int g = (int) (Math.sqrt((cell.x - i) * (cell.x - i) + (cell.y - j) * (cell.y - j)) * 10);
                    int h =  (Math.abs(endPoint.x - tempCell.x) + Math.abs(endPoint.y - tempCell.y))*10;
                    if (!open.containsKey(tempCell.getCellNumber())) {
                        open.put(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                        // System.out.println( g + " " + h);
                        //System.out.println("Put Cell "+tempCell.getCellNumber()+" in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    } else if (cell.G+g+h < open.get(tempCell.getCellNumber()).F) {
                        open.replace(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                        //System.out.println("Replace cell "+tempCell.getCellNumber()+"in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.err.println(i + " " + j + 34534534);
                }

            }
        }
        close.add(cell.getCellNumber());
        open.remove(cell.getCellNumber());

        return false;
    }

    public static boolean findIterationWithMemory(Cell cell, HashMap<Integer, Cell> open, HashSet close, char [][] table, Cell endPoint){

        for (int i = cell.x-1; i <= cell.x+1; i++) {
            for (int j = cell.y - 1; j <= cell.y + 1; j++) {
                try {
                    if ((i == cell.x-1 && j == cell.y - 1) || (i == cell.x-1 && j == cell.y + 1) || (i == cell.x+1 && j == cell.y + 1) || (i == cell.x+1 && j == cell.y - 1))
                        continue;
                    if (!(i >= 0 && i < MAXLENGTH && j >= 0 && j < MAXWIDTH ))
                        continue;
                    if (!(table[i][j] != '#' && wasThereTable[i][j] != 'Y' && (cell.x != i || cell.y != j)))
                        continue;
                    Cell tempCell = new Cell(i, j);
                    if (close.contains(tempCell.getCellNumber()))
                        continue;

                    int g = (int) (Math.sqrt((cell.x - i) * (cell.x - i) + (cell.y - j) * (cell.y - j)) * 10);
                    int h =  0;//(Math.abs(endPoint.x - tempCell.x) + Math.abs(endPoint.y - tempCell.y))*10;
                    if (!open.containsKey(tempCell.getCellNumber())) {
                        open.put(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                        // System.out.println( g + " " + h);
                        //System.out.println("Put Cell "+tempCell.getCellNumber()+" in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    } else if (cell.G+g+h < open.get(tempCell.getCellNumber()).F) {
                        open.replace(tempCell.getCellNumber(), new Cell(i, j, cell.G+g, h, cell));
                        //System.out.println("Replace cell "+tempCell.getCellNumber()+"in open i = "+i+" j = "+j+" F = "+ (cell.G+g + h)+ " parent = "+cell.getCellNumber());
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.err.println(i + " " + j + 34534534);
                }

            }
        }
        close.add(cell.getCellNumber());
        wasThereTable[cell.x][cell.y] = 'Y';
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
        return this.x * Player.MAXWIDTH + this.y;
    }
}