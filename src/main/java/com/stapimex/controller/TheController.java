package com.stapimex.controller;

import com.stapimex.dao.BoPhanDao;
import com.stapimex.dao.NhomDao;
import com.stapimex.dao.TheDao;
import com.stapimex.dao.impl.BoPhanDaoImpl;
import com.stapimex.dao.impl.NhomDaoImpl;
import com.stapimex.dao.impl.TheDaoImpl;
import com.stapimex.model.BoPhan;
import com.stapimex.model.ComboItem;
import com.stapimex.model.Nhom;
import com.stapimex.model.The;
import com.stapimex.model.view.TheView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TheController {
    private final TheDao theDao;
    private final BoPhanDao boPhanDao;
    private final NhomDao nhomDao;

    public TheController() {

        this.theDao = new TheDaoImpl();
        this.boPhanDao = new BoPhanDaoImpl();
        this.nhomDao = new NhomDaoImpl();

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
    public void loadTable(DefaultTableModel model) {

        model.setRowCount(0);

        List<TheView> list = theDao.findAll();

        for (TheView item : list) {

            model.addRow(new Object[]{
                    item.getSbd(),
                    item.getTenNhom(),
                    item.getTenBoPhan(),
                    item.getSoLuong()
            });
        }
    }

    public void search(
            DefaultTableModel model,
            Integer maBoPhan,
            Integer maNhom,
            Integer sbd
    ) {

        model.setRowCount(0);

        List<TheView> list =
                theDao.search(
                        maBoPhan,
                        maNhom,
                        sbd
                );

        for (TheView item : list) {

            model.addRow(new Object[]{
                    item.getSbd(),
                    item.getTenNhom(),
                    item.getTenBoPhan(),
                    item.getSoLuong()
            });
        }
    }

    public void insert(The the) {

        if (!theDao.insert(the)) {

            throw new RuntimeException(
                    "Thêm thẻ thất bại"
            );
        }
    }

    public void capPhat(int sbd, int soLuong, int tinhTrang, boolean daKy, String ghiChu) {


    }
    public void thuHoi(
            int sbd,
            int soLuong,
            int tinhTrang,
            boolean daKy,
            String ghiChu
    ){

    }
}
