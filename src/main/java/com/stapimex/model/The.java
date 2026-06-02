package com.stapimex.model;

public class The {

    private int sbd;
    private int maNhom;
    private int maBoPhan;
    private int soLuong;

    public The() {
    }

    public The(int sbd, int maNhom, int maBoPhan, int soLuong) {
        this.sbd = sbd;
        this.maNhom = maNhom;
        this.maBoPhan = maBoPhan;
        this.soLuong = soLuong;
    }

    public int getSbd() {
        return sbd;
    }

    public void setSbd(int sbd) {
        this.sbd = sbd;
    }

    public int getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(int maNhom) {
        this.maNhom = maNhom;
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
