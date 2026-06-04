package com.stapimex.view.capphat;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CapPhatDialog extends JDialog {

    private JDateChooser jdNgayCapPhat;

    private JSpinner spSoLuong;

    private JTextArea txtGhiChu;

    private JComboBox<String> cboTinhTrang;

    private JCheckBox chkDaKy;

    private JButton btnSave;
    private JButton btnCancel;

    private boolean saved;

    public CapPhatDialog(
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
        // Ngày cấp phát (THÊM MỚI Ở ĐÂY)
        //-------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Ngày cấp phát:"), gbc);

        jdNgayCapPhat = new JDateChooser();
        jdNgayCapPhat.setDateFormatString("dd/MM/yyyy");
        jdNgayCapPhat.setDate(new Date());

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(jdNgayCapPhat, gbc);

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

        txtGhiChu =
                new JTextArea(
                        5,
                        20
                );

        gbc.gridx = 1;

        panel.add(
                new JScrollPane(
                        txtGhiChu
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

    public int getSoLuong() {
        return (Integer)
                spSoLuong.getValue();
    }

    public int getTinhTrang() {
        return cboTinhTrang.getSelectedIndex();
    }

    public boolean isDaKy() {
        return chkDaKy.isSelected();
    }

    public String getGhiChu() {
        return txtGhiChu.getText().trim();
    }
}