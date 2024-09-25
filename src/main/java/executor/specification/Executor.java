package executor.specification;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import mongoql.MongoQuery;
import org.bson.Document;

public abstract class Executor {
    protected MongoClient connection;

    public Executor(MongoClient connection){
        this.connection = connection;
    }

    abstract public MongoCursor<Document> executeQuery(MongoQuery mongoQuery);

    public MongoClient getConnection() {
        return connection;
    }
}
