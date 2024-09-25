package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.SelectClause;
import sqlquery.factory.ClauseFactory;

public class SelectFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new SelectClause(keyword);
    }
}
