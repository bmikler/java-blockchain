package blockchain;

import blockchain.mining.DifficultyManager;
import blockchain.mining.TimeCounter;
import blockchain.utils.FileService;

import java.util.ArrayList;

public class BlockChainUtils {

    private final static BlockChain INSTANCE = loadBlockChain();

    private static BlockChain loadBlockChain() {
        return FileService.loadBlockChain()
                .orElse(new BlockChain(
                        new ArrayList<>(),
                        new DifficultyManager(30,5, new TimeCounter(),
                                0))
                );
    }

    public static BlockChain getInstance() {
        return INSTANCE;
    }


}
