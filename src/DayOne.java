import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;


public class DayOne {

    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */

    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        HashSet<Integer> set = new HashSet<>();
        Integer total = 0;
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            Integer e = parse(line);
            total += e;
            if (set.contains(total)) {
                System.out.println(total);
            } else {
                set.add(total);
            }
        }

    }
    public static Integer parse(String s){
        int total = 0;
        int returnVal = 0;
        if(s.charAt(0) == '+'){
            returnVal += Integer.parseInt(s);
        }else if(s.charAt(0) =='-'){
            total -= Integer.parseInt(s);
            for (int i = 0; i < total; i++)
            {
                returnVal--;
            }
        }

        return returnVal;
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
