package com.stapimex.view.the;

import com.stapimex.controller.TheController;
import com.stapimex.model.ComboItem;
import com.stapimex.model.The;
import com.stapimex.view.capphat.CapPhatDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ThePanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JComboBox<ComboItem> cboBoPhan;
    private JComboBox<ComboItem> cboNhom;

    private JTextField txtSbd;

    private JButton btnSearch;
    private JButton btnRefresh;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JButton btnCapPhat;
    private JButton btnThuHoi;

    private final TheController controller;

    public ThePanel() {

        controller = new TheController();

        initComponents();

        loadComboData();

        controller.loadTable(model);
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        // =========================
        // TITLE
        // =========================

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel lblTitle = new JLabel("Danh Sách Thẻ RFID");

        lblTitle.setFont(
                lblTitle.getFont()
                        .deriveFont(Font.BOLD, 22f)
        );

        topPanel.add(lblTitle, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // =========================
        // CENTER
        // =========================

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        // Search Panel

        JPanel searchPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        10
                )
        );

        cboBoPhan = new JComboBox<>();
        cboNhom = new JComboBox<>();

        cboBoPhan.setPreferredSize(
                new Dimension(200, 20)
        );

        cboNhom.setPreferredSize(
                new Dimension(200, 20)
        );

        txtSbd = new JTextField(10);

        btnSearch = new JButton("Tìm kiếm");
        btnRefresh = new JButton("Làm mới");

        searchPanel.add(new JLabel("Bộ phận"));
        searchPanel.add(cboBoPhan);

        searchPanel.add(new JLabel("Nhóm"));
        searchPanel.add(cboNhom);

        searchPanel.add(new JLabel("SBD"));
        searchPanel.add(txtSbd);

        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);

        centerPanel.add(searchPanel, BorderLayout.NORTH);

        // Table

        model = new DefaultTableModel(
                new Object[]{
                        "SBD",
                        "Nhóm",
                        "Bộ phận",
                        "Số lượng"
                },
                0
        ) {
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

        table.getTableHeader()
                .setReorderingAllowed(false);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(centerPanel, BorderLayout.CENTER);

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT
                )
        );

        btnCapPhat = new JButton("Cấp phát");
        btnThuHoi = new JButton("Thu hồi");

        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");

        bottomPanel.add(btnCapPhat);
        bottomPanel.add(btnThuHoi);

        bottomPanel.add(btnAdd);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);

        initEvents();
    }

    private void loadComboData() {

        cboBoPhan.addItem(
                new ComboItem(
                        0,
                        "Tất cả"
                )
        );

        cboNhom.addItem(
                new ComboItem(
                        0,
                        "Tất cả"
                )
        );


        controller.loadBoPhan(cboBoPhan);

        controller.loadNhom(cboNhom);

    }

    private void initEvents() {

        btnSearch.addActionListener(e -> {

            Integer maBoPhan = null;
            Integer maNhom = null;
            Integer sbd = null;

            ComboItem boPhan =
                    (ComboItem)
                            cboBoPhan.getSelectedItem();

            if (
                    boPhan != null
                            && boPhan.getId() > 0
            ) {

                maBoPhan = boPhan.getId();
            }

            ComboItem nhom =
                    (ComboItem)
                            cboNhom.getSelectedItem();

            if (
                    nhom != null
                            && nhom.getId() > 0
            ) {

                maNhom = nhom.getId();
            }

            String sbdText =
                    txtSbd.getText().trim();

            try {

                if (!sbdText.isEmpty()) {

                    sbd =
                            Integer.parseInt(
                                    sbdText
                            );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "SBD phải là số"
                );

                return;
            }

            controller.search(
                    model,
                    maBoPhan,
                    maNhom,
                    sbd
            );
        });

        btnRefresh.addActionListener(e -> {

            cboBoPhan.setSelectedIndex(0);

            cboNhom.setSelectedIndex(0);

            txtSbd.setText("");

            controller.loadTable(model);
        });

        btnCapPhat.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn thẻ"
                );

                return;
            }

            CapPhatDialog dialog =
                    new CapPhatDialog(
                            SwingUtilities.getWindowAncestor(this),
                            "Cấp phát thẻ"
                    );

            dialog.setVisible(true);

            if (dialog.isSaved()) {

                int sbd =
                        Integer.parseInt(
                                model.getValueAt(
                                        row,
                                        0
                                ).toString()
                        );

                controller.capPhat(
                        sbd,
                        dialog.getSoLuong(),
                        dialog.getTinhTrang(),
                        dialog.isDaKy(),
                        dialog.getGhiChu()
                );
            }
        });

        btnAdd.addActionListener(this::actionPerformed);

    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    private void actionPerformed(ActionEvent e) {

        TheDialog dialog =
                new TheDialog(
                        SwingUtilities.getWindowAncestor(this)
                );

        controller.loadBoPhan(
                dialog.getCboBoPhan()
        );

        controller.loadNhom(
                dialog.getCboNhom()
        );

        dialog.setVisible(true);

        if (dialog.isSaved()) {

            try {

                controller.insert(
                        dialog.getData()
                );

                controller.loadTable(model);

                JOptionPane.showMessageDialog(
                        this,
                        "Thêm thành công"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        }
    }
}