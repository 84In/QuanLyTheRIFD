package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.ThuHoiCapPhatDao;
import com.stapimex.model.ThuHoiCapPhat;
import com.stapimex.model.view.ThuHoiCapPhatView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThuHoiCapPhatImpl implements ThuHoiCapPhatDao {

    @Override
    public List<ThuHoiCapPhatView> findAllCapPhat() {
        String sql =
                """
                        SELECT *
                        FROM vw_cap_phat
                        ORDER BY ngay_cap_phat_thu_hoi DESC
                        """;

        List<ThuHoiCapPhatView> list =
                new ArrayList<>();

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                ThuHoiCapPhatView item =
                        new ThuHoiCapPhatView();

                item.setNgayCapPhatThuHoi(
                        rs.getDate("ngay_cap_phat_thu_hoi")
                );

                item.setSbd(
                        rs.getInt("sbd")
                );

                item.setMaNhom(
                        rs.getInt("ma_nhom")
                );

                item.setTenNhom(
                        rs.getString("ten_nhom")
                );

                item.setMaBoPhan(
                        rs.getInt("ma_bo_phan")
                );

                item.setTenBoPhan(
                        rs.getString("ten_bo_phan")
                );

                item.setSoLuong(
                        rs.getInt("so_luong")
                );

                item.setTinhTrang(
                        rs.getString("tinh_trang")
                );

                item.setDaKy(
                        rs.getInt("da_ky")
                );

                item.setCapPhat(
                        rs.getInt("cap_phat")
                );

                item.setLoai(
                        rs.getString("loai")
                );

                item.setGhiChu(
                        rs.getString("ghi_chu")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ThuHoiCapPhatView> findAllThuHoi() {
        String sql =
                """
                        SELECT *
                        FROM vw_thu_hoi
                        ORDER BY ngay_cap_phat_thu_hoi DESC
                        """;

        List<ThuHoiCapPhatView> list =
                new ArrayList<>();

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                ThuHoiCapPhatView item =
                        new ThuHoiCapPhatView();

                item.setNgayCapPhatThuHoi(
                        rs.getDate("ngay_cap_phat_thu_hoi")
                );

                item.setSbd(
                        rs.getInt("sbd")
                );

                item.setMaNhom(
                        rs.getInt("ma_nhom")
                );

                item.setTenNhom(
                        rs.getString("ten_nhom")
                );

                item.setMaBoPhan(
                        rs.getInt("ma_bo_phan")
                );

                item.setTenBoPhan(
                        rs.getString("ten_bo_phan")
                );

                item.setSoLuong(
                        rs.getInt("so_luong")
                );

                item.setTinhTrang(
                        rs.getString("tinh_trang")
                );

                item.setDaKy(
                        rs.getInt("da_ky")
                );

                item.setCapPhat(
                        rs.getInt("cap_phat")
                );

                item.setLoai(
                        rs.getString("loai")
                );

                item.setGhiChu(
                        rs.getString("ghi_chu")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean insert(
            ThuHoiCapPhat item
    ) {

        Connection conn = null;

        try {

            conn =
                    DBConnection.getConnection();

            conn.setAutoCommit(false);

            //--------------------------
            // Kiểm tra tồn kho
            //--------------------------

            int soLuongHienTai =
                    getSoLuongHienTai(
                            conn,
                            item
                    );


            if (soLuongHienTai < 0) {

                throw new RuntimeException(
                        "Không tìm thấy thẻ"
                );
            }

            if (
                    item.getCapPhat() == 1
                            &&
                            soLuongHienTai < item.getSoLuong()
            ) {

                throw new RuntimeException(
                        "Số lượng tồn chỉ còn "
                                + soLuongHienTai
                                + " thẻ"
                );
            }


            //--------------------------
            // Lưu lịch sử
            //--------------------------

            String insertSql = """
                    INSERT INTO Thu_Hoi_Cap_Phat
                    (
                        ngay_cap_phat_thu_hoi,
                        ma_bo_phan,
                        ma_nhom,
                        sbd,
                        so_luong,
                        tinh_trang,
                        da_ky,
                        cap_phat,
                        ghi_chu
                    )
                    VALUES
                    (
                        ?,?,?,?,?,?,?,?,?
                    )
                    """;

            try (
                    PreparedStatement ps =
                            conn.prepareStatement(
                                    insertSql
                            )
            ) {

                ps.setDate(
                        1,
                        new Date(
                                item.getNgayThuCapPhatThuHoi()
                                        .getTime()
                        )
                );

                ps.setInt(
                        2,
                        item.getMaBoPhan()
                );

                ps.setInt(
                        3,
                        item.getMaNhom()
                );

                ps.setInt(
                        4,
                        item.getSbd()
                );

                ps.setInt(
                        5,
                        item.getSoLuong()
                );

                ps.setString(
                        6,
                        item.getTinhTrang()
                );

                ps.setInt(
                        7,
                        item.getDaKy()
                );

                ps.setInt(
                        8,
                        item.getCapPhat()
                );

                ps.setString(
                        9,
                        item.getGhiChu()
                );

                if (ps.executeUpdate() <= 0) {

                    conn.rollback();

                    return false;
                }
            }

            //--------------------------
            // Cập nhật số lượng thẻ
            //--------------------------

            if (!updateSoLuongThe(
                    conn,
                    item,
                    true
            )) {

                conn.rollback();

                return false;
            }

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();
                }

            } catch (Exception ignored) {
            }

            throw new RuntimeException(
                    e.getMessage()
            );

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public List<ThuHoiCapPhatView> search(java.util.Date tuNgay, java.util.Date denNgay, Integer maBoPhan, Integer maNhom, Integer sbd, int capPhat) {
        List<ThuHoiCapPhatView> list =
                new ArrayList<>();

        StringBuilder sql =
                new StringBuilder("""
                        SELECT *
                        FROM vw_thu_hoi_cap_phat
                        WHERE cap_phat = ?
                        """);

        List<Object> params =
                new ArrayList<>();

        params.add(capPhat);

        if (tuNgay != null) {

            sql.append(
                    " AND ngay_cap_phat_thu_hoi >= ?"
            );

            params.add(
                    new java.sql.Date(
                            tuNgay.getTime()
                    )
            );
        }

        if (denNgay != null) {

            sql.append(
                    " AND ngay_cap_phat_thu_hoi <= ?"
            );

            params.add(
                    new java.sql.Date(
                            denNgay.getTime()
                    )
            );
        }

        if (maBoPhan != null) {

            sql.append(
                    " AND ma_bo_phan = ?"
            );

            params.add(maBoPhan);
        }

        if (maNhom != null) {

            sql.append(
                    " AND ma_nhom = ?"
            );

            params.add(maNhom);
        }

        if (sbd != null) {

            sql.append(
                    " AND sbd = ?"
            );

            params.add(sbd);
        }

        sql.append(
                " ORDER BY ngay_cap_phat_thu_hoi DESC"
        );

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql.toString()
                        )
        ) {

            for (
                    int i = 0;
                    i < params.size();
                    i++
            ) {

                ps.setObject(
                        i + 1,
                        params.get(i)
                );
            }

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                ThuHoiCapPhatView item =
                        new ThuHoiCapPhatView();

                item.setNgayCapPhatThuHoi(
                        rs.getDate(
                                "ngay_cap_phat_thu_hoi"
                        )
                );

                item.setSbd(
                        rs.getInt("sbd")
                );

                item.setMaNhom(
                        rs.getInt("ma_nhom")
                );

                item.setTenNhom(
                        rs.getString("ten_nhom")
                );

                item.setMaBoPhan(
                        rs.getInt("ma_bo_phan")
                );

                item.setTenBoPhan(
                        rs.getString("ten_bo_phan")
                );

                item.setSoLuong(
                        rs.getInt("so_luong")
                );

                item.setTinhTrang(
                        rs.getString("tinh_trang")
                );

                item.setDaKy(
                        rs.getInt("da_ky")
                );

                item.setGhiChu(
                        rs.getString("ghi_chu")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean remove(ThuHoiCapPhat item) {
        Connection conn = null;

        try {

            conn =
                    DBConnection.getConnection();

            conn.setAutoCommit(false);

            //--------------------------
            // Kiểm tra tồn kho
            //--------------------------

            int soLuongHienTai =
                    getSoLuongHienTai(
                            conn,
                            item
                    );


            if (
                    item.getCapPhat() == 0
                            &&
                            soLuongHienTai < item.getSoLuong()
            ) {

                throw new RuntimeException(
                        "Không thể hoàn trả thẻ thu hồi vì thiếu "
                                + (item.getSoLuong() - soLuongHienTai)
                                + " thẻ"
                );
            }


            //--------------------------
            // Lưu lịch sử
            //--------------------------

            String removeSql = """
                    DELETE FROM Thu_Hoi_Cap_Phat
                    WHERE
                       ngay_cap_phat_thu_hoi = ?
                       AND
                       ma_bo_phan = ?
                       AND
                       ma_nhom = ?
                       AND
                       sbd = ?
                       AND
                       so_luong = ?
                    """;

            try (
                    PreparedStatement ps =
                            conn.prepareStatement(
                                    removeSql
                            )
            ) {

                ps.setDate(
                        1,
                        new Date(
                                item.getNgayThuCapPhatThuHoi()
                                        .getTime()
                        )
                );

                ps.setInt(
                        2,
                        item.getMaBoPhan()
                );

                ps.setInt(
                        3,
                        item.getMaNhom()
                );

                ps.setInt(
                        4,
                        item.getSbd()
                );

                ps.setInt(
                        5,
                        item.getSoLuong()
                );

                if (ps.executeUpdate() <= 0) {

                    conn.rollback();

                    return false;
                }
            }

            //--------------------------
            // Cập nhật số lượng thẻ
            //--------------------------

            if (!updateSoLuongThe(
                    conn,
                    item,
                    false
            )) {

                conn.rollback();

                return false;
            }

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();
                }

            } catch (Exception ignored) {
                throw new RuntimeException(
                        e.getMessage()
                ); // Them bo sung
            }

            throw new RuntimeException(
                    e.getMessage()
            );

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public boolean updateKyNhan(ThuHoiCapPhat item) {

        String sql = """
                UPDATE Thu_Hoi_Cap_Phat
                SET da_ky = ?
                WHERE
                    ngay_cap_phat_thu_hoi = ?
                    AND
                    ma_bo_phan = ?
                    AND
                    ma_nhom = ?
                    AND
                    sbd = ?
                    AND
                    so_luong = ?
                """;
        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);
        ) {
            ps.setInt(1, item.getDaKy());
            ps.setDate(
                    2,
                    new Date(
                            item.getNgayThuCapPhatThuHoi()
                                    .getTime()
                    )
            );

            ps.setInt(
                    3,
                    item.getMaBoPhan()
            );

            ps.setInt(
                    4,
                    item.getMaNhom()
            );

            ps.setInt(
                    5,
                    item.getSbd()
            );

            ps.setInt(
                    6,
                    item.getSoLuong()
            );

            if (ps.executeUpdate() <= 0) {
                conn.rollback();
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private int getSoLuongHienTai(
            Connection conn,
            ThuHoiCapPhat item
    ) throws Exception {

        String sql = """
                SELECT so_luong
                FROM The
                WHERE
                    sbd = ?
                    AND ma_nhom = ?
                    AND ma_bo_phan = ?
                """;


        try (
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    item.getSbd()
            );

            ps.setInt(
                    2,
                    item.getMaNhom()
            );

            ps.setInt(
                    3,
                    item.getMaBoPhan()
            );
            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        "so_luong"
                );
            }
        }

        return -1;
    }

    private boolean updateSoLuongThe(
            Connection conn,
            ThuHoiCapPhat item,
            boolean themMoi
    ) throws Exception {

        String sql = """
                UPDATE The
                SET so_luong =
                    so_luong + ?
                WHERE
                    sbd = ?
                    AND ma_nhom = ?
                    AND ma_bo_phan = ?
                """;

        try (
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            int change =
                    item.getCapPhat() == 1
                            ?  (themMoi ?  -item.getSoLuong() : +item.getSoLuong())
                            : (themMoi ? +item.getSoLuong() : -item.getSoLuong());

            ps.setInt(
                    1,
                    change
            );

            ps.setInt(
                    2,
                    item.getSbd()
            );

            ps.setInt(
                    3,
                    item.getMaNhom()
            );

            ps.setInt(
                    4,
                    item.getMaBoPhan()
            );

            return ps.executeUpdate() > 0;
        }
    }

}