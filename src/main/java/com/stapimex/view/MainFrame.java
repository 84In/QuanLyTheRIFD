package com.stapimex.view;

import com.stapimex.view.dashboard.DashboardPanel;
import com.stapimex.view.the.ThePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Quản lý thẻ RFID");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //------------------------------------
        // SIDEBAR
        //------------------------------------

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setPreferredSize(
                new Dimension(
                        220,
                        0
                )
        );

        JLabel lblTitle = new JLabel("RFID Manager");

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        lblTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        //------------------------------------
        // MENU BUTTONS
        //------------------------------------

        JButton btnDashboard =
                createMenuButton("Dashboard");

        JButton btnThe =
                createMenuButton("Quản lý thẻ");

        //------------------------------------
        // ADD COMPONENTS
        //------------------------------------

        menuPanel.add(lblTitle);

        menuPanel.add(
                Box.createVerticalStrut(20)
        );

        menuPanel.add(btnDashboard);

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(btnThe);

        menuPanel.add(
                Box.createVerticalGlue()
        );

        //------------------------------------
        // SIDEBAR BORDER
        //------------------------------------

        menuPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                0,
                                1,
                                Color.LIGHT_GRAY
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                15,
                                20,
                                15
                        )
                )
        );

        add(menuPanel, BorderLayout.WEST);

        //------------------------------------
        // CONTENT
        //------------------------------------

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        contentPanel.add(
                new DashboardPanel(),
                "dashboard"
        );

        contentPanel.add(
                new ThePanel(),
                "the"
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        //------------------------------------
        // DEFAULT
        //------------------------------------

        cardLayout.show(
                contentPanel,
                "dashboard"
        );

        //------------------------------------
        // EVENTS
        //------------------------------------

        btnDashboard.addActionListener(
                e -> cardLayout.show(
                        contentPanel,
                        "dashboard"
                )
        );

        btnThe.addActionListener(
                e -> cardLayout.show(
                        contentPanel,
                        "the"
                )
        );
    }

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        10
                )
        );

        return button;
    }
}