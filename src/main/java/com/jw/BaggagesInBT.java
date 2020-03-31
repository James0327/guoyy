package com.jw;

/**
 * @Description: test com.jw.BaggagesInBT
 * @Package: com.jw
 * @ClassName: BaggagesInBT
 * @Author: james.guo
 * @Date: 2019/3/18 22:49
 * @Version: 1.0
 */
public class BaggagesInBT {
    /**
     * BT checkin的起点，所对应请求FareIndex
     */
    private int checkInSectorIndex;
    /**
     * BT checkout的终点, 所对应请求FareIndex
     */
    private int checkOutSectorIndex;
    /**
     * 行李的件数，PC
     */
    private int freePieces;
    /**
     * 行李的重量，KG
     */
    private int weight;
    /**
     * 行李的体积，‘每件三边不超过55*40*20CM’或‘每件三边不超过158CM’
     */
    private String volume;
    /**
     * S7 196表的no
     */
    private int noteID;

    public int getCheckInSectorIndex() {
        return checkInSectorIndex;
    }

    public void setCheckInSectorIndex(int checkInSectorIndex) {
        this.checkInSectorIndex = checkInSectorIndex;
    }

    public int getCheckOutSectorIndex() {
        return checkOutSectorIndex;
    }

    public void setCheckOutSectorIndex(int checkOutSectorIndex) {
        this.checkOutSectorIndex = checkOutSectorIndex;
    }

    public int getFreePieces() {
        return freePieces;
    }

    public void setFreePieces(int freePieces) {
        this.freePieces = freePieces;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }
}
