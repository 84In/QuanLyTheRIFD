package com.stapimex.model.view;

public class ThongKeNgayView {

    private int ngay;
    private int capPhat;
    private int thuHoi;

    public ThongKeNgayView() {
    }

    public ThongKeNgayView(
            int ngay,
            int capPhat,
            int thuHoi
    ) {
        this.ngay = ngay;
        this.capPhat = capPhat;
        this.thuHoi = thuHoi;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getCapPhat() {
        return capPhat;
    }

    public void setCapPhat(int capPhat) {
        this.capPhat = capPhat;
    }

    public int getThuHoi() {
        return thuHoi;
    }

    public void setThuHoi(int thuHoi) {
        this.thuHoi = thuHoi;
    }
}
