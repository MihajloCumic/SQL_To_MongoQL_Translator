package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.SQLQuery;
import sqlquery.WhereClause;

import java.util.Arrays;

public class WhereAggregationRule extends Rule{
    public WhereAggregationRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        WhereClause whereClause = null;
        for(Clause clause: query.getQuery()){
            if(clause instanceof WhereClause){
                whereClause = (WhereClause) clause;
                break;
            }
        }
        if(whereClause == null) return true;

        String[] aggregations = {"avg", "sum", "min", "max", "count"};

        for(String param: whereClause.getParameters()){
            if(Arrays.asList(aggregations).contains(param)){
                errorMessage += " -> " + param +  "(~).";
                return false;
            }
        }

        return true;

    }
}
