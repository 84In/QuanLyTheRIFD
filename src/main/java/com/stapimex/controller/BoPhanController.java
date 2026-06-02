package com.stapimex.controller;

import com.stapimex.dao.BoPhanDao;
import com.stapimex.dao.NhomDao;
import com.stapimex.dao.impl.BoPhanDaoImpl;
import com.stapimex.dao.impl.NhomDaoImpl;
import com.stapimex.dao.impl.TheDaoImpl;
import com.stapimex.model.BoPhan;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BoPhanController {


    private final BoPhanDao boPhanDao;

    public BoPhanController() {
        this.boPhanDao = new BoPhanDaoImpl();
    }

    public void loadTable(DefaultTableModel model){

        model.setRowCount(0);

        List<BoPhan> list =
                boPhanDao.findAll();

        for(BoPhan item : list){

            model.addRow(
                    new Object[]{
                            item.getId(),
                            item.getTenBoPhan()
                    }
            );
        }
    }
}
