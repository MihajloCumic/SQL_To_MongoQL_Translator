package sqlquery.factory;

import sqlquery.Clause;

public abstract class ClauseFactory{

    public ClauseFactory(){}

    public abstract Clause createClause(String keyword);

}
