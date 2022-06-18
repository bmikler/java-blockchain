package blockchain;

import blockchain.chat.Client;
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

    private final static int MINER_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {

        FileManager fileManager = new FileManager();
        MessagesCache messagesCache = new MessagesCache(new ArrayList<>());
        Blockchain blockchain = fileManager.loadBlockchain().orElse(new Blockchain(new ArrayList<>()));
        //Blockchain blockchain = new Blockchain(new ArrayList<>());

        int difficulty = blockchain.getDifficulty();

        BlockchainService blockchainService = new BlockchainService(blockchain, new Validator(), new Printer(), new DifficultyService(difficulty),messagesCache, fileManager);
        Random random = new Random();

        List<Thread> miners = new ArrayList<>();

        for (int i = 0; i < MINER_NUMBER; i++) {

            Digger digger = new Digger(blockchainService, new TimeCounter(), random);
            Miner miner = new Miner(i, blockchainService, digger);

            miners.add(new Thread(miner));
        }

        Client client = new Client(blockchainService, new MessageGenerator(), messagesCache);
        Thread clientThread = new Thread(client);

        for (var m : miners) {
            m.setPriority(1);
            m.start();
        }

        clientThread.setPriority(10);
        clientThread.start();



    }
}
