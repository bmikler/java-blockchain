package blockchain.model;

import blockchain.chat.Message;
import blockchain.util.StringUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Block implements Serializable {

    private long id;
    private long minerId;
    private long timestamp;
    private String hashOfPrevious;
    private String hash;
    private long magicNumber;
    private long generatingTime;
    private List<Message> blockData;

    public Block() {
        this.id = 0L;
        this.hash = "0";
    }

    public long getId() {
        return id;
    }

    public long getMinerId() {
        return minerId;
    }

    private String getHashInput() {
        return id + timestamp + hashOfPrevious + magicNumber + blockData;
    }

    public String getHash() {
        return hash;
    }

    public String getHashOfPrevious() {
        return hashOfPrevious;
    }


    public long getGeneratingTime() {
        return generatingTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMinerId(long minerId) {
        this.minerId = minerId;
    }

    public void setHashOfPrevious(String hashOfPrevious) {
        this.hashOfPrevious = hashOfPrevious;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
        this.hash = StringUtil.applySha256(getHashInput());
    }

    public void setGeneratingTime(long generatingTime) {
        this.generatingTime = generatingTime;
    }

    public List<Message> getBlockData() {
        return Collections.unmodifiableList(blockData);
    }

    public void setBlockData(List<Message> blockData) {
        this.blockData = blockData;
    }

    @Override
    public String toString() {
        return
                "Id: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + hashOfPrevious +
                "\nHash of the block:\n" + getHash();
    }
}
