package app;

import gui.Gui;
import view.SwingGui;

public class AppCore {
    public static void main(String[] args) {
        Gui gui = new SwingGui();
        gui.start();
    }

}
