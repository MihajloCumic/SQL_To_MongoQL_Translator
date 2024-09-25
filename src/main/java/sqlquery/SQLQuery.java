package sqlquery;

import java.util.ArrayList;

public class SQLQuery {
    private ArrayList<Clause> query;

    public SQLQuery(){
        this.query = new ArrayList<>();
    }

    public void addClause(Clause clause){
        this.query.add(clause);
    }

    @Override
    public String toString() {
        String str = "";
        for(Clause clause: this.query){
            str += clause.toString() + "\n";
        }
        return str;
    }

    public ArrayList<Clause> getQuery() {
        return query;
    }

    public void setQuery(ArrayList<Clause> query) {
        this.query = query;
    }
}
