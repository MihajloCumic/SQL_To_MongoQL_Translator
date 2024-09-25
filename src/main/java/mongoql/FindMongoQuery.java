package mongoql;

public class FindMongoQuery extends MongoQuery{
    private String find = "{}";
    private String projection = "{}";

    public FindMongoQuery(String sort, String find, String projection) {

        super(sort);
        this.find = find;
        this.projection = projection;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    @Override
    public String toString() {
        return "FindMongoQuery{" +
                "find='" + find + '\'' +
                ", projection='" + projection + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
