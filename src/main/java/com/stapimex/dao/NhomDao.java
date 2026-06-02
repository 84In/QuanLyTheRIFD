package com.stapimex.dao;

import com.stapimex.model.ComboItem;
import com.stapimex.model.Nhom;

import java.util.List;

public interface NhomDao {

    List<ComboItem> findAllCombo();

    List<Nhom> findAll();

    boolean insert(Nhom nhom);

    boolean update(Nhom nhom);

    boolean delete(int id);
}
