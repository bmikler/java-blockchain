package blockchain.chat;

import blockchain.service.BlockchainService;

public class Client implements Runnable{

    private final BlockchainService blockchainService;
    private final MessageGenerator messageGenerator;
    private final MessagesCache messagesCache;

    public Client(BlockchainService blockchainService, MessageGenerator messageGenerator, MessagesCache messagesCache) {
        this.blockchainService = blockchainService;
        this.messageGenerator = messageGenerator;
        this.messagesCache = messagesCache;
    }

    @Override
    public void run() {

        int i = blockchainService.getLength() + 5;

        while (blockchainService.getLength() < i) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

                Message randomMessage = messageGenerator.getRandomMessage();
                //System.err.println(randomMessage);
                messagesCache.addMessage(randomMessage);
            }


    }
}

