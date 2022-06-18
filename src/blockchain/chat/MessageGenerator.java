package blockchain.chat;

import java.util.List;
import java.util.Random;

public class MessageGenerator {

    private final Random random = new Random();

    private final List<String> authors = List.of(
            "Tom", "John", "Anna", "Sarah", "Nick");

    private final List<String> messages = List.of(
            "It's not fair!", "Hey, nice chat", "Hey, I'm first!", "Blockchain", "Trolololo"
    );

    public Message getRandomMessage() {
        String author = authors.get(random.nextInt(authors.size()));
        String message = messages.get(random.nextInt(messages.size()));

        return new Message(author, message);
    }

}
