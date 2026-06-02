package com.stapimex.model;

public class Nhom {
    private int id;
    private String tenNhom;

    public Nhom() {

    }
    public Nhom(int id,String tenNhom) {
        this.tenNhom = tenNhom;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }
}
