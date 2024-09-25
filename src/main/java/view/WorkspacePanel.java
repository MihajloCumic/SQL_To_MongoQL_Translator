package view;

import javax.swing.*;
import java.awt.*;

public class WorkspacePanel extends JPanel {
    private JButton runBtn;
    private JTextArea input;
    private JTable table;
    private CardLayout cardLayout;
    private JLabel errorLabel;
    private JPanel tablePane;

    public WorkspacePanel(){
        this.setLayout(new BorderLayout());

        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500,400));
        table.setFillsViewportHeight(true);

        runBtn = new JButton("Run");
        runBtn.addActionListener(MainFrame.getInstance().getActionManager().getRunAction());


        input = new JTextArea();
        input.setMargin(new Insets(10,10,10,10));
        input.setFont(input.getFont().deriveFont((16f)));

        JScrollPane inputPane = new JScrollPane(input);
        //JScrollPane tablePane = new JScrollPane(table);
        JScrollPane tableSPane = new JScrollPane(table);
        tablePane = new JPanel();

        cardLayout = new CardLayout();
        errorLabel = new JLabel("Error:\n");
        errorLabel.setForeground(Color.RED);
        tablePane.setLayout(cardLayout);
        tablePane.add(tableSPane);
        tablePane.add(errorLabel);




        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, inputPane, tablePane);
        splitPane.setDividerLocation(400);
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        JPanel btnPane = new JPanel();
        btnPane.add(runBtn);

        this.add(btnPane, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
    }

    public JTextArea getInput() {
        return input;
    }

    public void setInput(JTextArea input) {
        this.input = input;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getRunBtn() {
        return runBtn;
    }

    public void setRunBtn(JButton runBtn) {
        this.runBtn = runBtn;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public JPanel getTablePane() {
        return tablePane;
    }

    public void setTablePane(JPanel tablePane) {
        this.tablePane = tablePane;
    }
}
