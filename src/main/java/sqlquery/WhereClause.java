package sqlquery;

import java.util.ArrayList;
import java.util.Arrays;

public class WhereClause extends Clause{

    private ArrayList<String> operators;
    private String[] operatorKeys = {"like", "or", "and", ">", "=", "<", ">=", "<="};

    private boolean inFlag = false;
    private int inPosition = 0;

    private ArrayList<String> inParams;
    private ArrayList<String> logicalOperators;
    private ArrayList<Integer> inParamsCounter;

    public WhereClause(String keyword) {
        super(keyword);
        this.operators = new ArrayList<>();
        this.inParams = new ArrayList<>();
        this.logicalOperators = new ArrayList<>();
        this.inParamsCounter = new ArrayList<>();

    }

    @Override
    public void addParam(String param) {
        if(Arrays.asList(this.operatorKeys).contains(param)){
            if(param.equalsIgnoreCase("and") || param.equalsIgnoreCase("or")){
                this.logicalOperators.add(param);
            }else{
                this.operators.add(param);
            }
            if(this.inFlag){
                inPosition++;
            }
            this.inFlag = false;
        }else if(param.equalsIgnoreCase("in")){
            this.inFlag = true;
            this.operators.add("in");
            this.inParamsCounter.add(0);
        }else{
            if(this.inFlag){
                this.inParams.add(param);
                this.inParamsCounter.set(inPosition, this.inParamsCounter.get(inPosition) + 1);

            }else{
                super.addParam(param);
            }
        }
    }

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + " | Params:";
        for(String param: this.parameters){
            str += ", " + param;
        }
        str += " | Operators:";
        for(String operator: this.operators){
            str += ", " + operator;
        }

        str += " | Logical operators:";
        for(String operator: this.logicalOperators){
            str += ", " + operator;
        }

        str += " | In params:";
        for(String inParam: this.inParams){
            str += ", " + inParam;
        }

        str += " | In params counter:";
        for(int inParam: this.inParamsCounter){
            str += ", " + inParam;
        }
        return str;
    }

    public ArrayList<String> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<String> operators) {
        this.operators = operators;
    }

    public ArrayList<String> getLogicalOperators() {
        return logicalOperators;
    }

    public void setLogicalOperators(ArrayList<String> logicalOperators) {
        this.logicalOperators = logicalOperators;
    }

    public ArrayList<String> getInParams() {
        return inParams;
    }

    public void setInParams(ArrayList<String> inParams) {
        this.inParams = inParams;
    }

    public ArrayList<Integer> getInParamsCounter() {
        return inParamsCounter;
    }

    public void setInParamsCounter(ArrayList<Integer> inParamsCounter) {
        this.inParamsCounter = inParamsCounter;
    }
}
