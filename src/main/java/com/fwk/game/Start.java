package com.fwk.game;

import com.fwk.game.ui.MainFrame;

public class Start {
    public static void main(String[] args) throws Exception {
        MainFrame mainFrame = new MainFrame();
        while (true) {
            Thread.sleep(25);
            mainFrame.repaint();
        }
    }
}
