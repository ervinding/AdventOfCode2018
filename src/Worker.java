public class Worker {

    private int timer;
    private int timeSpent = 0;
    private String letter;

    public Worker(int timer) {
        this.timer = timer;
    }

    protected void setTimer(int t) {
        timeSpent = 0;
        this.timer = t;
    }
    protected int decrement() {
        timeSpent++;
        return timer--;
    }

    protected int getTimer() {
        return timer;
    }

    protected void assignLetter(String s) {
        letter = s;
    }

    protected String getLetter() {
        return letter;
    }

    protected int totalTime(){
        return timeSpent;
    }


}
