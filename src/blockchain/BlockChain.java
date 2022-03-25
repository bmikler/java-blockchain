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
    private long timer;

    private BlockChain() {
        this.zeroStart = 1;
        this.blockchain = new LinkedList<>();
        this.timer = System.currentTimeMillis();
    }

    public static BlockChain getInstance() {
        return INSTANCE;
    }

    public List<Block> getBlockchain() {
        return Collections.unmodifiableList(blockchain);
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
        System.out.println(block);
        blockchain.add(block);

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

    private void save() {
        FileService.saveBlockchain(INSTANCE);
    }

    private Boolean isBlockValid(Block block) {

        return getLastHash().equals(block.getHashPrevious())
                && block.getHashCurrent().startsWith("0".repeat(zeroStart));

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
