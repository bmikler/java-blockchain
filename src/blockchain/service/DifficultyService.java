package blockchain.service;

import static blockchain.service.DifficultyServiceOperation.*;

public class DifficultyService {

    private final int maxDifficulty;
    private int difficulty;

    public DifficultyService(int maxDifficulty, int difficulty) {
        this.maxDifficulty = maxDifficulty;
        this.difficulty = difficulty;
    }

    public DifficultyServiceOperation updateDifficulty(long generationTime) {
        if (generationTime > 30) {
            difficulty--;
            return DECREASE;
        }

        else if (generationTime < 10) {
            difficulty++;

            if (difficulty > maxDifficulty) {
                difficulty = maxDifficulty;
            }

            return INCREASE;
        }

        else {
            return STAY_THE_SAME;
        }
    }

    public int getDifficulty() {
        return difficulty;
    }
}
