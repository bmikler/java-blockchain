package blockchain.mining;

import blockchain.Block;
import blockchain.BlockChain;
import blockchain.utils.StringUtil;

import java.util.Date;
import java.util.Random;

public class Digger {

    public Block generateNewBlock(BlockChain blockChain, long minerId) {

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

            zeroStart = blockChain.getDifficulty();

            hashCurrent = StringUtil.applySha256(id + timeStamp + magicNumber + lastHash);

        } while (!hashCurrent.startsWith("0".repeat(zeroStart)));

        return new Block(id, minerId, timeStamp, magicNumber, lastHash);
    }

}
