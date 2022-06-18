package blockchain.service;

import static blockchain.service.DifficultyServiceOperation.*;

public class DifficultyService {

    private int difficulty;

    public DifficultyService(int difficulty) {
        this.difficulty = difficulty;
    }

    public DifficultyServiceOperation updateDifficulty(long generationTime) {
        if (generationTime > 30) {
            difficulty--;
            return DECREASE;
        }

        else if (generationTime < 10) {
            difficulty++;

            if (difficulty > 5) {
                difficulty = 5;
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
