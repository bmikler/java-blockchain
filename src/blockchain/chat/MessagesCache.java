package blockchain.chat;

import java.util.Collections;
import java.util.List;

public class MessagesCache {

    private final List<Message> messageList;

    public MessagesCache(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messageList);
    }

    public void clearCache() {
        messageList.clear();
    }

}
