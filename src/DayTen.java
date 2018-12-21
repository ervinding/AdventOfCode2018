
import java.io.*;
import java.util.*;

public class DayTen {

    class Position {
        private int x, y, xVel, yVel;
        Position(int x, int y, int xv, int yv) {
            this.x = x;
            this.y = y;
            this.xVel = xv;
            this.yVel = yv;
        }

        public void move() {
            this.x += xVel;
            this.y += yVel;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public boolean at(int x, int y) {
            return this.x == x && this.y == y;
        }
    }

    String filename = "/Users/ervinding/git/AdventOfCode2018/src/input10.txt";

    List<Position> positions = new ArrayList<>();
    /*position=< -9932, -30242> velocity=< 1,  3>*/

    public DayTen() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.replaceAll(" ","");
            String position = line.substring(line.lastIndexOf("position=<"));
            String velocity = line.substring(line.lastIndexOf("velocity=<"));
            int x = Integer.parseInt(position.substring(position.indexOf("<")+1, position.indexOf(",")));
            int y = Integer.parseInt(position.substring(position.indexOf(",") + 1,position.indexOf(">")));
            int xv = Integer.parseInt(velocity.substring(velocity.indexOf("<")+1, velocity.indexOf(",")));
            int yv = Integer.parseInt(velocity.substring(velocity.indexOf(",") + 1,velocity.indexOf(">")));
//            System.out.println(x + " " + y + " " + xv + " " + yv);
            positions.add(new Position(x,y,xv,yv));
        }
        int seconds = 0;
        while (true) {
            int count = 0;
            for (Position p : positions) {
                p.move();
            }
            seconds++;
            for (Position p : positions) {
                if (hasNeighbors(p)) {
                    count++;
                }
                else {
                    break;
                }
            }
            if (count > 6) {
                break;
            }
        }

        int MIN_X = 0, MIN_Y = 0, MAX_X = 0, MAX_Y = 0;
        for (Position p : positions) {
            if (p.getX() > MAX_X) {
                MAX_X = p.getX();
            }
            if (p.getY() > MAX_Y) {
                MAX_Y = p.getY();
            }
            if (p.getX() < MIN_X) {
                MIN_X = p.getX();
            }
            if (p.getY() < MIN_Y) {
                MIN_Y = p.getY();
            }
        }

        for (int y = MIN_Y; y <= MAX_Y; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = MIN_X; x <= MAX_X; x++) {
                boolean found = false;
                for (Position p : positions) {
                    if (p.at(x, y)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            String line = sb.toString();
            if (line.contains("#")) {
                line = line.substring(line.indexOf(" #"));
                System.out.println(line);
            }

        }
        System.out.println("\nNumber of seconds: " + seconds);



    }

    public boolean hasNeighbors(Position p) {
        int px = p.getX();
        int py = p.getY();
        for (Position pp : positions) {
            int count = 0;
            if (pp.getX() == px && pp.getY() == py) {
                continue;
            }
            if (px+1 == pp.getX() && py == pp.getY()){
                count++;
            }
            if (px-1 == pp.getX() && py == pp.getY()) {
                count++;
            }
            if (py-1 == pp.getY() && px == pp.getX()) {
                count++;
            }
            if (py+1 == pp.getY() && px == pp.getX()) {
                count++;
            }
            if (count > 0) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        new DayTen();
    }
}