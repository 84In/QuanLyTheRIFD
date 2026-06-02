package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.TheDao;
import com.stapimex.model.The;
import com.stapimex.model.view.TheView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TheDaoImpl implements TheDao {

    private static final String SELECT_VIEW = """
            SELECT
                sbd,
                ma_nhom,
                ten_nhom,
                ma_bo_phan,
                ten_bo_phan,
                so_luong
            FROM vw_the
            """;

    @Override
    public List<TheView> findAll() {

        List<TheView> list = new ArrayList<>();

        String sql = SELECT_VIEW + " ORDER BY sbd";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                list.add(mapTheView(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<TheView> search(Integer maBoPhan, Integer maNhom, Integer sbd) {
        List<TheView> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT
            sbd,
            ma_nhom,
            ten_nhom,
            ma_bo_phan,
            ten_bo_phan,
            so_luong
        FROM vw_the
        WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        if (maBoPhan != null) {
            sql.append(" AND ma_bo_phan = ?");
            params.add(maBoPhan);
        }

        if (maNhom != null) {
            sql.append(" AND ma_nhom = ?");
            params.add(maNhom);
        }

        if (sbd != null) {
            sql.append(" AND sbd = ?");
            params.add(sbd);
        }

        sql.append(" ORDER BY sbd");

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql.toString())
        ) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapTheView(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public The findBySbd(int sbd) {

        String sql = """
                SELECT
                    sbd,
                    ma_nhom,
                    ma_bo_phan,
                    so_luong
                FROM The
                WHERE sbd = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, sbd);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapThe(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(The the) {

        String sql = """
                INSERT INTO The
                (
                    sbd,
                    ma_nhom,
                    ma_bo_phan,
                    so_luong
                )
                VALUES
                (
                    ?,
                    ?,
                    ?,
                    ?
                )
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, the.getSbd());
            ps.setInt(2, the.getMaNhom());
            ps.setInt(3, the.getMaBoPhan());
            ps.setInt(4, the.getSoLuong());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(The the) {

        String sql = """
                UPDATE The
                SET
                    ma_nhom = ?,
                    ma_bo_phan = ?,
                    so_luong = ?
                WHERE sbd = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, the.getMaNhom());
            ps.setInt(2, the.getMaBoPhan());
            ps.setInt(3, the.getSoLuong());
            ps.setInt(4, the.getSbd());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int sbd) {

        String sql = """
                DELETE FROM The
                WHERE sbd = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, sbd);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private The mapThe(ResultSet rs) throws Exception {

        The item = new The();

        item.setSbd(rs.getInt("sbd"));
        item.setMaNhom(rs.getInt("ma_nhom"));
        item.setMaBoPhan(rs.getInt("ma_bo_phan"));
        item.setSoLuong(rs.getInt("so_luong"));

        return item;
    }

    private TheView mapTheView(ResultSet rs) throws Exception {

        TheView item = new TheView();

        item.setSbd(rs.getInt("sbd"));

        item.setMaNhom(rs.getInt("ma_nhom"));
        item.setTenNhom(rs.getString("ten_nhom"));

        item.setMaBoPhan(rs.getInt("ma_bo_phan"));
        item.setTenBoPhan(rs.getString("ten_bo_phan"));

        item.setSoLuong(rs.getInt("so_luong"));

        return item;
    }
}