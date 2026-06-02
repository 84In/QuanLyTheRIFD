package com.stapimex.dao;

import com.stapimex.model.The;
import com.stapimex.model.view.TheView;

import java.util.List;

public interface TheDao {
    List<TheView> findAll();

    List<TheView> search(
            Integer maBoPhan,
            Integer maNhom,
            Integer sbd
    );

    The findBySbd(int sbd);

    boolean insert(The the);

    boolean update(The the);

    boolean delete(int sbd);

}
