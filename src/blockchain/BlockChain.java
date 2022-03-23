package blockchain;

import java.io.Serializable;
import java.util.*;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 3812017177088226528L;

    private final Random random = new Random();
    private final int zeroStart;
    private final List<Block> blockchain;

    public BlockChain() {
        this.zeroStart = 1;
        this.blockchain = new LinkedList<>();
    }

    public List<Block> getBlockchain() {
        return Collections.unmodifiableList(blockchain);
    }

    public void addBlockToBlockChain(Block block) {
        blockchain.add(block);
    }

    public boolean isValid() {

        for (int i = blockchain.size() - 1; i > 1; i--) {

            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);

            if (!current.getHashPrevious().equals(previous.getHashCurrent())) {
                return false;
            }

        }

        return true;
    }

    public Block generateNewBlock() {

        long id = getLastBlock()
                .map(block -> block.getId() + 1)
                .orElse(1L);

        String hashPrevious = getLastBlock()
                .map(Block::getHashCurrent)
                .orElse(String.valueOf(0));

        long timeStamp = new Date().getTime();

        int magicNumber = findMagicNumber(id, timeStamp, hashPrevious);

        return new Block(id, timeStamp, magicNumber, hashPrevious);
    }

    private int findMagicNumber(long id, long timestamp, String hashPrevious) {

        int magicNumber;
        String hashCurrent;

        do {
            magicNumber = random.nextInt();
            hashCurrent = StringUtil.applySha256(
                    id + timestamp + magicNumber + hashPrevious);
        } while (!hashCurrent.startsWith("0".repeat(zeroStart)));

        return magicNumber;
    }

    private Optional<Block> getLastBlock() {

        if (blockchain.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(blockchain.get(blockchain.size() - 1));
    }
}
