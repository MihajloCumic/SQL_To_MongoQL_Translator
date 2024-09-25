package validator.implementation.rules;

import sqlquery.SQLQuery;

public abstract class Rule {

    protected String ruleName;
    protected String errorMessage;

    public Rule(String ruleName, String errorMessage){
        this.ruleName = ruleName;
        this.errorMessage = errorMessage;
    }

    public abstract boolean check(SQLQuery query);

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

