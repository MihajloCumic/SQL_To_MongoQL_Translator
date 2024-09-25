package packager.specification;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface Packager {
    List<Map<String, String>> packageData(MongoCursor<Document> documents);
}
