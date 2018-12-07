import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class DayFour {

    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        List<String> l = new ArrayList<>();
        List<Guard> g = new ArrayList<>();
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            l.add(line);
        }
        Collections.sort(l);
        Guard guard = new Guard(0);
        for (int i = 0; i < l.size(); i++) {
            String lineParsed = l.get(i).substring(l.get(i).indexOf(' '));
//            System.out.println(l.get(i));
            if (lineParsed.contains("Guard")) {
                guard = parse(lineParsed);
                for (int e = 0; e < g.size(); e++) {
                    if (g.get(e).getId() == guard.getId()) {
                        guard = g.remove(e);
                        break;
                    }
                }
//                System.out.println("Guard " + guard);
            }
            else if (lineParsed.contains("falls")) {
                for (int e = 0; e < g.size(); e++) {
                    if (g.get(e).getId() == guard.getId()) {
                        guard = g.remove(e);
                        break;
                    }
                }
                int time = parseTime(lineParsed);
//                System.out.println("falls asleep " + time);
                guard.addTime(time);
                g.add(guard);
            }
            else if (lineParsed.contains("wakes")) {
                for (int e = 0; e < g.size(); e++) {
                    if (g.get(e).getId() == guard.getId()) {
                        guard = g.remove(e);
                        break;
                    }
                }
                int time = parseTime(lineParsed);
                for (int t = guard.getLatestTime()+1; t < time; t++) {
                    guard.addTime(t);
                }
                g.add(guard);
//                System.out.println("wakes up " + time);
            }
        }
        //Part 1
        for (Guard gg : g ) {
            System.out.println(gg.getId() + ":" + " Total Asleep " + gg.getTimeAsleepSize() + " most asleep: " + gg.getMostAsleep());
        }
        //Part 2
        for (Guard gg : g ) {
            System.out.println(gg.getId() + ":" + " max " + gg.getMax() + " minute " + gg.getMostAsleep());
        }
    }

    protected static int parseTime(String x) {
        String timeStr = x.substring(x.indexOf(':')+1, x.indexOf("]"));
        int num = Integer.parseInt(timeStr);
        return num;
    }

    protected static Guard parse(String x) {
        String guardNum = x.substring(x.indexOf('#')+1, x.indexOf("begins")-1);
        int num = Integer.parseInt(guardNum);
        Guard guard = new Guard(num);
        return guard;
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
