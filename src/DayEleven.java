
import java.io.*;
import java.util.*;

public class DayEleven {

    class Position {
        private int x, y, val, square;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getVal() {
            return this.val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getSquare() {
            return this.square;
        }

        public void setSquare(int val) {
            this.square = val;
        }

    }

    String filename = "/Users/ervinding/git/AdventOfCode2018/src/input11.txt";


    public DayEleven() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int input = Integer.parseInt(sc.nextLine());
        List<Position> positions = new ArrayList<>();
        Position[][] grid = new Position[301][301];
        for (int x = 1; x < 301; x++) {
            for (int y = 1; y < 301; y++) {
                Position p = new Position(x,y);
                p.setVal(calcValue(p, input));
                grid[x][y] = p;
                positions.add(p);
            }
        }
        int max = 0;
        Position maxP = null;
        for (Position p : positions) {
            int total = 0;
            if (p.getY() < 298 && p.getX() < 298) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        total += grid[p.getX() + x][p.getY() + y].getVal();
                    }
                }
                p.setSquare(total);
                if (p.getSquare() > max) {
                    max = p.getSquare();
                    maxP = p;
                }
            }
        }
        System.out.println("3by3: " + maxP.getX() + ", " + maxP.getY());

        /* PART 2 */
        max = 0;
        maxP = null;
        int maxSquare = 0;
        int square = 1;
        System.out.println("Brute force");
        while (square < 301) {
            for (Position p : positions) {
                int total = 0;
                if (p.getY() < 301 - square && p.getX() < 301 - square) {
                    for (int x = 0; x < square; x++) {
                        for (int y = 0; y < square; y++) {
                            total += grid[p.getX() + x][p.getY() + y].getVal();
                        }
                    }
                    if (total > p.getSquare()) {
                        p.setSquare(total);
                    }
                    if (p.getSquare() > max) {
                        max = p.getSquare();
                        maxP = p;
                        maxSquare = square;
                    }
                }
            }
            square++;
            System.out.println("Square " + square + ": " + maxP.getX() + "," + maxP.getY() + "," + maxSquare);
        }
        System.out.println("square: " + maxP.getX() + ", " + maxP.getY() + ", " + maxSquare);
    }

    int calcValue(Position p, int input) {
        Integer val = ((((p.getX() + 10)*p.getY()) + input) * (p.getX() + 10));
        String temp = val.toString();
        int returnVal = Character.getNumericValue(temp.charAt(temp.length()-3)) - 5;
        return returnVal;
    }








    public static void main(String[] args) {
        new DayEleven();
    }
}