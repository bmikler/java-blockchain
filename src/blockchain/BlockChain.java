package blockchain;

import blockchain.utils.FileService;
import blockchain.utils.StringUtil;

import java.io.Serializable;
import java.util.*;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 3812017177088226528L;

    private static final BlockChain INSTANCE = FileService.loadBlockChain().orElse(new BlockChain());
    private final List<Block> blockchain;
    private int zeroStart;

    private BlockChain() {
        this.zeroStart = 1;
        this.blockchain = new LinkedList<>();
    }

    public static BlockChain getInstance() {
        return INSTANCE;
    }

    public int getZeroStart() {
        return zeroStart;
    }

    public void print() {
        blockchain.forEach(System.out::println);
    }

    public void addBlockToBlockChain(Block block) {

        if(!isBlockValid(block)) {
            throw new IncorectBlockException();
        }
        blockchain.add(block);
        save();
    }

    private void save() {
        FileService.saveBlockchain(INSTANCE);
    }

    private Boolean isBlockValid(Block block) {

        return getLastBlock()
                .map(b -> b.getHashCurrent().equals(block.getHashPrevious()))
                .orElse(true);

    }

    public Optional<Block> getLastBlock() {

        if (blockchain.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(blockchain.get(blockchain.size() - 1));
    }
}
