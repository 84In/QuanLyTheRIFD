package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.BoPhanDao;
import com.stapimex.model.BoPhan;
import com.stapimex.model.ComboItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoPhanDaoImpl implements BoPhanDao {
    @Override
    public List<ComboItem> findAllCombo() {
        List<ComboItem> list = new ArrayList<>();

        String sql = """
                SELECT id, ten_bo_phan
                FROM Bo_Phan
                ORDER BY id  ASC
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
                                rs.getString("ten_bo_phan")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<BoPhan> findAll() {
        List<BoPhan> list =
                new ArrayList<>();

        String sql =
                """
                        SELECT id, ten_bo_phan
                        FROM Bo_Phan
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

                BoPhan item =
                        new BoPhan();

                item.setId(
                        rs.getInt("id")
                );

                item.setTenBoPhan(
                        rs.getString(
                                "ten_bo_phan"
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
    public boolean insert(BoPhan boPhan) {
        return false;
    }

    @Override
    public boolean update(BoPhan boPhan) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
