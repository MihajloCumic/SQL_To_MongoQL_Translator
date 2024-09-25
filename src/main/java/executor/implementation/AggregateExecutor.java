package executor.implementation;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import executor.MongoDBController;
import executor.specification.Executor;
import mongoql.AggregateMongoQuery;
import mongoql.MongoQuery;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class AggregateExecutor  extends Executor {


    public AggregateExecutor(MongoClient connection) {
        super(connection);
    }

    @Override
    public MongoCursor<Document> executeQuery(MongoQuery mongoQuery) {
        AggregateMongoQuery aggregateMongoQuery = (AggregateMongoQuery) mongoQuery;

        MongoDatabase database = connection.getDatabase(MongoDBController.getDb());

        List<Document> documents = new ArrayList<>();

        String match = aggregateMongoQuery.getMatch();
        String lookup = aggregateMongoQuery.getLookup();
        String group = aggregateMongoQuery.getGroup();
        String project = aggregateMongoQuery.getProject();
        String sort = aggregateMongoQuery.getSort();

//        if(!match.equalsIgnoreCase("")) documents.add(Document.parse(match));

        if(!lookup.equalsIgnoreCase("")){
            String[] lookups = lookup.split("\\|");
            System.out.println(lookups.length);
            for(int i = 0; i < lookups.length; i++){
                documents.add(Document.parse(lookups[i]));
            }
        }
        if(!match.equalsIgnoreCase("")) documents.add(Document.parse(match));
        if(!group.equalsIgnoreCase("")) documents.add(Document.parse(group));
        if(!project.equalsIgnoreCase("")) documents.add(Document.parse(project));
        if(!sort.equalsIgnoreCase("")) documents.add(Document.parse(sort));

        MongoCursor<Document> cursor = database.getCollection(aggregateMongoQuery.getCollection())
                .aggregate(documents).iterator();



        return cursor;
    }


}
