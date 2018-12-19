import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class DayThree {

    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    /* PART ONE */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        int[][] grid = new int[1000][1000];
        Hashtable<Integer, Integer> map = new Hashtable<>();
        int lineIndex = 1;
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            String lineParsed, lengthStr, widthStr, xPos, yPos, size;
            int x, y, width, length, uniqueVal;
            lineParsed = line.substring(line.indexOf('@'));
            size = lineParsed.substring(lineParsed.indexOf(':')+2);

            //Parse to grab x and y coordinates and the width and length
            xPos = lineParsed.substring(2, lineParsed.indexOf(':')).substring(0,lineParsed.indexOf(',')-2);
            yPos = lineParsed.substring(2, lineParsed.indexOf(':')).substring(lineParsed.indexOf(',')-1);
            widthStr = size.substring(0,size.indexOf('x'));
            lengthStr = size.substring(size.indexOf('x')+1);

            //Convert to int
            x = Integer.parseInt(xPos);
            y = Integer.parseInt(yPos);
            width = Integer.parseInt(widthStr);
            length = Integer.parseInt(lengthStr);
            uniqueVal = width*length;


            System.out.println("(" + x + "," + y + ") with size " + width + " by " + length);
            for (int i = x; i < x+width; i++) {
                for (int e = y; e < y+length; e++) {
                    if (grid[e][i] > 1) {
                        grid[e][i] = 1; //if overlap, set to 1.
                    }
                    else if (grid[e][i] == 1) {
                        continue;
                    }
                    else {
                        grid[e][i] = lineIndex;
                    }
                }
            }
            map.put(lineIndex,uniqueVal);
            lineIndex++;
        }
        //loop to get the total amount of overlaps. PART 1.
        int partOneTotal = 0;
        for (int i = 0; i  < grid.length; i++) {
            for (int e = 0; e < grid[i].length; e++) {
                if (grid[e][i] == 1) {
                    partOneTotal++;
                }
            }
        }
        System.out.println("Part 1: " + partOneTotal);

        //loop to get the key that appears as many time as the value. Key is the lineindex and the uniqueval is w*l
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int total = 0;
            for (int i = 0; i  < grid.length; i++) {
                for (int e = 0; e < grid[i].length; e++) {
                    if (grid[e][i] == entry.getKey()) {
                        total++;
                    }
                }
            }
            /* PART 2, non-overlapping */
            if (total == entry.getValue()) {
                System.out.println("Part 2: " + entry.getKey());
            }
        }



//        for (int i = 0; i  < grid.length; i++) {
//            w.println(Arrays.toString(grid[i]));
//        }

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
