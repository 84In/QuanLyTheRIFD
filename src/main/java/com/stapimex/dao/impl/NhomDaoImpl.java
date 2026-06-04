package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.NhomDao;
import com.stapimex.model.ComboItem;
import com.stapimex.model.Nhom;

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
                ORDER BY id ASC
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            list.add(new ComboItem(
                    0, "Tất cả"
            ));
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

    @Override
    public List<Nhom> findAll() {

        List<Nhom> list =
                new ArrayList<>();

        String sql =
                """
                SELECT id, ten_nhom
                FROM Nhom
                ORDER BY id ASC
                """;

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Nhom item =
                        new Nhom();

                item.setId(
                        rs.getInt("id")
                );

                item.setTenNhom(
                        rs.getString(
                                "ten_nhom"
                        )
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean insert(Nhom nhom) {
        return false;
    }

    @Override
    public boolean update(Nhom nhom) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
