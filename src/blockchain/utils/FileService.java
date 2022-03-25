package blockchain.utils;

import blockchain.BlockChain;

import java.io.*;
import java.util.Optional;

public class FileService {

    private static final String filePath = "./Blockchain/task/files/blockchain.obj";
    /*
    Also, the blockchain should be saved to the file after each block.
    At the start of the program, you should check if a blockchain exists on the hard drive,
    load it, check if it is valid, and then continue to create blocks.

    You may want to use serialization to do that.
     */

    public static Optional<BlockChain> loadBlockChain() {

        try(
                var fis = new FileInputStream(filePath);
                var ois = new ObjectInputStream(fis);
                ) {

            BlockChain blockChain = (BlockChain) ois.readObject();
            return Optional.of(blockChain);

        } catch (IOException | ClassNotFoundException e) {
            //System.err.println("File not found. Creating new blockchain.");
            return Optional.empty();
        }

    }

    public static void saveBlockchain(BlockChain blockChain) {
        try(
                var fs = new FileOutputStream(filePath);
                var os = new ObjectOutputStream(fs);
        ) {

            os.writeObject(blockChain);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


}
