package com.stapimex.dao;

import com.stapimex.model.BoPhan;
import com.stapimex.model.ComboItem;

import java.util.List;

public interface BoPhanDao {

    List<ComboItem> findAllCombo();

    List<BoPhan> findAll();

    boolean insert(BoPhan boPhan);

    boolean update(BoPhan boPhan);

    boolean delete(int id);
}
