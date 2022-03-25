package blockchain;

import blockchain.mining.Miner;
import blockchain.mining.MinerNumberGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockChain blockChain = BlockChain.getInstance();
        MinerNumberGenerator generator = new MinerNumberGenerator();
        final int minerCounter = 50;

        Set<Miner> miners = new HashSet<>();

        for (int i = 0; i < minerCounter; i++) {
            miners.add(new Miner(generator, blockChain));
        }

        for(Miner miner : miners) {
            miner.start();
        }

        Thread.sleep(5000);


    }
}
