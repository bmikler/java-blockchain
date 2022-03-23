package blockchain;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Enter how many zeros the hash must start with: ");
        int zeroStart = scanner.nextInt();

        BlockChain blockChain = new BlockChain(zeroStart, new LinkedList<>());


        for (int i = 0; i < 5; i++) {

            long start = System.currentTimeMillis();

            Block block = blockChain.generateNewBlock();

            long end = System.currentTimeMillis();

            blockChain.addBlockToBlockChain(block);

            System.out.println(block);
            System.out.println("Block was generating for " + ((end - start)/1000) + " seconds");


        }

        System.out.println(blockChain.isValid());

    }
}
