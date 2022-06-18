package blockchain.service;

import blockchain.model.Block;

public class Miner implements Runnable{

    private final long id;
    private final BlockchainService blockchainService;
    private final Digger digger;

    public Miner(long id, BlockchainService blockchainService, Digger digger) {
        this.id = id;
        this.blockchainService = blockchainService;
        this.digger = digger;
    }

    @Override
    public void run() {

        int i = blockchainService.getLength() + 5;

        while (true) {
            Block newBlock = digger.generateNextBlock();
            try {
                blockchainService.addNewBlock(newBlock, id);
            } catch (RuntimeException e) {
                System.err.println("Miner # " + id + " - " + e.getMessage());
            }
        }

    }



}
