package blockchain;

import blockchain.utils.FileService;

public class Main {
    public static void main(String[] args) {

        BlockChain blockChain = BlockChain.getInstance();

        Miner miner = new Miner(blockChain);
        Miner miner1 = new Miner(blockChain);

        blockChain.addBlockToBlockChain(miner.generateNewBlock());
        blockChain.addBlockToBlockChain((miner1.generateNewBlock()));

        blockChain.print();

    }
}
