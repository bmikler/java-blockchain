package blockchain.mining;

public class MinerNumberGenerator {
    private static long ACTUAL_NUMBER = 0;

    public long getNumber() {
        ACTUAL_NUMBER++;
        return ACTUAL_NUMBER;
    }

}
