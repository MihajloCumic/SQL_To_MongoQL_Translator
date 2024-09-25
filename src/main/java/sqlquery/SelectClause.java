package sqlquery;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectClause extends Clause{

    private String[] aggregationKeys = {"avg", "sum", "count", "min", "max"};
    private ArrayList<String> aggregations;
    private ArrayList<String> aggregationParams;
    private boolean agrFlag = false;

    public SelectClause(String keyword) {
        super(keyword);
        this.aggregations = new ArrayList<>();
        this.aggregationParams = new ArrayList<>();
    }

    @Override
    public void addParam(String param) {
        if(Arrays.asList(this.aggregationKeys).contains(param) && !this.agrFlag){
            this.aggregations.add(param);
            this.agrFlag  = true;
        }else if(this.agrFlag){
            this.aggregationParams.add(param);
            this.agrFlag = false;
        }else {
            super.addParam(param);
        }
    }

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + " | Params:";
        for(String param: this.parameters){
            str += " " + param + ",";
        }
        str += " | Aggregations:";
        for(String aggregation: this.aggregations){
            str += " " + aggregation;
        }

        str += " | AgrParams:";
        for(String agrParam: this.aggregationParams){
            str += " " + agrParam;
        }
        return str;
    }

    public ArrayList<String> getAggregations() {
        return aggregations;
    }

    public void setAggregations(ArrayList<String> aggregations) {
        this.aggregations = aggregations;
    }

    public ArrayList<String> getAggregationParams() {
        return aggregationParams;
    }

    public void setAggregationParams(ArrayList<String> aggregationParams) {
        this.aggregationParams = aggregationParams;
    }
}
