package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.FromClause;
import sqlquery.factory.ClauseFactory;

public class FromFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new FromClause(keyword);
    }
}
