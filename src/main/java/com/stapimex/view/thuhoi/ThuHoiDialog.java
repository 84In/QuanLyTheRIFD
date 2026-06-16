
package com.stapimex.view.thuhoi;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ThuHoiDialog extends JDialog {

    private JDateChooser jdNgayThuHoi;

    private JSpinner spSoLuong;

    private JComboBox<String> cboGhiChu;

    private JComboBox<String> cboTinhTrang;

    private JCheckBox chkDaKy;

    private JButton btnSave;
    private JButton btnCancel;

    private boolean saved;

    public ThuHoiDialog(
            Window owner,
            String title
    ) {

        super(
                owner,
                title,
                ModalityType.APPLICATION_MODAL
        );

        initComponents();

        setSize(500,400);

        setLocationRelativeTo(owner);
    }

    private void initComponents() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8,8,8,8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        //-------------------------
        // Ngày thu hồi  (THÊM MỚI Ở ĐÂY)
        //-------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Ngày thu hồi:"), gbc);

        jdNgayThuHoi = new JDateChooser();
        jdNgayThuHoi.setDateFormatString("dd/MM/yyyy");
        jdNgayThuHoi.setDate(new Date());

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(jdNgayThuHoi, gbc);

        //-------------------------
        // Số lượng
        //-------------------------

//        gbc.gridx = 0;
//        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;

        panel.add(
                new JLabel("Số lượng"),
                gbc
        );

        spSoLuong =
                new JSpinner(
                        new SpinnerNumberModel(
                                3,
                                1,
                                999,
                                1
                        )
                );

        gbc.gridx = 1;

        panel.add(spSoLuong, gbc);

        //-------------------------
        // Tình trạng
        //-------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        panel.add(
                new JLabel("Tình trạng"),
                gbc
        );

        cboTinhTrang =
                new JComboBox<>(
                        new String[]{
                                "Bình thường",
                                "Mất",
                                "Hỏng"
                        }
                );

        gbc.gridx = 1;

        panel.add(
                cboTinhTrang,
                gbc
        );

        //-------------------------
        // Ký
        //-------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        panel.add(
                new JLabel("Ký nhận"),
                gbc
        );

        chkDaKy =
                new JCheckBox(
                        "Đã ký"
                );

        gbc.gridx = 1;

        panel.add(
                chkDaKy,
                gbc
        );

        //-------------------------
        // Ghi chú
        //-------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        panel.add(
                new JLabel("Ghi chú"),
                gbc
        );

        cboGhiChu = new JComboBox<>(
                new String[]{
                        "",
                        "Thu hồi bình thường",
                        "Thu hồi cuối tháng",
                        "Thẻ hỏng",
                        "Cấp phát bổ sung",
                        "Điều chỉnh số lượng",
                }
        );

        gbc.gridx = 1;

        panel.add(
                new JScrollPane(
                        cboGhiChu
                ),
                gbc
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        btnSave =
                new JButton("Lưu");

        btnCancel =
                new JButton("Hủy");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnSave.addActionListener(e -> {

            saved = true;
            //Xử lý cập nhật tại đây (gọi action controller)

            dispose();
        });

        btnCancel.addActionListener(e ->
                dispose()
        );

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isSaved() {
        return saved;
    }

    public Date getNgayThuHoi() {
        return jdNgayThuHoi.getDate();
    }

    public int getSoLuong() {
        return (Integer)
                spSoLuong.getValue();
    }

    public int getTinhTrangValue() {
        return cboTinhTrang.getSelectedIndex();
    }

    public String getTinhTrangText() {
        return cboTinhTrang
                .getSelectedItem()
                .toString();
    }

    public int getDaKy() {
        return chkDaKy.isSelected() ? 1 : 0;
    }

    public String getGhiChu() {
        return cboGhiChu.getSelectedItem().toString().trim();
    }
}