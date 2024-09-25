package view;

import controller.ActionManager;
import view.model.TableModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame  extends JFrame {

    private static MainFrame instance = null;

    private ActionManager actionManager;

    private WorkspacePanel workspacePanel;

    private TableModel tableModel;

    private MainFrame(){}

    private void initialise(){
        actionManager = new ActionManager();
        initialiseGUI();
    }

    private void initialiseGUI(){
        //JFrame podesavanja
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/3 * 2, screenSize.height/3 * 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SQL to MongoQL");

        //workspace
        workspacePanel = new WorkspacePanel();
        this.add(workspacePanel);



    }

    public static MainFrame getInstance(){
        if (instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public WorkspacePanel getWorkspacePanel() {
        return workspacePanel;
    }

    public void setWorkspacePanel(WorkspacePanel workspacePanel) {
        this.workspacePanel = workspacePanel;
    }

    public void setTableModel(TableModel tableModel){
        this.tableModel = tableModel;
        this.workspacePanel.getTable().setModel(tableModel);
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
