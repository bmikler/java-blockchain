package blockchain;

import blockchain.utils.StringUtil;

import java.io.Serializable;

public class Block implements Serializable {
    private static final long serialVersionUID = 3812017177098226528L;

    private final long id;
    private final long minerId;
    private final long timeStamp;
    private final int magicNumber;
    private final String hashPrevious;

    public Block(long id, long minerId, long timeStamp, int magicNumber, String hashPrevious) {
        this.id = id;
        this.minerId = minerId;
        this.timeStamp = timeStamp;
        this.magicNumber = magicNumber;
        this.hashPrevious = hashPrevious;
    }

    public long getId() {
        return id;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHashPrevious() {
        return hashPrevious;
    }

    public String getHashCurrent() {
        return StringUtil.applySha256(
                id + timeStamp + magicNumber + hashPrevious);
    }

    @Override
    public String toString() {
        return "\nBlock:\n" +
                "Created by miner # " + minerId + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Magic number:" + magicNumber + "\n" +
                "Hash of the previous block:\n" + hashPrevious + '\n' +
                "Hash of the block:\n" + getHashCurrent();
    }
}
