package com.stapimex.controller;

import com.stapimex.dao.DashboardDAO;
import com.stapimex.dao.impl.DashboardDAOImpl;
import com.stapimex.model.view.ThongKeNgayView;

import java.util.List;

public class DashboardController {

    private final DashboardDAO dashboardDAO;

    public DashboardController() {
        this.dashboardDAO = new DashboardDAOImpl();
    }

    public int getTongCapPhat(int month, int year){
        return dashboardDAO.tongCapPhat(month,year);
    }

    public int getTongThuHoi(int month, int year){
        return dashboardDAO.tongThuHoi(month,year);
    }

    public List<ThongKeNgayView> getThongKeTheoNgay(int month, int year) {
        return dashboardDAO.thongKeTheoNgay(month,year);
    }
}
