package view;

import gui.Gui;
import view.model.TableModel;

public class SwingGui implements Gui {

    private MainFrame mainFrame;


    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
        mainFrame.setTableModel(new TableModel());
    }


}
