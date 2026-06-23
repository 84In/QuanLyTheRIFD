package com.stapimex.dao.impl;

import com.stapimex.config.DBConnection;
import com.stapimex.dao.DashboardDAO;
import com.stapimex.model.view.ThongKeNgayView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl implements DashboardDAO {
    public int tongCapPhat(int month, int year) {

        String sql =
                """
                SELECT IFNULL(SUM(so_luong),0)
                FROM Thu_Hoi_Cap_Phat
                WHERE cap_phat = 1
                AND MONTH(ngay_cap_phat_thu_hoi)=?
                AND YEAR(ngay_cap_phat_thu_hoi)=?
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int tongThuHoi(int month, int year) {

        String sql =
                """
                SELECT IFNULL(SUM(so_luong),0)
                FROM Thu_Hoi_Cap_Phat
                WHERE cap_phat = 0
                AND MONTH(ngay_cap_phat_thu_hoi)=?
                AND YEAR(ngay_cap_phat_thu_hoi)=?
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<ThongKeNgayView> thongKeTheoNgay(
            int month,
            int year
    ) {

        List<ThongKeNgayView> list = new ArrayList<>();

        String sql =
                """
                SELECT
                    DAY(ngay_cap_phat_thu_hoi) ngay,

                    SUM(
                        CASE
                            WHEN cap_phat = 1
                            THEN so_luong
                            ELSE 0
                        END
                    ) cap_phat,

                    SUM(
                        CASE
                            WHEN cap_phat = 0
                            THEN so_luong
                            ELSE 0
                        END
                    ) thu_hoi

                FROM Thu_Hoi_Cap_Phat

                WHERE MONTH(ngay_cap_phat_thu_hoi)=?
                  AND YEAR(ngay_cap_phat_thu_hoi)=?

                GROUP BY DAY(ngay_cap_phat_thu_hoi)

                ORDER BY DAY(ngay_cap_phat_thu_hoi)
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(
                        new ThongKeNgayView(
                                rs.getInt("ngay"),
                                rs.getInt("cap_phat"),
                                rs.getInt("thu_hoi")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
