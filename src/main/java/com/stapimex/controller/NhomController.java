package com.stapimex.controller;

import com.stapimex.dao.NhomDao;
import com.stapimex.dao.impl.NhomDaoImpl;
import com.stapimex.model.Nhom;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NhomController {


    private final NhomDao nhomDao;

    public NhomController() {
        this.nhomDao = new NhomDaoImpl();
    }

    public void loadTable(DefaultTableModel model){

        model.setRowCount(0);

        List<Nhom> list =
               nhomDao.findAll();

        for(Nhom item : list){

            model.addRow(
                    new Object[]{
                            item.getId(),
                            item.getTenNhom()
                    }
            );
        }
    }
}
