package com.stapimex.controller;

import com.stapimex.dao.TheDao;
import com.stapimex.dao.impl.TheDaoImpl;
import com.stapimex.model.view.TheView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TheController {
    private final TheDao theDao;

    public TheController() {
        this.theDao = new TheDaoImpl();
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
}
