package blockchain.mining;

import blockchain.Block;
import blockchain.BlockChain;
import blockchain.IncorectBlockException;

public class Miner extends Thread{

    private final long id;
    private final BlockChain blockChain;
    private final Digger digger;

    public Miner(MinerNumberGenerator generator, BlockChain blockChain, Digger digger) {

        this.id = generator.getNumber();
        this.blockChain = blockChain;
        this.digger = digger;
    }

    @Override
    public void run() {
        while (blockChain.getSize() < 1000){

            Block block = digger.generateNewBlock(blockChain, this.id);
            try {
                blockChain.addBlockToBlockChain(block);
            } catch (IncorectBlockException e) {
                System.err.println("Miner: " + id + " Incorrect block. Somebody was faster");
            }
        }
    }

}
