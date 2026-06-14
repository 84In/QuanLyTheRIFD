package com.stapimex.dao;

import com.stapimex.model.ThuHoiCapPhat;
import com.stapimex.model.view.ThuHoiCapPhatView;

import java.util.Date;
import java.util.List;

public interface ThuHoiCapPhatDao {
    List<ThuHoiCapPhatView> findAllCapPhat();
    boolean insert(ThuHoiCapPhat thuHoiCapPhat);
    List<ThuHoiCapPhatView> search(
            Date tuNgay,
            Date denNgay,
            Integer maBoPhan,
            Integer maNhom,
            Integer sbd,
            int capPhat
    );
}
