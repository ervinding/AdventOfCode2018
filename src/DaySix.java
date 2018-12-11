import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Point;


public class DaySix {
    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    /* PART ONE */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        HashMap<Integer, Point> points = new HashMap<>();
        HashMap<Integer, Integer> regions = new HashMap<>();
        int maxX = 0, maxY = 0, count = 0;
        // parse and determine grid size
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            int xPos = Integer.parseInt(line.substring(0,line.indexOf(",")));
            int yPos = Integer.parseInt(line.substring(line.indexOf(",")+2));
            Point p = new Point(xPos,yPos);
            points.put(count,p);
            count++;
            if (xPos > maxX) {
                maxX = xPos;
            }
            if (yPos > maxY) {
                maxY = yPos;
            }
        }
        int[][] grid = new int[maxX + 1][maxY + 1];
        int farthest, bestDist;
        //loop to determine each points closest point.
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                farthest = maxX + maxY;
                bestDist = -1;
                // find distance to closest point
                for (int i = 0; i < count; i++) {
                    Point p = points.get(i);
                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    if (dist < farthest) {
                        farthest = dist;
                        bestDist = i;
                    }
                    else if (dist == farthest) {
                        bestDist = -1;
                    }
                }
                grid[x][y] = bestDist; // either actual dist or -1
                Integer total = regions.get(bestDist);
                if (total == null) {
                    total = 1;
                }
                else {
                    total++;
                }
                regions.put(bestDist, total);
            }
        }

        // remove infinite
        for (int x = 0; x <= maxX; x++) {
            int infinite = grid[x][0];
            regions.remove(infinite);
            infinite = grid[x][maxY];
            regions.remove(infinite);
        }
        for (int y = 0; y <= maxY; y++) {
            int infinite = grid[0][y];
            regions.remove(infinite);
            infinite = grid[maxX][y];
            regions.remove(infinite);
        }

        // find biggest
        int biggest = 0;
        for (int size : regions.values()) {
            if (size > biggest) {
                biggest = size;
            }
        }
        System.out.println("Biggest: " + biggest);
        //PART 2
        int area = 0;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                int size = 0;
                for (int i = 0; i < count; i++) {
                    Point p = points.get(i);
                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    size += dist;
                }
                if (size < 10000) {
                    area++;
                }
            }
        }
        System.out.println("Area Size: " + area);
    }



    /**
     * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
     * and System.out or from filenames specified on the command line, then call doIt.
     * @param args
     */
    public static void main(String[] args) {
        try {
            BufferedReader r;
            PrintWriter w;
            if (args.length == 0) {
                r = new BufferedReader(new InputStreamReader(System.in));
                w = new PrintWriter(System.out);
            } else if (args.length == 1) {
                r = new BufferedReader(new FileReader(args[0]));
                w = new PrintWriter(System.out);
            } else {
                r = new BufferedReader(new FileReader(args[0]));
                w = new PrintWriter(new FileWriter(args[1]));
            }
            doIt(r, w);
            w.flush();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }

    }
}
