package com.stapimex.model.view;

import java.util.Date;

public class ThuHoiCapPhatView {
    private Date ngayCapPhatThuHoi;

    private int sbd;

    private int maNhom;
    private String tenNhom;

    private int maBoPhan;
    private String tenBoPhan;

    private int soLuong;

    private String tinhTrang;

    private int daKy;

    private int capPhat;

    private String loai;

    private String ghiChu;

    public ThuHoiCapPhatView() {
    }

    public ThuHoiCapPhatView(Date ngayCapPhatThuHoi, int sbd, int maNhom, String tenNhom, int maBoPhan, String tenBoPhan, int soLuong, String tinhTrang, int daKy, int capPhat, String loai, String ghiChu) {
        this.ngayCapPhatThuHoi = ngayCapPhatThuHoi;
        this.sbd = sbd;
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.maBoPhan = maBoPhan;
        this.tenBoPhan = tenBoPhan;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.daKy = daKy;
        this.capPhat = capPhat;
        this.loai = loai;
        this.ghiChu = ghiChu;
    }

    public Date getNgayCapPhatThuHoi() {
        return ngayCapPhatThuHoi;
    }

    public void setNgayCapPhatThuHoi(Date ngayCapPhatThuHoi) {
        this.ngayCapPhatThuHoi = ngayCapPhatThuHoi;
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

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public String getTenBoPhan() {
        return tenBoPhan;
    }

    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /* Dùng để kiểm tra xử lý*/
    @Override
    public String toString() {
        return "ThuHoiCapPhatView{" +
                "ngayCapPhatThuHoi=" + ngayCapPhatThuHoi +
                ", sbd=" + sbd +
                ", maNhom=" + maNhom +
                ", tenNhom='" + tenNhom + '\'' +
                ", maBoPhan=" + maBoPhan +
                ", tenBoPhan='" + tenBoPhan + '\'' +
                ", soLuong=" + soLuong +
                ", tinhTrang='" + tinhTrang + '\'' +
                ", daKy=" + daKy +
                ", capPhat=" + capPhat +
                ", loai='" + loai + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
