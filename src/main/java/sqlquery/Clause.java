package sqlquery;

import java.util.ArrayList;

public abstract class Clause {
    protected String keyword;
    protected ArrayList<String> parameters;

    public Clause(String keyword){
        this.keyword = keyword;
        this.parameters = new ArrayList<>();
    }

    public void addParam(String param){
        this.parameters.add(param);
    };

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + "| Params:";
        for(String param: this.parameters){
            str += " " + param;
        }
        return str;

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }
}
