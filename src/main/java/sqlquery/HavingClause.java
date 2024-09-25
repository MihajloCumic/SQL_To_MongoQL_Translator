package sqlquery;

import java.util.ArrayList;
import java.util.Arrays;

public class HavingClause extends Clause{
    private String[] operatorKeys = {"or", "and", ">", "=", "<"};
    private String[] aggregationKeys = {"avg", "sum", "count", "min", "max"};
    private ArrayList<String> operators;
    private ArrayList<String> aggregations;
    private ArrayList<Integer> indexOfAggregationFunction;

    public HavingClause(String keyword) {
        super(keyword);
        this.operators = new ArrayList<>();
        this.aggregations = new ArrayList<>();
        this.indexOfAggregationFunction = new ArrayList<>();
    }

    @Override
    public void addParam(String param) {
        if(Arrays.asList(this.operatorKeys).contains(param)){
            this.operators.add(param);
        }else if(Arrays.asList(this.aggregationKeys).contains(param)){
            this.aggregations.add(param);
            this.indexOfAggregationFunction.add(this.parameters.size());
        }else{
            super.addParam(param);
        }

    }

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + " | Params:";
        for(String param: this.parameters){
            str += " " + param;
        }
        str += " | Operators:";
        for(String operator: this.operators){
            str += " " + operator;
        }
        str += " | Aggregation:";
        for(int i = 0; i < this.aggregations.size(); i++){
            str += " " + this.aggregations.get(i) + ", pos_param: " + this.indexOfAggregationFunction.get(i);
        }

        return str;
    }
}
