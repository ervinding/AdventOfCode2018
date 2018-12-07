import java.util.*;

public class Guard {

    private int id;
    private int latestTime;
    private Hashtable<Integer,Integer> timeAsleep = new Hashtable<>();

    public Guard(int id) {
        this.id = id;
    }

    protected int getLatestTime() {
        return this.latestTime;
    }

    protected int getTimeAsleepSize() {
        int num = 0;
        for (Map.Entry<Integer,Integer> entry : timeAsleep.entrySet()) {
           num += entry.getValue();
        }
        return num;
    }

    protected void addTime(int time) {
        if (timeAsleep.containsKey(time)) {
            timeAsleep.replace(time,timeAsleep.get(time)+1);
        }else {
            timeAsleep.put(time, 1);
        }
        latestTime = time;
    }

    protected int getMostAsleep() {
        int max = 0;
        int returnVal = 0;
        for (Map.Entry<Integer,Integer> entry : timeAsleep.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                returnVal = entry.getKey();
            }
        }
        return returnVal;
    }
    protected int getMax() {
        int max = 0;
        int returnVal = 0;
        for (Map.Entry<Integer,Integer> entry : timeAsleep.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                returnVal = entry.getValue();
            }
        }
        return returnVal;
    }

    protected int getId() {
        return this.id;
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }
}
