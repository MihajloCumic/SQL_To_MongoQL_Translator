package validator.implementation.rules;

import sqlquery.Clause;
import sqlquery.OrderByClause;
import sqlquery.SQLQuery;

public class OrderByRule extends Rule{


    public OrderByRule(String ruleName, String errorMessage) {
        super(ruleName, errorMessage);
    }

    @Override
    public boolean check(SQLQuery query) {
        OrderByClause orderByClause = null;
        for(Clause clause: query.getQuery()){
            if(clause instanceof OrderByClause){
                orderByClause = (OrderByClause) clause;
                break;
            }
        }

        if(orderByClause == null) return true;
        if(orderByClause.getParameters().size() == orderByClause.getOrderList().size()) return true;

        return false;
    }
}
