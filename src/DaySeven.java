import java.io.*;
import java.util.*;

public class DaySeven {
    /**
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    /* PART ONE */
//    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
//        String[] letters = {"A", "B", "C", "D", "E",
//                "F", "G", "H", "I", "J",
//                "K", "L", "M", "N", "O",
//                "P", "Q", "R", "S", "T",
//                "U", "V", "W", "X", "Y", "Z"};
//        HashMap<String, List<String>> m = new HashMap<>();
//        String dependency, letter;
//        StringBuilder sb = new StringBuilder();
//        Boolean[] workers = {false, false, false, false, false};
//        for (String line = r.readLine(); line != null; line = r.readLine()) {
//            List<String> dependencies = new ArrayList<>();
//            dependency = line.substring(5, 6);
//            letter = line.substring(36, 37);
//            if (m.containsKey(letter)) {
//                dependencies = m.get(letter);
//                dependencies.add(dependency);
//                m.replace(letter, dependencies);
//                continue;
//            }
//            dependencies.add(dependency);
//            m.put(letter, dependencies);
//        }
//        System.out.println(m.toString());
//        String nextLetter = null;
//        List<String> removed = new ArrayList<>();
//        while (!m.isEmpty()) {
//            //find the nextletter by finding the one that doesn't have any dependencies.
//            for (String l : letters) {
//                if (!m.containsKey(l) && !removed.contains(l)) {
//                    nextLetter = l;
//                    removed.add(nextLetter);
//                    break;
//                }
//            }
//            sb.append(nextLetter);
//            for (List<String> l : m.values()) {
//                if (l.contains(nextLetter)) {
//                    l.remove(nextLetter);
//                }
//            }
//            for (HashMap.Entry<String, List<String>> entry : m.entrySet()) {
//                if (entry.getValue().isEmpty()) {
//                    String last = entry.getKey();
//                    m.remove(entry.getKey());
//                    if (m.isEmpty()) {
//                        sb.append(last);
//                    }
//                    break;
//                }
//            }
//            System.out.println(sb.toString());
//            System.out.println(m.toString());
//        }
//        System.out.println(sb.length());
//        System.out.println(sb.toString());
//    }

    String filename = "/Users/ervinding/git/AdventOfCode2018/src/input7.txt";

    /* PART TWO */
    public DaySeven() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        String[] letters = {"A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String, List<String>> m = new HashMap<>();
        String dependency, letter;
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            List<String> dependencies = new ArrayList<>();
            dependency = line.substring(5, 6);
            letter = line.substring(36, 37);
            if (m.containsKey(letter)) {
                dependencies = m.get(letter);
                dependencies.add(dependency);
                m.replace(letter, dependencies);
                continue;
            }
            dependencies.add(dependency);
            m.put(letter, dependencies);
        }
        System.out.println(m.toString());
        String nextLetter = null;
        List<String> removed = new ArrayList<>();
        int total = 0;
        Worker[] workers = new Worker[5];
        while (!m.isEmpty()) {
            //find the nextletter by finding the one that doesn't have any dependencies.
            for (Worker w : workers) {
                if (w.getTimer() == 0) {
                    for (String l : letters) {
                        if (!m.containsKey(l) && !removed.contains(l)) {
                            nextLetter = l;
                            w.setTimer(letterString.indexOf("l") + 1);
                            removed.add(nextLetter);
                            break;
                        }
                    }
                    sb.append(nextLetter);

                    for (List<String> l : m.values()) {
                        if (l.contains(nextLetter)) {
                            l.remove(nextLetter);
                        }
                    }
                    for (HashMap.Entry<String, List<String>> entry : m.entrySet()) {
                        if (entry.getValue().isEmpty()) {
                            String last = entry.getKey();
                            m.remove(entry.getKey());
                            if (m.isEmpty()) {
                                sb.append(last);
                            }
                            break;
                        }
                    }

                }
            }
        }
        System.out.println(sb.toString());
        System.out.println(m.toString());

    }

    public static void main(String[] args) {
        new DaySeven();

    }
}