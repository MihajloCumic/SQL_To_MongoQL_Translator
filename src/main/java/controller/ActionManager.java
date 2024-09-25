package controller;

public class ActionManager {
    private RunAction runAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        this.runAction = new RunAction();
    }

    public RunAction getRunAction() {
        return runAction;
    }

    public void setRunAction(RunAction runAction) {
        this.runAction = runAction;
    }
}
