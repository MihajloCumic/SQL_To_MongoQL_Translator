package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;

public class MandatoryRule extends Rule{


    public MandatoryRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        boolean selectFlag = false;
        boolean fromFlag = false;

        Clause selectClause = null;
        Clause fromClause = null;

        for(Clause clause: query.getQuery()){
            if(clause.getKeyword().equalsIgnoreCase("select")){
                selectFlag = true;
                selectClause = clause;
            }
            if(clause.getKeyword().equalsIgnoreCase("from")) {
                fromFlag = true;
                fromClause = clause;
            }
        }

        if(!(selectFlag && fromFlag)){
            this.errorMessage += " Mising SELECT or FROM keywords.";
            return false;
        }
        if(selectClause != null && fromClause != null){
            if(selectClause.getParameters().isEmpty() && ((SelectClause)selectClause).getAggregations().isEmpty() || fromClause.getParameters().isEmpty()){
                this.errorMessage += " Missing parameters in SELECT or FROM clause.";
                return false;
            }
        }

        return true;
    }
}
