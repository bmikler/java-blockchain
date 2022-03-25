package blockchain;

import blockchain.mining.Miner;
import blockchain.utils.FileService;
import blockchain.utils.StringUtil;

import java.io.Serializable;
import java.util.*;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 3812017177088226528L;

    private static final BlockChain INSTANCE = FileService.loadBlockChain().orElse(new BlockChain());
    private final List<Block> blockchain = new LinkedList<>();
    private int zeroStart = 1;
    private long timer = System.currentTimeMillis();

    public static BlockChain getInstance() {
        return INSTANCE;
    }

    public int getSize() {
        return blockchain.size();
    }

    public int getZeroStart() {
        return zeroStart;
    }

    public synchronized void addBlockToBlockChain(Block block) {

        if(!isBlockValid(block)) {
            throw new IncorectBlockException();
        }

        blockchain.add(block);
        System.out.println(block);

        long tmp = System.currentTimeMillis();
        long time = (tmp - timer) / 1000;
        System.out.println("Block was generating for " + time + " seconds");

        if (time > 15){
            zeroStart--;
            System.out.println("N was decreased to " + zeroStart);
        }

        else if (time < 5) {
            zeroStart++;
            System.out.println("N was increased to " + zeroStart);
        }

        else {
            System.out.println("N stays the same");
        }

        timer = tmp;
        save();
    }

    private Boolean isBlockValid(Block block) {

        return getLastHash().equals(block.getHashPrevious())
                && block.getHashCurrent().startsWith("0".repeat(zeroStart));

    }

    private void save() {
        FileService.saveBlockchain(INSTANCE);
    }

    public String getLastHash() {

        if(blockchain.isEmpty()){
            return String.valueOf(0);
        }
        return blockchain.get(blockchain.size() - 1).getHashCurrent();

    }

    public long getLastId() {
        if(blockchain.isEmpty()){
            return 0L;
        }
        return blockchain.get(blockchain.size() - 1).getId();
    }

}
