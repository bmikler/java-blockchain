package blockchain.util;

import blockchain.chat.Message;
import blockchain.model.Block;
import blockchain.service.DifficultyServiceOperation;

import java.util.List;

public class Printer {

    public void printBlock(Block block, int actualDifficulty, DifficultyServiceOperation operation) {

        System.out.println("Block:");
        System.out.println("Created by miner # " + block.getMinerId());
        System.out.println(block);
        printBlockData(block.getBlockData());
        prinTimeInfo(block.getGeneratingTime());
        printDifficultyInfo(actualDifficulty, operation);
        System.out.println();

    }

    private void prinTimeInfo(long generatingTime) {
        System.out.println("Block was generating for " + generatingTime + " seconds");

    }

    private void printBlockData(List<Message> messages) {

        System.out.println("Block data:");
        if (messages.isEmpty()){
            System.out.println("No messages");
        } else {
            messages.forEach(System.out::println);
        }


    }

    private void printDifficultyInfo(int actualDifficulty, DifficultyServiceOperation operation) {
        switch (operation) {
            case INCREASE:
                System.out.println("N was increased to " + actualDifficulty);
                break;
            case DECREASE:
                System.out.println("N was decrease to " + actualDifficulty);
                break;
            case STAY_THE_SAME:
                System.out.println("N stays the same");
                break;
        }
    }

}
