package packager.implementation;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import packager.specification.Packager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoPackager implements Packager {
    @Override
    public List<Map<String, String>> packageData(MongoCursor<Document> documents) {
        List<Map<String, String>> rows = new ArrayList<>();
        while(documents.hasNext()){
            Document d = documents.next();
            String str = d.toJson();
            str = str.replaceAll(": \\{\"", ".").replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
            String[] pairs = str.split(",");
            Map<String, String> row = new HashMap<>();
            for(int i = 0; i < pairs.length; i++){
                String pair = pairs[i];
                String[] splitPair = pair.split(":");
                String name = splitPair[0].trim();
                if(name.contains(".")){
                    name = name.split("\\.")[1];
                }
                String field = splitPair[1].trim();
                row.put(name, field);
            }
            rows.add(row);
        }
        return rows;
    }
}
