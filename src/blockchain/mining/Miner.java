package blockchain.mining;

import blockchain.Block;
import blockchain.BlockChain;
import blockchain.IncorectBlockException;
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
        while (blockChain.getSize() < 1000){
            Block block = generateNewBlock();
            
            try {
                blockChain.addBlockToBlockChain(block);
            } catch (IncorectBlockException e) {
                System.err.println("Incorrect block. Somebody was faster");
            }
        }
    }

    public Block generateNewBlock() {

        long id;
        long timeStamp;
        int magicNumber;
        String lastHash;

        int zeroStart;

        Random random = new Random();

        String hashCurrent;

        do {
            id = blockChain.getLastId() + 1;
            timeStamp = new Date().getTime();
            magicNumber = random.nextInt();
            lastHash = blockChain.getLastHash();

            zeroStart = blockChain.getZeroStart();

            hashCurrent = StringUtil.applySha256(id+ timeStamp + magicNumber + lastHash);

        } while (!hashCurrent.startsWith("0".repeat(zeroStart)));

        return new Block(id, this.id, timeStamp, magicNumber, lastHash);
    }


}
