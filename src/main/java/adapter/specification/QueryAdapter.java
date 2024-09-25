package adapter.specification;

import mongoql.MongoQuery;
import sqlquery.SQLQuery;

public interface QueryAdapter {
    MongoQuery getMongoQL(SQLQuery query);
}
