package com.stapimex.view.bophan;

import com.stapimex.controller.BoPhanController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BoPhanPanel extends JPanel {

    private JTable table;

    private DefaultTableModel model;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private final BoPhanController controller;

    public BoPhanPanel() {
        controller = new BoPhanController();
        initComponents();
        controller.loadTable(model);
    }
    private void initComponents() {

        setLayout(new BorderLayout(10,10));

        JLabel title =
                new JLabel("Quản Lý Bộ Phận");

        title.setFont(
                title.getFont()
                        .deriveFont(
                                Font.BOLD,
                                22f
                        )
        );

        add(title, BorderLayout.NORTH);

        model =
                new DefaultTableModel(
                        new Object[]{
                                "Mã",
                                "Tên bộ phận"
                        },
                        0
                );

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

        JPanel buttonPanel =
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

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );
    }
}