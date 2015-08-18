import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    final static String VERTICAL = "V";
    final static String HORIZONTAL = "H";

    static ArrayList<Wall> wallArrayList = new ArrayList<>();

    static class Wall {
        int x;
        int y;
        String orientation;

        public Wall(int x, int y, String orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt(); // width of the board
        int h = in.nextInt(); // height of the board
        int playerCount = in.nextInt(); // number of players (2 or 3)
        int myId = in.nextInt(); // id of my player (0 = 1st player, 1 = 2nd player, ...)

        int [] [] playerArray = new int [playerCount][3];
        int countDays = 0;
        // game loop
        while (true) {
            countDays ++;
            for (int i = 0; i < playerCount; i++) {
                int x = in.nextInt(); // x-coordinate of the player
                int y = in.nextInt(); // y-coordinate of the player
                int wallsLeft = in.nextInt(); // number of walls available for the player
                playerArray[i][0] = x;
                playerArray[i][1] = y;
                playerArray[i][2] = wallsLeft;
            }

            wallArrayList.clear(); // очищення списка стенок
            int wallCount = in.nextInt(); // number of walls on the board
            for (int i = 0; i < wallCount; i++) {
                int wallX = in.nextInt(); // x-coordinate of the wall
                int wallY = in.nextInt(); // y-coordinate of the wall
                String wallOrientation = in.next(); // wall orientation ('H' or 'V')
                wallArrayList.add(new Wall(wallX, wallY, wallOrientation)); // заполнение списка стенок
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            switch (myId){
                case 0:
                    if(countDays == 2){
                        if (!wallForPlayer1(myId, playerArray[myId][1], playerArray[1 - myId]))
                            move(myId);
                    }
                    else
                        move(myId);
                    break;
                case 1:
                    if(countDays == 2){
                        if (!wallForPlayer0(myId, playerArray[myId][1], playerArray[1 - myId]))
                            move(myId);
                    }
                    else
                        move(myId);
                    break;
                case 2:
                    System.err.println(countDays);
                    if (countDays == 5) {
                        if (!wallForPlayer0(myId, playerArray[myId][1], playerArray[0]))
                            if(!wallForPlayer1(myId, playerArray[myId][1], playerArray[1]))
                                move(myId);
                    }
                    else
                        move(myId);
                    break;
            }

            // System.err.println(playerArray[1][0]+" "+playerArray[1][1]+" "+playerArray[1][2]);


            // action: LEFT, RIGHT, UP, DOWN or "putX putY putOrientation" to place a wall
        }
    }

    public static void moveRight(){
        System.out.println("RIGHT");
    }
    public static void moveLeft(){
        System.out.println("LEFT");
    }
    public static void moveBottom(){
        System.out.println("DOWN");
    }
    public static boolean wallForPlayer0(int myId, int myY, int [] array){
        if(array[1] == -1 || (array[1] == myY && myId != 2)){
            return false;
        }

        else if (Math.abs(array[1] - myY) > 1){
            if (safeWall((array[0]+1), array[1], VERTICAL)){
                return true;
            }
            //System.out.println((array[0]+1) + " " + array[1] + " " + "V");
        }
        else if (array[1] > myY){
            if (safeWall((array[0]+1), array[1], VERTICAL))
                //System.out.println((array[0]+1) + " " + array[1] + " " + "V");
                return true;
        }
        else if (array[1] < myY){
            if (safeWall((array[0]+1), (array[1]-1), VERTICAL))
                //System.out.println((array[0]+1) + " " + (array[1]-1) + " " + "V");
                return true;
        }
        return false;
    }

    public static boolean wallForPlayer1(int myId, int myY, int [] array){
        if(array[1] == -1 || (array[1] == myY && myId != 2)){
            return false;
        }

        else if (Math.abs(array[1] - myY) > 1){
            if (safeWall(array[0], array[1], VERTICAL))
                return true;            //// не ставить стенку если  коордрдинаты  чX 8
            //System.out.println(array[0] + " " + array[1] + " " + "V");
        }
        else if (array[1] > myY){
            //System.out.println(array[0] + " " + array[1] + " " + "V");
            if (safeWall(array[0], array[1], VERTICAL))
                return true;
        }
        else if (array[1] < myY){
            if (safeWall(array[0], (array[1]-1), VERTICAL))
                //System.out.println(array[0] + " " + (array[1]-1) + " " + "V");
                return true;
        }
        return false;
    }

    public static void wallForPlayer2(int [] array){
        System.out.println(array[0] + " " + (array[1]+1) + " " + "H");
    }

    public static boolean safeWall(int x, int y, String orientation){

        if ((orientation == VERTICAL) && !((x > 0) && (x <= 8) && (y >= 0) && (y < 8)))
            return false;
        if ((orientation == HORIZONTAL) && !((x >= 0) && (x < 8) && (y > 0) && (y <= 8)))
            return false;

        if (wallArrayList.isEmpty()){
            System.out.println(x + " " + y + " " + orientation);
            return true;
        }

        else for (Wall wall : wallArrayList){
            if (wall.orientation == orientation){
                if ((orientation == VERTICAL) && (wall.x == x) && (Math.abs(wall.y - y) <= 1))
                    return false;
                else if ((orientation == HORIZONTAL) && (wall.y == y) && (Math.abs(wall.x - x) <= 1))
                    return false;
                else if ((wall.x == x) && (wall.y == y))
                    return false;
            }
            else if ((wall.orientation == HORIZONTAL) && (wall.x + 1 == x) && (wall.y - 1 == y))
                return false;
            else if ((wall.orientation == VERTICAL) && (wall.x - 1 == x) && (wall.y + 1 == y))
                return false;

        }
        System.out.println(x + " " + y + " " + orientation);
        return true;
    }

    public static void move(int i){
        if (i == 0)
            moveRight();
        else if (i == 1)
            moveLeft();
        else if (i == 2)
            moveBottom();
    }
}