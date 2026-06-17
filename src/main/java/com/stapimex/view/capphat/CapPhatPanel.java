package com.stapimex.view.capphat;

import com.stapimex.controller.ThuHoiCapPhatController;
import com.stapimex.model.ComboItem;
import com.stapimex.model.ThuHoiCapPhat;
import com.stapimex.view.thuhoi.ThuHoiDialog;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class CapPhatPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JDateChooser dcFromDate;
    private JDateChooser dcToDate;

    private JComboBox<ComboItem> cboBoPhan;
    private JComboBox<ComboItem> cboNhom;

    private JTextField txtSbd;

    private JButton btnSearch;
    private JButton btnRefresh;
    private JButton btnThuHoi;

    private ThuHoiCapPhatController controller;

    public CapPhatPanel() {
        controller = new ThuHoiCapPhatController();

        initComponents();

        initEvents();

        loadComboData();

        reloadData();
    }

    public void reloadData() {
        controller.loadTable(model);
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        //--------------------------------
        // TITLE
        //--------------------------------

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        JLabel lblTitle =
                new JLabel(
                        "LỊCH SỬ CẤP PHÁT"
                );

        lblTitle.setFont(
                lblTitle.getFont()
                        .deriveFont(
                                Font.BOLD,
                                22f
                        )
        );

        topPanel.add(
                lblTitle,
                BorderLayout.WEST
        );

        add(
                topPanel,
                BorderLayout.NORTH
        );

        //--------------------------------
        // CENTER
        //--------------------------------

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        //--------------------------------
        // SEARCH PANEL
        //--------------------------------

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        dcFromDate =
                new JDateChooser();

        dcToDate =
                new JDateChooser();

        dcFromDate.setDateFormatString(
                "dd/MM/yyyy"
        );

        dcToDate.setDateFormatString(
                "dd/MM/yyyy"
        );

        dcToDate.setDate(
                new Date()
        );

        cboBoPhan =
                new JComboBox<>();

        cboNhom =
                new JComboBox<>();

        cboBoPhan.setPreferredSize(
                new Dimension(
                        180,
                        25
                )
        );

        cboNhom.setPreferredSize(
                new Dimension(
                        180,
                        25
                )
        );

        txtSbd =
                new JTextField(
                        10
                );

        btnSearch =
                new JButton(
                        "Tìm kiếm"
                );

        btnRefresh =
                new JButton(
                        "Làm mới"
                );

        searchPanel.add(
                new JLabel(
                        "Từ ngày"
                )
        );

        searchPanel.add(
                dcFromDate
        );

        searchPanel.add(
                new JLabel(
                        "Đến ngày"
                )
        );

        searchPanel.add(
                dcToDate
        );

        searchPanel.add(
                new JLabel(
                        "Bộ phận"
                )
        );

        searchPanel.add(
                cboBoPhan
        );

        searchPanel.add(
                new JLabel(
                        "Nhóm"
                )
        );

        searchPanel.add(
                cboNhom
        );

        searchPanel.add(
                new JLabel(
                        "SBD"
                )
        );

        searchPanel.add(
                txtSbd
        );

        searchPanel.add(
                btnSearch
        );

        searchPanel.add(
                btnRefresh
        );

        centerPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        //--------------------------------
        // TABLE
        //--------------------------------

        model =
                new DefaultTableModel(
                        new Object[]{
                                "Ngày",
                                "SBD",

                                "Mã nhóm",
                                "Nhóm",

                                "Mã bộ phận",
                                "Bộ phận",

                                "Số lượng",
                                "Tình trạng",
                                "Đã ký",
                                "Ghi chú"
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

        table =
                new JTable(model);
        table.removeColumn(
                table.getColumnModel().getColumn(2)
        );

        table.removeColumn(
                table.getColumnModel().getColumn(3)
        );

        table.setRowHeight(35);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader()
                .setReorderingAllowed(false);

        //--------------------------------
        // CENTER ALIGN
        //--------------------------------

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (
                int i = 0;
                i < table.getColumnCount();
                i++
        ) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }

        //--------------------------------
        // GHI CHÚ RỘNG HƠN
        //--------------------------------

        table.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(350);

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        //--------------------------------
        // BOTTOM
        //--------------------------------

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        btnThuHoi = new JButton(
                "Thu hồi"
        );
        bottomPanel.add(btnThuHoi);
        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
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

                maBoPhan =
                        boPhan.getId();
            }

            ComboItem nhom =
                    (ComboItem)
                            cboNhom.getSelectedItem();

            if (
                    nhom != null
                            && nhom.getId() > 0
            ) {

                maNhom =
                        nhom.getId();
            }

            try {

                String txt =
                        txtSbd.getText().trim();

                if (!txt.isEmpty()) {

                    sbd =
                            Integer.parseInt(txt);
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "SBD phải là số"
                );

                return;
            }

            controller.search(
                    model,
                    dcFromDate.getDate(),
                    dcToDate.getDate(),
                    maBoPhan,
                    maNhom,
                    sbd
            );
        });
        btnRefresh.addActionListener(e -> {

            dcFromDate.setDate(null);

            dcToDate.setDate(
                    new Date()
            );

            cboBoPhan.setSelectedIndex(0);

            cboNhom.setSelectedIndex(0);

            txtSbd.setText("");

            controller.loadTable(model);
        });
        btnThuHoi.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn thẻ"
                );

                return;
            }

            ThuHoiDialog dialog =
                    new ThuHoiDialog(
                            SwingUtilities.getWindowAncestor(this),
                            "Thu hồi thẻ"
                    );

            dialog.setVisible(true);

            if (dialog.isSaved()) {

                try {

                    int sbd =
                            Integer.parseInt(
                                    model.getValueAt(
                                            row,
                                            1
                                    ).toString()
                            );

                    int maNhom =
                            Integer.parseInt(
                                    model.getValueAt(
                                            row,
                                            2
                                    ).toString()
                            );

                    int maBoPhan =
                            Integer.parseInt(
                                    model.getValueAt(
                                            row,
                                            4
                                    ).toString()
                            );
                    ThuHoiCapPhat thuHoiCapPhat = new ThuHoiCapPhat(
                            dialog.getNgayThuHoi(),
                            maBoPhan,
                            maNhom,
                            sbd,
                            dialog.getSoLuong(),
                            dialog.getTinhTrangText(),
                            dialog.getDaKy(),
                            0,
                            dialog.getGhiChu());
                    controller.insert(
                            thuHoiCapPhat
                    );

                    controller.loadTable(model);

                    JOptionPane.showMessageDialog(
                            this,
                            "Thu hồi thành công"
                    );

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            this,
                            ex.getMessage(),
                            "Lỗi" + ex.getMessage(),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }


    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JComboBox<ComboItem> getCboBoPhan() {
        return cboBoPhan;
    }

    public JComboBox<ComboItem> getCboNhom() {
        return cboNhom;
    }

    public JTextField getTxtSbd() {
        return txtSbd;
    }

    public JDateChooser getDcFromDate() {
        return dcFromDate;
    }

    public JDateChooser getDcToDate() {
        return dcToDate;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnThuHoi() {
        return btnThuHoi;
    }


}