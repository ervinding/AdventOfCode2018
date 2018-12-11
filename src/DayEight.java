import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;

public class DayEight {

    String filename = "/Users/ervinding/git/AdventOfCode2018/src/input8.txt";

    int partOneTotal = 0;

    int partTwoTotal = 0;

    int index = 0;

    List<Integer> input = new ArrayList<>();


    public DayEight() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = sc.nextLine();
        String[] ints = line.split(" ");
        for (int i = 0 ; i < ints.length; i++) {
            input.add(Integer.parseInt(ints[i]));
        }
        System.out.println(input.toString());
        partOneTotal = parse();
        System.out.println(partOneTotal);
        index = 0;
        partTwoTotal = parse2();
        System.out.println(partTwoTotal);

    }


    protected int parse() {
        int total = 0;
        int numOfChildren = input.get(index);
        index++;
        int numOfMetadata = input.get(index);
        index++;
        if (numOfChildren == 0) {
            for (int i = 0; i < numOfMetadata; i++) {
                total += input.get(index);
                index++;
            }
            return total;
        }
        else {
            for (int i = 0; i < numOfChildren; i++) {
                total += parse();
            }
            for (int i = numOfMetadata; i > 0; i--) {
                total += input.get(index);
                index++;
            }
        }
        return total;

    }





    protected int parse2() {
        int total = 0;
        int numOfChildren = input.get(index);
        index++;
        int numOfMetadata = input.get(index);
        index++;
        List<Integer> children = new ArrayList<>();
        // if leaf, grab the total of the metadata
        if (numOfChildren == 0) {
            for (int i = 0; i < numOfMetadata; i++) {
                total += input.get(index);
                index++;
            }
            return total;
        }
        else { // otherwise, grab each child's value and add them to a list.
            for (int i = 0; i < numOfChildren; i++) {
                    int value = parse2();
                    children.add(value);
            }
            /* after the list is populated, get the value of the metadata (by then, the index will be at the metadata) and
               check if it's within the children list. If it is, grab that child's value and add it to the total. */
            for (int i = 0; i < numOfMetadata; i++) {
                if (input.get(index)-1 < children.size()) {
                    total += (children.get(input.get(index)-1));
                }
                index++;
            }
        }
        return total;
    }


    public static void main(String[] args) {
        new DayEight();

    }
}