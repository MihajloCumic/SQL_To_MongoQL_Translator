package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.GroupByClause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;

public class GroupByRule extends Rule{
    public GroupByRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {

        SelectClause selectClause = null;
        for(Clause clause: query.getQuery()){
            if(clause instanceof SelectClause){
                selectClause = (SelectClause) clause;
                break;
            }
        }

        if(selectClause == null) return false;

        if(selectClause.getAggregations().isEmpty()) return true;

        GroupByClause groupByClause = null;

        for(Clause clause: query.getQuery()){
            if(clause instanceof GroupByClause){
                groupByClause = (GroupByClause) clause;
                break;
            }
        }

        if(groupByClause == null && selectClause.getParameters().isEmpty()) return true;
        if(groupByClause == null && !selectClause.getParameters().isEmpty()) return false;

        boolean isItInGroupBy = true;
        for(String selectParam: selectClause.getParameters()){
            if(!groupByClause.getParameters().contains(selectParam)){
                isItInGroupBy = false;
                break;
            }
        }

        if(!isItInGroupBy) return false;

        return true;
    }
}
