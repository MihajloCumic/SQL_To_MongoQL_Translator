package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.OrderByClause;
import sqlquery.factory.ClauseFactory;

public class OrderByFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new OrderByClause(keyword);
    }
}
