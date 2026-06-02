package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.NhomDao;
import com.stapimex.model.ComboItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhomDaoImpl implements NhomDao {


    @Override
    public List<ComboItem> findAllCombo() {

        List<ComboItem> list = new ArrayList<>();

        String sql = """
                SELECT id, ten_nhom
                FROM Nhom
                ORDER BY ten_nhom
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(
                        new ComboItem(
                                rs.getInt("id"),
                                rs.getString("ten_nhom")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
