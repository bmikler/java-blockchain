package blockchain;

import blockchain.mining.Miner;
import blockchain.mining.MinerNumberGenerator;

public class Main {
    public static void main(String[] args) {

        BlockChain blockChain = BlockChain.getInstance();
        MinerNumberGenerator generator = new MinerNumberGenerator();

        Miner miner = new Miner(generator, blockChain);
        Miner miner1 = new Miner(generator, blockChain);

        miner1.start();
        miner.start();

    }
}
