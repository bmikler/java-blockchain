package blockchain;

import blockchain.chat.ChatSimulator;
import blockchain.chat.MessageGenerator;
import blockchain.chat.MessagesCache;
import blockchain.model.Blockchain;
import blockchain.service.*;
import blockchain.util.FileManager;
import blockchain.util.Printer;
import blockchain.util.TimeCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private final static int MINERS_NUMBER = 10;
    private final static int MAX_DIFFICULTY_LEVEL = 10;
    private final static int MESSAGES_TIME_INTERVAL_MILLISECONDS = 1000;
    private final static int BLOCKS_PER_RUN = 10;

    public static void main(String[] args) throws InterruptedException {

        FileManager fileManager = new FileManager();
        MessagesCache messagesCache = new MessagesCache(new ArrayList<>());
        Blockchain blockchain = fileManager.loadBlockchain().orElse(new Blockchain(new ArrayList<>()));


        int difficulty = blockchain.getDifficulty();
        DifficultyService difficultyService = new DifficultyService(MAX_DIFFICULTY_LEVEL, difficulty);

        BlockchainService blockchainService = new BlockchainService(
                blockchain,
                new Validator(),
                new Printer(),
                difficultyService,
                messagesCache,
                fileManager);

        List<Thread> miners = createMiners(blockchainService);

        ChatSimulator client = new ChatSimulator(blockchainService, new MessageGenerator(), messagesCache, MESSAGES_TIME_INTERVAL_MILLISECONDS);
        Thread clientThread = new Thread(client);

        for (var m : miners) {
            m.setPriority(1);
            m.start();
        }

        clientThread.setPriority(10);
        clientThread.start();

//        Printer printer = new Printer();
//        new Thread(() -> {
//            blockchain.getBlocks().subList(blockchain.getLength() - 5, blockchain.getLength())
//                    .forEach(b -> printer.printBlock(b, 0, DifficultyServiceOperation.STAY_THE_SAME));
//        }).run();

    }


    private static List<Thread> createMiners(BlockchainService blockchainService) {

        Random random = new Random();
        List<Thread> miners = new ArrayList<>();

        for (int i = 0; i < MINERS_NUMBER; i++) {

            Digger digger = new Digger(blockchainService, new TimeCounter(), random);
            Miner miner = new Miner(i, blockchainService, digger, BLOCKS_PER_RUN);
            miners.add(new Thread(miner));
        }

        return miners;

    }
}
