import javax.swing.text.AttributeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class DayFive {

    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    /* PART ONE */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        String line = r.readLine();
        String[] letters = {"a","b","c","d","e",
                            "f","g","h","i","j",
                            "k","l","m","n","o",
                            "p","q","r","s","t",
                            "u","v","w","x","y","z"};

        for (String s : letters) {
            removePairs(line, s);
            System.out.println(s);
        }
    }
    // Part 1
//    protected static String parse(String x) {
//       for (int i = 0; i < x.length()-1; i++) {
//           Character curr = x.charAt(i);
//           Character next = x.charAt(i+1);
//           if (Character.isLowerCase(curr)) {
//               if (Character.toUpperCase(curr) == next) {
//                   String toDel = curr.toString() + next.toString();
//                   x = x.replaceAll(toDel, "");
//                   System.out.println();
//                   return parse(x);
//               }
//           }
//           else {
//               if (Character.toLowerCase(curr) == next) {
//                   String toDel = curr.toString() + next.toString();
//                   x = x.replaceAll(toDel, "");
//                   return parse(x);
//               }
//           }
//       }
//       System.out.println(x.length());
//       return x;
//    }
    protected static String removePairs(String x, String letter) {
        String temp = x;
        x = x.replaceAll(letter, "");
        x = x.replaceAll(letter.toUpperCase(), "");
        return parse(x);
    }

    protected static String parse(String x) {
        for (int i = 0; i < x.length()-1; i++) {
            Character curr = x.charAt(i);
            Character next = x.charAt(i+1);
            if (Character.isLowerCase(curr)) {
                if (Character.toUpperCase(curr) == next) {
                    String toDel = curr.toString() + next.toString();
                    x = x.replaceAll(toDel, "");
                    return parse(x);
                }
            }
            else {
                if (Character.toLowerCase(curr) == next) {
                    String toDel = curr.toString() + next.toString();
                    x = x.replaceAll(toDel, "");
                    return parse(x);
                }
            }
        }
        System.out.println(x.length());
        return x;
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
