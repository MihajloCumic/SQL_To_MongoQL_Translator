package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.FromClause;
import sqlquery.SQLQuery;

public class NumberOfJoinsRule extends Rule{
    public NumberOfJoinsRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        FromClause fromClause = null;

        for(Clause clause: query.getQuery()){
            if(clause instanceof FromClause){
                fromClause = (FromClause) clause;
                break;
            }
        }
        if(fromClause == null) return false;

        if(fromClause.getJoins().isEmpty()) return true;

        int joinCnt = 0;
        for(String str: fromClause.getJoins()){
            if(str.equalsIgnoreCase("join")){
                joinCnt++;
            }

        }

        return joinCnt > 2? false: true;
    }
}
