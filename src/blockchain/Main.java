package blockchain;

import blockchain.mining.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockChain blockChain = BlockChainUtils.getInstance();
        MinerNumberGenerator generator = new MinerNumberGenerator();

        final int minerCounter = 50;

        Set<Miner> miners = new HashSet<>();

        for (int i = 0; i < minerCounter; i++) {
            miners.add(new Miner(generator, blockChain, new Digger()));
        }

        for(Miner miner : miners) {
            miner.start();
        }

        Thread.sleep(5000);


    }
}
