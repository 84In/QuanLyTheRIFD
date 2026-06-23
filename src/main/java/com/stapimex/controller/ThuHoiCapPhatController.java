package com.stapimex.controller;

import com.stapimex.dao.BoPhanDao;
import com.stapimex.dao.NhomDao;
import com.stapimex.dao.TheDao;
import com.stapimex.dao.ThuHoiCapPhatDao;
import com.stapimex.dao.impl.BoPhanDaoImpl;
import com.stapimex.dao.impl.NhomDaoImpl;
import com.stapimex.dao.impl.TheDaoImpl;
import com.stapimex.dao.impl.ThuHoiCapPhatImpl;
import com.stapimex.model.ComboItem;
import com.stapimex.model.ThuHoiCapPhat;
import com.stapimex.model.view.ThuHoiCapPhatView;
import com.stapimex.util.DateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

public class ThuHoiCapPhatController {
    private ThuHoiCapPhatDao thuHoiCapPhatDao;
    private BoPhanDao boPhanDao;
    private NhomDao nhomDao;

    public ThuHoiCapPhatController(){
        thuHoiCapPhatDao = new ThuHoiCapPhatImpl();
        this.boPhanDao = new BoPhanDaoImpl();
        this.nhomDao = new NhomDaoImpl();

    }

    @Transient
    public void insert(ThuHoiCapPhat thuHoiCapPhat) {

        if (!thuHoiCapPhatDao.insert(thuHoiCapPhat)) {

            throw new RuntimeException(
                thuHoiCapPhat.getCapPhat() == 1 ? "Cấp phát thất bại!": "Thu hồi thất bai!"
            );
        }
    }

    public void loadBoPhan(
            JComboBox<ComboItem> comboBox
    ) {

        comboBox.removeAllItems();

        for (ComboItem item :
                boPhanDao.findAllCombo()) {

            comboBox.addItem(
                    new ComboItem(
                            item.getId(),
                            item.getName()
                    )
            );
        }
    }

    public void loadNhom(
            JComboBox<ComboItem> comboBox
    ) {

        comboBox.removeAllItems();

        for (ComboItem item :
                nhomDao.findAllCombo()) {

            comboBox.addItem(
                    new ComboItem(
                            item.getId(),
                            item.getName()
                    )
            );
        }
    }

    public void loadTable(DefaultTableModel model, boolean capPhat) {

        model.setRowCount(0);

        List<ThuHoiCapPhatView> list = capPhat ?
                thuHoiCapPhatDao.findAllCapPhat(): thuHoiCapPhatDao.findAllThuHoi();

        for (ThuHoiCapPhatView item : list) {

            model.addRow(
                    new Object[]{
                            DateUtil.format(
                                    item.getNgayCapPhatThuHoi()
                            ),

                            item.getSbd(),

                            item.getMaNhom(),
                            item.getTenNhom(),

                            item.getMaBoPhan(),
                            item.getTenBoPhan(),

                            item.getSoLuong(),

                            item.getTinhTrang(),

                            item.getDaKy() == 1
                                    ? "Đã ký"
                                    : "Chưa ký",

                            item.getGhiChu()
                    }
            );
        }
    }
    public void search(
            DefaultTableModel model,
            Date tuNgay,
            Date denNgay,
            Integer maBoPhan,
            Integer maNhom,
            Integer sbd,
            boolean capPhat
    ) {

        model.setRowCount(0);

        List<ThuHoiCapPhatView> list =
                thuHoiCapPhatDao.search(
                        tuNgay,
                        denNgay,
                        maBoPhan,
                        maNhom,
                        sbd,
                        capPhat ? 1 : 0 // cấp phát
                );

        for (ThuHoiCapPhatView item : list) {

            model.addRow(
                    new Object[]{
                            DateUtil.format(
                                    item.getNgayCapPhatThuHoi()
                            ),

                            item.getSbd(),

                            item.getMaNhom(),
                            item.getTenNhom(),

                            item.getMaBoPhan(),
                            item.getTenBoPhan(),

                            item.getSoLuong(),

                            item.getTinhTrang(),

                            item.getDaKy() == 1
                                    ? "Đã ký"
                                    : "Chưa ký",

                            item.getGhiChu()
                    }
            );
        }
    }

    @Transient
    public void remove(ThuHoiCapPhat item){
        thuHoiCapPhatDao.remove(item);
    }

    public void kyNhan(ThuHoiCapPhat item){
        thuHoiCapPhatDao.updateKyNhan(item);
    }
}
