package blockchain.util;

public class TimeCounter {

    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long countTime() {
        return (System.currentTimeMillis() - start)/1000;
    }

}
