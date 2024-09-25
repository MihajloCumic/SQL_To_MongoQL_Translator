package sqlquery.factory.implementation;

import sqlquery.Clause;
import sqlquery.GroupByClause;
import sqlquery.factory.ClauseFactory;

public class GroupByFactory extends ClauseFactory {
    @Override
    public Clause createClause(String keyword) {
        return new GroupByClause(keyword);
    }
}
