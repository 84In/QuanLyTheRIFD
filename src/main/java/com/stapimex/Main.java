package com.stapimex;

import com.formdev.flatlaf.FlatLightLaf;
import com.stapimex.view.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {

            MainFrame frame = new MainFrame();
            frame.setVisible(true);

        });
    }
}