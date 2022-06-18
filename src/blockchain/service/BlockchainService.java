package blockchain.service;

import blockchain.chat.MessagesCache;
import blockchain.model.Block;
import blockchain.model.Blockchain;
import blockchain.util.FileManager;
import blockchain.util.Printer;

public class BlockchainService {

    private final Blockchain blockchain;
    private final Validator validator;
    private final Printer printer;
    private final DifficultyService difficultyService;
    private final MessagesCache messagesCache;
    private final FileManager fileManager;

    public BlockchainService(Blockchain blockchain, Validator validator, Printer printer, DifficultyService difficultyService, MessagesCache messagesCache, FileManager fileManager) {
        this.blockchain = blockchain;
        this.validator = validator;
        this.printer = printer;
        this.difficultyService = difficultyService;
        this.messagesCache = messagesCache;
        this.fileManager = fileManager;
    }

    public synchronized void addNewBlock(Block block, long minerId) {

        if (!validator.validateBlockchain(blockchain)) {
            throw new RuntimeException("Blockchain invalid!");
        }

        if (validator.validateNewBlock(getLastBlock(), block, difficultyService.getDifficulty())) {

            block.setMinerId(minerId);
            block.setBlockData(messagesCache.getMessages());

            blockchain.addBlock(block);
            DifficultyServiceOperation operation = difficultyService.updateDifficulty(block.getGeneratingTime());
            blockchain.setDifficulty(difficultyService.getDifficulty());

            printer.printBlock(block, difficultyService.getDifficulty(), operation);

            fileManager.saveBlockchain(blockchain);

            messagesCache.clearCache();


        } else {
            throw new RuntimeException("New Block invalid!");
        }



    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public Block getLastBlock() {
        return blockchain.getLastBlock().orElse(new Block());
    }

    public int getActualDifficulty() {
        return difficultyService.getDifficulty();
    }

    public int getLength() {
        return blockchain.getLength();
    }
}
