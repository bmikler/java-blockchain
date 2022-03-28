package blockchain.mining;

import java.io.Serializable;

public class DifficultyManager implements Serializable {

    private final int MAX_TIME_SECONDS;
    private final int MIN_TIME_SECONDS;
    private final TimeCounter timer;
    private int difficult;

    public DifficultyManager(int MAX_TIME_SECONDS, int MIN_TIME_SECONDS, TimeCounter timer, int difficult) {
        this.MAX_TIME_SECONDS = MAX_TIME_SECONDS;
        this.MIN_TIME_SECONDS = MIN_TIME_SECONDS;
        this.timer = timer;
        this.difficult = difficult;
    }

    public int getDifficult() {
        return difficult;
    }

    public void update() {
        long miningTime = timer.countTimeTillLastUsage();

        System.out.println("Block was generating for " + miningTime + " seconds");

        if (miningTime > MAX_TIME_SECONDS && difficult > 0){
            difficult--;
            System.out.println("N was decreased to " + difficult);
        } else if (miningTime < MIN_TIME_SECONDS) {
            difficult++;
            System.out.println("N was increased to " + difficult);
        } else {
            System.out.println("N stays the same");
        }

    }

}
