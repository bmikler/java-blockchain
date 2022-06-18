package blockchain.service;

import blockchain.model.Block;

public class Miner implements Runnable{

    private final long id;
    private final BlockchainService blockchainService;
    private final Digger digger;
    private final int blocksPerRound;

    public Miner(long id, BlockchainService blockchainService, Digger digger, int blocksPerRound) {
        this.id = id;
        this.blockchainService = blockchainService;
        this.digger = digger;
        this.blocksPerRound = blocksPerRound;
    }

    @Override
    public void run() {

        int i = blockchainService.getLength() + 5;

        while (true) {
            Block newBlock = digger.generateNextBlock();

            if (blockchainService.getLength() < i) {
                try {
                    blockchainService.addNewBlock(newBlock, id);
                } catch (RuntimeException e) {
                    System.err.println("Miner # " + id + " - " + e.getMessage());
                }
            } else {
                break;
            }

        }

    }



}
