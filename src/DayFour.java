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
    /* PART ONE */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        Hashtable<Integer, Integer> map = new Hashtable<>();
        List<String> l = new ArrayList<>();
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            l.add(line);
        }
        Collections.sort(l);
        for (int i = 0; i < l.size(); i++) {
            String lineParsed, guardNum;
            lineParsed = l.get(i).substring(l.get(i).indexOf(' '));
            System.out.println(l.get(i));
        }
//        System.out.println(l.toString());
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
