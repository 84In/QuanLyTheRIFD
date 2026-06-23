package com.stapimex.dao;

import com.stapimex.model.view.ThongKeNgayView;

import java.util.List;

public interface DashboardDAO {

    public int tongCapPhat(int month, int year);

    public int tongThuHoi(int month, int year);

    public List<ThongKeNgayView> thongKeTheoNgay(
            int month,
            int year
    );
}
