package blockchain.service;

import blockchain.model.Block;
import blockchain.model.Blockchain;

public class Validator {

    public boolean validateNewBlock(Block lastBlock, Block newBlock, int actualDifficulty) {
        return newBlock.getId() == lastBlock.getId() + 1 &&
                newBlock.getHashOfPrevious() == lastBlock.getHash() &&
                newBlock.getHash().startsWith("0".repeat(actualDifficulty));

    }

    public boolean validateBlockchain(Blockchain blockchain) {

        String previousHash = "0";

        for (Block block : blockchain.getBlocks()) {
            if (!block.getHashOfPrevious().equals(previousHash)){
                return false;
            }
            previousHash = block.getHash();
        }
        return true;
    }

}
