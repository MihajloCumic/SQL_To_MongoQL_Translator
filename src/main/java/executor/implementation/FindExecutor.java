package executor.implementation;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import executor.MongoDBController;
import executor.specification.Executor;
import mongoql.FindMongoQuery;
import mongoql.MongoQuery;
import org.bson.Document;

public class FindExecutor extends Executor {
    public FindExecutor(MongoClient connection) {
        super(connection);
    }

    @Override
    public MongoCursor<Document> executeQuery(MongoQuery mongoQuery) {
        FindMongoQuery findMongoQuery = (FindMongoQuery) mongoQuery;

        MongoDatabase database = connection.getDatabase(MongoDBController.getDb());

        MongoCursor<Document> cursor = database.getCollection(findMongoQuery.getCollection())
                .find(Document.parse(findMongoQuery.getFind()))
                .projection(Document.parse(findMongoQuery.getProjection()))
                .sort(Document.parse(findMongoQuery.getSort()))
                .iterator();


        return cursor;
    }
}
