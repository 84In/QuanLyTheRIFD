package com.stapimex.view.the;

import com.stapimex.model.ComboItem;
import com.stapimex.model.The;

import javax.swing.*;
import java.awt.*;

public class TheDialog extends JDialog {

    private JTextField txtSbd;

    private JComboBox<ComboItem> cboBoPhan;
    private JComboBox<ComboItem> cboNhom;

    private JSpinner spSoLuong;

    private JButton btnSave;
    private JButton btnCancel;

    private boolean saved = false;

    public TheDialog(Window owner) {

        super(
                owner,
                "Thêm Thẻ RFID",
                ModalityType.APPLICATION_MODAL
        );

        initComponents();

        setSize(500, 320);
        setLocationRelativeTo(owner);
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //----------------------------------
        // SBD
        //----------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("SBD"), gbc);

        txtSbd = new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtSbd, gbc);

        //----------------------------------
        // Bộ phận
        //----------------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        gbc.weightx = 0;

        panel.add(new JLabel("Bộ phận"), gbc);

        cboBoPhan = new JComboBox<>();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(cboBoPhan, gbc);

        //----------------------------------
        // Nhóm
        //----------------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        gbc.weightx = 0;

        panel.add(new JLabel("Nhóm"), gbc);

        cboNhom = new JComboBox<>();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(cboNhom, gbc);

        //----------------------------------
        // Số lượng
        //----------------------------------

        gbc.gridx = 0;
        gbc.gridy++;

        panel.add(new JLabel("Số lượng"), gbc);

        spSoLuong = new JSpinner(
                new SpinnerNumberModel(
                        1,
                        1,
                        99999,
                        1
                )
        );

        gbc.gridx = 1;

        panel.add(spSoLuong, gbc);

        //----------------------------------
        // Buttons
        //----------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnCancel.addActionListener(
                e -> dispose()
        );

        btnSave.addActionListener(
                e -> {

                    if (validateData()) {

                        saved = true;

                        dispose();
                    }
                }
        );

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean validateData() {

        String sbd =
                txtSbd.getText().trim();

        if (sbd.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập SBD"
            );

            txtSbd.requestFocus();

            return false;
        }

        try {

            Integer.parseInt(sbd);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "SBD phải là số"
            );

            txtSbd.requestFocus();

            return false;
        }

        if (cboBoPhan.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn bộ phận"
            );

            return false;
        }

        if (cboNhom.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn nhóm"
            );

            return false;
        }

        return true;
    }

    public The getData() {

        ComboItem boPhan =
                (ComboItem)
                        cboBoPhan.getSelectedItem();

        ComboItem nhom =
                (ComboItem)
                        cboNhom.getSelectedItem();

        The the = new The();

        the.setSbd(
                Integer.parseInt(
                        txtSbd.getText().trim()
                )
        );

        the.setMaBoPhan(
                boPhan.getId()
        );

        the.setMaNhom(
                nhom.getId()
        );

        the.setSoLuong(
                (Integer)
                        spSoLuong.getValue()
        );

        return the;
    }

    public boolean isSaved() {
        return saved;
    }

    public JComboBox<ComboItem> getCboBoPhan() {
        return cboBoPhan;
    }

    public JComboBox<ComboItem> getCboNhom() {
        return cboNhom;
    }
}