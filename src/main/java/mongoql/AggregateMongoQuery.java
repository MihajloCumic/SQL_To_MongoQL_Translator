package mongoql;

public class AggregateMongoQuery extends MongoQuery{
    private String match = "";
    private String project = "";
    private String lookup = "";
    private String group = "";
    public AggregateMongoQuery(String sort, String match, String project, String lookup, String group) {

        super(sort);
        this.match = match;
        this.project = project;
        this.lookup = lookup;
        this.group = group;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "AggregateMongoQuery{" +
                "match='" + match + '\'' +
                ", project='" + project + '\'' +
                ", lookup='" + lookup + '\'' +
                ", group='" + group + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
