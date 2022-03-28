package blockchain.mining;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DifficultyManagerTest {

    private DifficultyManager manager;
    private TimeCounter counter;
    private int startLevel = 2;
    private final int MAX_TIME_SECONDS = 10;
    private final int MIN_TIME_SECONDS = 1;

    @Before
    public void init() {
        counter = mock(TimeCounter.class);
    }

    @Test
    public void testGeneratingToFastIncreaseDifficultyLevel() {

        manager = new DifficultyManager(MAX_TIME_SECONDS, MIN_TIME_SECONDS, counter, startLevel);

        when(counter.countTimeTillLastUsage()).thenReturn(0L);
        manager.update();

        int expectedDifficulty = startLevel + 1;
        int actualDifficulty = manager.getDifficult();

        Assert.assertEquals(expectedDifficulty, actualDifficulty);
    }

    @Test
    public void testGeneratingToSlowDecreaseDifficultyLevel() {

        manager = new DifficultyManager(MAX_TIME_SECONDS, MIN_TIME_SECONDS, counter, startLevel);

        when(counter.countTimeTillLastUsage()).thenReturn(50L);

        manager.update();

        int expectedDifficulty = startLevel - 1;
        int actualDifficulty = manager.getDifficult();

        Assert.assertEquals(expectedDifficulty, actualDifficulty);

    }

    @Test
    public void testGeneratingToSlowDecreaseDifficultyLevelUnderZero() {

        manager = new DifficultyManager(MAX_TIME_SECONDS, MIN_TIME_SECONDS, counter, 0);

        when(counter.countTimeTillLastUsage()).thenReturn(50L);

        manager.update();

        int expectedDifficulty = 0;
        int actualDifficulty = manager.getDifficult();

        Assert.assertEquals(expectedDifficulty, actualDifficulty);

    }

    @Test
    public void testGenerateTimeOKNoChangeDifficultyLevel() {

        manager = new DifficultyManager(MAX_TIME_SECONDS, MIN_TIME_SECONDS, counter, startLevel);

        when(counter.countTimeTillLastUsage()).thenReturn(5L);

        manager.update();

        int expectedDifficulty = startLevel;
        int actualDifficulty = manager.getDifficult();

        Assert.assertEquals(expectedDifficulty, actualDifficulty);

    }

}