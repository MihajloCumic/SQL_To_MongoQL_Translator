package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.HavingClause;
import sqlquery.factory.ClauseFactory;

public class HavingFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new HavingClause(keyword);
    }
}
