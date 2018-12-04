import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class DayTwo {

    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    /* PART ONE
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        int doubles = 0;
        int triples = 0;
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            HashMap<Character, Integer> map = parse(line);
            Boolean hasDouble = false;
            Boolean hasTriple = false;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 2 && !hasDouble) {
                    doubles++;
                    hasDouble = true;
                }
                else if (entry.getValue() == 3 && !hasTriple) {
                    triples++;
                    hasTriple = true;
                }
            }
        }
        System.out.println(doubles*triples);

    }

    public static HashMap<Character,Integer> parse(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 1);
        for (int i = 1; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
            else {
                map.put(s.charAt(i),1);
            }
        }
        return map;
    }*/
    //PART TWO
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        // Your code goes here - see Part0 for an example
        List<String> list = new ArrayList<>();
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            list.add(line);
        }
        String s, next;
        int num;
        for (int i = 0; i < list.size()-1; i++) { //iterate over list
            s = list.get(i);
            for (int j = i+1; j < list.size(); j++) { // iterate over list to compare item i
                next = list.get(j);
                num = 0;
                for (int e = 0; e < s.length(); e++) {
                    if (s.charAt(e) == next.charAt(e)) {
                        num++;
                    }
                }
                if (num == 25) {
                    System.out.println(s);
                    System.out.println(next);
                }
            }

        }

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
