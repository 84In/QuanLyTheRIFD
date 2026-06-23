package com.stapimex.view.dashboard;

import com.stapimex.controller.DashboardController;
import com.stapimex.dao.DashboardDAO;
import com.stapimex.dao.impl.DashboardDAOImpl;
import com.stapimex.model.view.ThongKeNgayView;
import com.stapimex.util.DashboardExcelExporter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DashboardPanel extends JPanel {

    private JLabel lblCapPhat;
    private JLabel lblThuHoi;
    private JLabel lblChenhLech;

    private JPanel pnlChart;

    private JComboBox<Integer> cboMonth;
    private JComboBox<Integer> cboYear;

    private JButton btnView;
    private JButton btnExport;

    private DashboardController controller;

    public DashboardPanel() {

        controller = new DashboardController();

        initComponents();

        initEvents();
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(
                new BoxLayout(
                        northPanel,
                        BoxLayout.Y_AXIS
                )
        );

        northPanel.add(createFilterPanel());

        JPanel top = new JPanel(
                new GridLayout(1, 3, 10, 10)
        );

        lblCapPhat = new JLabel();
        lblThuHoi = new JLabel();
        lblChenhLech = new JLabel();

        top.add(
                createCard(
                        "Cấp phát",
                        lblCapPhat
                )
        );

        top.add(
                createCard(
                        "Thu hồi",
                        lblThuHoi
                )
        );

        top.add(
                createCard(
                        "Chênh lệch",
                        lblChenhLech
                )
        );

        northPanel.add(top);

        add(
                northPanel,
                BorderLayout.NORTH
        );

        pnlChart = new JPanel(
                new BorderLayout()
        );

        add(
                pnlChart,
                BorderLayout.CENTER
        );

        LocalDate now = LocalDate.now();

        loadData(
                now.getMonthValue(),
                now.getYear()
        );
    }

    private JPanel createCard(
            String title,
            JLabel value
    ) {

        JPanel panel = new JPanel(
                new BorderLayout()
        );

        panel.setBorder(
                BorderFactory.createTitledBorder(title)
        );

        value.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        value.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        panel.add(value);

        return panel;
    }

    private void loadData(
            int month,
            int year
    ) {

        int capPhat =
                controller.getTongCapPhat(
                        month,
                        year
                );

        int thuHoi =
                controller.getTongThuHoi(
                        month,
                        year
                );

        lblCapPhat.setText(
                String.format("%,d", capPhat)
        );

        lblThuHoi.setText(
                String.format("%,d", thuHoi)
        );

        lblChenhLech.setText(
                String.format(
                        "%,d",
                        capPhat - thuHoi
                )
        );

        loadChart(
                month,
                year
        );
    }

    private void loadChart(
            int month,
            int year
    ) {

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        List<ThongKeNgayView> list =
                controller.getThongKeTheoNgay(
                        month,
                        year
                );

        for (ThongKeNgayView dto : list) {

            dataset.addValue(
                    dto.getCapPhat(),
                    "Cấp phát",
                    String.valueOf(dto.getNgay())
            );

            dataset.addValue(
                    dto.getThuHoi(),
                    "Thu hồi",
                    String.valueOf(dto.getNgay())
            );
        }

        JFreeChart chart =
                ChartFactory.createLineChart(
                        "Biểu đồ tháng " + month,
                        "Ngày",
                        "Số lượng",
                        dataset
                );

        pnlChart.removeAll();

        pnlChart.add(
                new ChartPanel(chart),
                BorderLayout.CENTER
        );

        pnlChart.revalidate();
        pnlChart.repaint();
    }
    private JPanel createFilterPanel() {

        JPanel panel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT
                )
        );

        cboMonth = new JComboBox<>();

        for (int i = 1; i <= 12; i++) {
            cboMonth.addItem(i);
        }

        cboYear = new JComboBox<>();

        for (int i = 2024; i <= 2035; i++) {
            cboYear.addItem(i);
        }

        LocalDate now = LocalDate.now();

        cboMonth.setSelectedItem(
                now.getMonthValue()
        );

        cboYear.setSelectedItem(
                now.getYear()
        );

        btnView = new JButton("Xem");

        btnExport = new JButton("Xuất Excel");

        panel.add(new JLabel("Tháng"));

        panel.add(cboMonth);

        panel.add(Box.createHorizontalStrut(10));

        panel.add(new JLabel("Năm"));

        panel.add(cboYear);

        panel.add(Box.createHorizontalStrut(15));

        panel.add(btnView);

        panel.add(btnExport);

        return panel;
    }
    private void initEvents(){
        btnView.addActionListener(e -> {

            int month =
                    (Integer) cboMonth.getSelectedItem();

            int year =
                    (Integer) cboYear.getSelectedItem();

            loadData(
                    month,
                    year
            );
        });
        btnExport.addActionListener(e -> {
//            JOptionPane.showMessageDialog(
//                    this,
//                    "Chức năng đang phát triển"
//            );
            int month =
                    (Integer) cboMonth.getSelectedItem();

            int year =
                    (Integer) cboYear.getSelectedItem();

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setSelectedFile(
                    new File(
                            String.format(
                                    "BaoCao_RFID_T%02d_%d.xlsx",
                                    month,
                                    year
                            )
                    )
            );

            if (
                    chooser.showSaveDialog(this)
                            == JFileChooser.APPROVE_OPTION
            ) {

                File file =
                        chooser.getSelectedFile();

                try {

                    DashboardExcelExporter.export(
                            month,
                            year,
                            file
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Xuất báo cáo thành công"
                    );

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            this,
                            ex.getMessage()
                    );
                }
            }
        });
    }

}