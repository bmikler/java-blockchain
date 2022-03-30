package blockchain.mining;

import blockchain.Block;
import blockchain.BlockChain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiggerTest {

    private Digger digger;
    private BlockChain blockChain;

    @Before
    public void init() {
        blockChain = mock(BlockChain.class);
        digger = new Digger();
    }

    @Test
    public void testIfHashStartWithCorrectNumbersOfZero() {

        when(blockChain.getLastId()).thenReturn(1L);
        when(blockChain.getDifficulty()).thenReturn(3);
        when(blockChain.getLastHash()).thenReturn("0");

        Block block = digger.generateNewBlock(blockChain, 1L);

        String expectedStartOfHash = "000";
        String actualStartOfHash = block.getHashCurrent().substring(0, 3);

        Assert.assertEquals(expectedStartOfHash, actualStartOfHash);

    }

    @Test
    public void testIdIncrement() {

        when(blockChain.getLastId()).thenReturn(1L);
        when(blockChain.getDifficulty()).thenReturn(3);
        when(blockChain.getLastHash()).thenReturn("0");

        Block block = digger.generateNewBlock(blockChain, 1L);

        long expectedId = 2L;
        long actualId = block.getId();

        Assert.assertEquals(expectedId, actualId);

    }

    @Test
    public void testPreviousHashNumber() {

        when(blockChain.getLastId()).thenReturn(1L);
        when(blockChain.getDifficulty()).thenReturn(3);
        when(blockChain.getLastHash()).thenReturn("000000559babe760cc21724afb27ae5b96f0ccf585b686263cbcf52bc166b938");

        Block block = digger.generateNewBlock(blockChain, 1L);

        String expectedPreviousHash = "000000559babe760cc21724afb27ae5b96f0ccf585b686263cbcf52bc166b938";
        String actualPreviousHash = block.getHashPrevious();

        Assert.assertEquals(expectedPreviousHash, actualPreviousHash);

    }

}