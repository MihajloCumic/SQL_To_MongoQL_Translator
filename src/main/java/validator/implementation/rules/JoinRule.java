package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.FromClause;
import sqlquery.SQLQuery;

public class JoinRule extends Rule{
    public JoinRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        FromClause fromClause = null;

        for(Clause  clause: query.getQuery()){
            if(clause instanceof FromClause){
                fromClause = (FromClause) clause;
                break;
            }
        }
        if(fromClause == null) return false;

        if(fromClause.getJoins().isEmpty()) return true;

        boolean joinCondition = true;

        for(String str: fromClause.getJoins()){
            if(str.equalsIgnoreCase("join")){
                joinCondition = !joinCondition;
                continue;
            }
            if(str.equalsIgnoreCase("using") || str.equalsIgnoreCase("on")){
                joinCondition = !joinCondition;
                continue;
            }
        }

        if(fromClause.getJoinFields().size() > fromClause.getParameters().size() - 1) {
            this.errorMessage += " Only one field is needed per join.";
            return false;
        }

        return joinCondition;
    }
}
