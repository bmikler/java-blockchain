package blockchain;

import blockchain.mining.DifficultyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.mock;

public class BlockChainTest {

    private BlockChain blockChain;

    @Before
    public void init() {
        DifficultyManager difficultyManager = mock(DifficultyManager.class);
        blockChain = new BlockChain(new ArrayList<>(), difficultyManager);
    }

    //TODO
    //test add block to blockchain valid or not, with full or empty blockchain
    //check if difficulty manger update and file service save


    @Test
    public void testGetLastHashEmptyBlockChain() {

        String expectedHash = "0";
        String actualHah = blockChain.getLastHash();

        Assert.assertEquals(expectedHash, actualHah);
    }

    @Test
    public void testGetLastHashWithNotEmptyBlockChain() {

        Block block = new Block(1, 1L, new Date().getTime(), 1, "0");
        blockChain.addBlockToBlockChain(block);

        String expectedHash = block.getHashCurrent();
        String actualHah = blockChain.getLastHash();

        Assert.assertEquals(expectedHash, actualHah);
    }

    @Test
    public void testGetLastIdEmptyBlockChain() {

        long expectedId = 0L;
        long actualId = blockChain.getLastId();

        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetLasIdtWithNotEmptyBlockChain() {

        Block block = new Block(1, 1L, new Date().getTime(), 1, "0");
        blockChain.addBlockToBlockChain(block);

        long expectedId = 1L;
        long actualId = blockChain.getLastId();

        Assert.assertEquals(expectedId, actualId);
    }
    
}