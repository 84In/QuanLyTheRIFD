package com.stapimex.model;

public class BoPhan {

    private int id;
    private String tenBoPhan;
    public BoPhan() {}
    public BoPhan(int id, String tenBoPhan) {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenBoPhan() {
        return tenBoPhan;
    }
    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
    }
}
