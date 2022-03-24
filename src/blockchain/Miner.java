package blockchain;

import blockchain.utils.StringUtil;

import java.util.Date;
import java.util.Random;

public class Miner {

    private final BlockChain blockChain;

    public Miner(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public Block generateNewBlock() {
        long id = blockChain.getLastBlock()
                .map(block -> block.getId() + 1)
                .orElse(1L);

        String hashPrevious = blockChain.getLastBlock()
                .map(Block::getHashCurrent)
                .orElse(String.valueOf(0));

        long timeStamp = new Date().getTime();

        int magicNumber = findMagicNumber(id, timeStamp, hashPrevious);

        return new Block(id, timeStamp, magicNumber, hashPrevious);
    }

    private int findMagicNumber(long id, long timestamp, String hashPrevious) {

        Random random = new Random();
        int magicNumber;
        String hashCurrent;

        do {
            magicNumber = random.nextInt();
            hashCurrent = StringUtil.applySha256(
                    id + timestamp + magicNumber + hashPrevious);
        } while (!hashCurrent.startsWith("0".repeat(blockChain.getZeroStart())));

        return magicNumber;
    }

}
