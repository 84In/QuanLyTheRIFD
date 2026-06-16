package com.stapimex.model;

import java.util.Date;

public class ThuHoiCapPhat {

    private Date ngayCapPhatThuHoi;
    private int maBoPhan;
    private int maNhom;
    private int sbd;
    private int soLuong;
    private String tinhTrang;
    private int daKy;
    private int capPhat;
    private String ghiChu;

    public ThuHoiCapPhat() {
    }

    public ThuHoiCapPhat(Date ngayCapPhatThuHoi, int maBoPhan, int maNhom, int sbd, int soLuong, String tinhTrang, int daKy, int capPhat, String ghiChu) {
        this.ngayCapPhatThuHoi = ngayCapPhatThuHoi;
        this.maBoPhan = maBoPhan;
        this.maNhom = maNhom;
        this.sbd = sbd;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.daKy = daKy;
        this.capPhat = capPhat;
        this.ghiChu = ghiChu;
    }

    public Date getNgayThuCapPhatThuHoi() {
        return ngayCapPhatThuHoi;
    }

    public void setNgayThuCapPhatThuHoi(Date ngayThuCapPhatThuHoi) {
        this.ngayCapPhatThuHoi = ngayThuCapPhatThuHoi;
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public int getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(int maNhom) {
        this.maNhom = maNhom;
    }

    public int getSbd() {
        return sbd;
    }

    public void setSbd(int sbd) {
        this.sbd = sbd;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getDaKy() {
        return daKy;
    }

    public void setDaKy(int daKy) {
        this.daKy = daKy;
    }

    public int getCapPhat() {
        return capPhat;
    }

    public void setCapPhat(int capPhat) {
        this.capPhat = capPhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
