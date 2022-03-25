package blockchain.mining;

import blockchain.Block;
import blockchain.BlockChain;
import blockchain.utils.StringUtil;

import java.util.Date;
import java.util.Random;

public class Miner extends Thread{

    private final long id;
    private final BlockChain blockChain;

    public Miner(MinerNumberGenerator generator, BlockChain blockChain) {

        this.id = generator.getNumber();
        this.blockChain = blockChain;
    }

    @Override
    public void run() {
        while (blockChain.getBlockchain().size() < 6){
            Block block = generateNewBlock();
            blockChain.addBlockToBlockChain(block);
        }
    }

    public Block generateNewBlock() {
        long id = blockChain.getLastId() + 1;
        String hashPrevious = blockChain.getLastHash();

        long timeStamp = new Date().getTime();

        int magicNumber = findMagicNumber(id, timeStamp, hashPrevious);

        return new Block(id, this.id, timeStamp, magicNumber, hashPrevious);
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
