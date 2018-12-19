
import java.io.*;
import java.util.*;

public class DayNine {

    class CircularDeque<T> extends ArrayDeque<T> {
        void rotate(int num) {
            if (num == 0 || this.size() == 0) {
                return;
            }
            else if (num > 0) { // clockwise
                for (int i = 0; i < num; i++) {
                    T temp = this.removeFirst();
                    this.addLast(temp);
                }
            }
            else { // counter clockwise
                for (int i = 0; i < Math.abs(num); i++) {
                    T temp = this.removeLast();
                    this.addFirst(temp);
                }
            }
        }
    }

    String filename = "/Users/ervinding/git/AdventOfCode2018/src/input9.txt";



    List<Integer> input = new ArrayList<>();


    public DayNine() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = sc.nextLine();
        HashMap<Integer,Long> scores = new HashMap<>();
        int players = parse(line);
        Long score = parseScore(line);
        int player = 0;
        CircularDeque<Integer> deque = new CircularDeque<>();

        for (int i = 0; i <= score; i++) {
            if (i % 23 == 0 && i != 0) {
                if (scores.containsKey(player)) {
                    scores.replace(player, scores.get(player) + i);
                } else {
                    scores.put(player, (long) i);
                }
                deque.rotate(-7);
                scores.put(player, scores.get(player) + deque.removeFirst());
            }
            else {
                deque.rotate(2);
                deque.addFirst(i);
            }
            player++;
            if (player == players) {
                player = 0;
            }
        }

        System.out.println(getMax(scores));
    }

    protected Long getMax(HashMap<Integer,Long> map) {
        long max = 0;
        for (Long num : map.values()) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    protected int parse(String line) {
       return Integer.parseInt(line.substring(0,line.indexOf(" ")));
    }

    protected Long parseScore(String line) {
        return Long.parseLong(line.substring(line.indexOf("worth ")+6,line.indexOf("points")-1));
    }





    public static void main(String[] args) {
        new DayNine();
    }
}