package blockchain.util;

import blockchain.model.Blockchain;

import java.io.*;
import java.util.Optional;

public class FileManager {

    private final static String FILENAME = "blockchain";

    public void saveBlockchain(Blockchain blockchain) {
        try(
                var fs = new FileOutputStream(FILENAME);
                var os = new ObjectOutputStream(fs);
        ) {
            os.writeObject(blockchain);

        } catch (IOException e) {
            System.err.println("File save error " + FILENAME);
            e.printStackTrace();
        }
    }

    public Optional<Blockchain> loadBlockchain() {

        try (
                var fis = new FileInputStream(FILENAME);
                var ois = new ObjectInputStream(fis);
        ) {
            return Optional.of ((Blockchain) ois.readObject());
        } catch (ClassNotFoundException | IOException e) {
            //System.err.println("File not found, generate new blockchain...");
            return Optional.empty();
        }
    }
}



