package mongoql;

public abstract class MongoQuery {

    protected String sort = "{}";
    private String collection;

    public MongoQuery(String sort){
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
