package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.FromClause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;

public class MandatoryParamsRule extends Rule{
    public MandatoryParamsRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        for(Clause clause: query.getQuery()){
            if(clause instanceof SelectClause || clause instanceof FromClause) continue;
            if(clause.getParameters().isEmpty()){
                this.errorMessage += " Clause: " + clause.getKeyword() + " is missing parameters";
                return false;
            }

        }
        return true;
    }
}
