package blockchain.chat;

import java.io.Serializable;

public class Message implements Serializable {

    private final String author;
    private final String message;

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

    @Override
    public String toString() {
        return author + ": " + message;
    }
}
