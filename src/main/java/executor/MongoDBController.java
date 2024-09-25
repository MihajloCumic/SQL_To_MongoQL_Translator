package executor;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.Arrays;

public class MongoDBController {
    private static String user = "writer";
    private static String db = "bp_tim66";
    private static String password = "vW9QXYyiRDeeSeuf";

    public static MongoClient getConnection(){
        MongoCredential credential = MongoCredential.createCredential(user, db, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("134.209.239.154", 27017), Arrays.asList(credential));

        System.out.println("Connected to Mongo Database");

        return mongoClient;
    }

    public static String getDb() {
        return db;
    }
}
