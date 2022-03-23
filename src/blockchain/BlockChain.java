package blockchain;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class BlockChain {
    private Random random = new Random();
    private final int zeroStart;
    private List<Block> blockchain;

    public BlockChain(int zeroStart, List<Block> blockchain) {;
        this.zeroStart = zeroStart;
        this.blockchain = blockchain;
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public void addBlockToBlockChain(Block block) {
        blockchain.add(block);
    }

    public Block generateNewBlock(Block previousBlock) {

        long id;
        String hashPrevious;
        long timeStamp = new Date().getTime();
        int magicNumber = 0;

        if (previousBlock == null) {
            id = 1;
            hashPrevious = String.valueOf(0);

        } else {
            id = previousBlock.getId() + 1;
            hashPrevious = previousBlock.getHashCurrent();
        }

        String hashCurrent;

        do {
            hashCurrent = generateHash(id, timeStamp, magicNumber, hashPrevious);
            magicNumber = random.nextInt();
        } while (!hashCurrent.startsWith("0".repeat(zeroStart)));

        return new Block(id, timeStamp, magicNumber, hashPrevious, hashCurrent);
    }

    private String generateHash(long id, long timestamp, int magicNumber, String hashPrevious) {
        return StringUtil.applySha256(
                id + timestamp + magicNumber + hashPrevious);
    }

    public boolean isValid(Block block) {
        return true;
    }

}
