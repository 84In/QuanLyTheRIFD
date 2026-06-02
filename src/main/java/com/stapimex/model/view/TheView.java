package com.stapimex.model.view;

import com.stapimex.model.The;

public class TheView extends The {

    private String tenBoPhan;
    private String tenNhom;

    public TheView(){

    }

    public TheView(int sbd, int maNhom, int maBoPhan, int soLuong, String tenBoPhan, String tenNhom) {
        super(sbd, maNhom, maBoPhan, soLuong);
        this.tenBoPhan = tenBoPhan;
        this.tenNhom = tenNhom;
    }

    public String getTenBoPhan() {
        return tenBoPhan;
    }

    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }
}
