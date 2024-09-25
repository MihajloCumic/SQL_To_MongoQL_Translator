package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.WhereClause;
import sqlquery.factory.ClauseFactory;

public class WhereFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new WhereClause(keyword);
    }
}
