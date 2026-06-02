package com.stapimex.view.nhom;

import com.stapimex.controller.NhomController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhomPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private final NhomController controller;

    public NhomPanel() {
        controller = new NhomController();
        initComponents();
        controller.loadTable(model);
    }

    private void initComponents() {

        setLayout(new BorderLayout(10,10));

        JLabel lblTitle =
                new JLabel("Quản Lý Nhóm");

        lblTitle.setFont(
                lblTitle.getFont()
                        .deriveFont(
                                Font.BOLD,
                                22f
                        )
        );

        add(lblTitle, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Mã",
                        "Tên nhóm"
                },
                0
        ){
            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        table = new JTable(model) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                }
                return c;
            }
        };
        table.setRowHeight(35);

        table.getTableHeader()
                .setReorderingAllowed(false);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        btnAdd =
                new JButton("Thêm");

        btnEdit =
                new JButton("Sửa");

        btnDelete =
                new JButton("Xóa");

        bottomPanel.add(btnAdd);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }
}