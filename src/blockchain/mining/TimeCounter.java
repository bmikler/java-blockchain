package blockchain.mining;

import java.io.Serializable;

public class TimeCounter implements Serializable {

    private long time = System.currentTimeMillis();

    public long countTimeTillLastUsage() {
        long lastBlockTime = System.currentTimeMillis();
        long miningTime = (lastBlockTime - time) / 1000;

        time = lastBlockTime;

        return miningTime;
    }

}
