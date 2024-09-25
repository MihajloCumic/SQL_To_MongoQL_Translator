package controller;

import com.mongodb.MongoClient;
import executor.MongoDBController;
import executor.specification.Executor;
import framework.AppFramework;
import mongoql.MongoQuery;
import packager.specification.Packager;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;
import validator.implementation.SQLValidator;
import validator.specification.Validator;
import view.MainFrame;
import view.WorkspacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunAction extends AbstractQueryAction{

    public RunAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/info.svg"));
        putValue(NAME, "Run");
        putValue(SHORT_DESCRIPTION, "Run");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppFramework appFramework = new AppFramework();

        WorkspacePanel workspace = MainFrame.getInstance().getWorkspacePanel();
        workspace.getCardLayout().first(workspace.getTablePane());

        String input = MainFrame.getInstance().getWorkspacePanel().getInput().getSelectedText();
        if(input == null){
            workspace.getCardLayout().next(workspace.getTablePane());
            workspace.getErrorLabel().setText("Could not run because no text was selected.");
            return;
        }

        Parser parser = appFramework.getParser();
        SQLQuery query = (SQLQuery) parser.parse(input);

        Validator validator = appFramework.getValidator();
        boolean inputIsValid = validator.validate(query);
        if(!inputIsValid){
            workspace.getCardLayout().next(workspace.getTablePane());
            workspace.getErrorLabel().setText(validator.getError());
            return;
        }

        MongoQuery mongoQuery = appFramework.getAdapter().getMongoQL(query);


        MongoClient connection = MongoDBController.getConnection();

        Executor executor = appFramework.getExecutor(connection, mongoQuery);
        Packager packager = appFramework.getPackager();

        List<Map<String, String>>rows = packager.packageData(executor.executeQuery(mongoQuery));
        if(rows.isEmpty()){
            workspace.getCardLayout().next(workspace.getTablePane());
            workspace.getErrorLabel().setText("Empty response.");
            return;
        }

        MainFrame.getInstance().getTableModel().setRows(rows);

        connection.close();



    }
}
