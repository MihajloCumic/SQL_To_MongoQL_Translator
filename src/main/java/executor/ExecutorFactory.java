package executor;

import com.mongodb.MongoClient;
import executor.implementation.AggregateExecutor;
import executor.implementation.FindExecutor;
import executor.specification.Executor;
import mongoql.AggregateMongoQuery;
import mongoql.MongoQuery;

public class ExecutorFactory {
    public static Executor createExecutor(MongoClient connection, MongoQuery mongoQuery){
        if(mongoQuery instanceof AggregateMongoQuery){
            return new AggregateExecutor(connection);
        }
        return new FindExecutor(connection);

    }
}
