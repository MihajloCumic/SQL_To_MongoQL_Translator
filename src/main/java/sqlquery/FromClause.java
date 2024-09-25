package sqlquery;

import java.util.ArrayList;
import java.util.Arrays;

public class FromClause extends Clause{

    private String[] joinKeys = {"join", "using", "on"};
    private String[] operatorKeys = {"like", ">", "=", "<"};
    private ArrayList<String> joins;
    private ArrayList<String> operators;
    private ArrayList<String> joinFields;
    private boolean joinFlag = false;

    public FromClause(String keyword) {
        super(keyword);
        this.joins = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.joinFields = new ArrayList<>();
    }

    @Override
    public void addParam(String param) {
        if(Arrays.asList(this.joinKeys).contains(param)){
            if(!param.equalsIgnoreCase("join"))
            {
                this.joinFlag = true;
            }
            this.joins.add(param);

        }else if(Arrays.asList(this.operatorKeys).contains(param)){
            this.operators.add(param);
        }else{

            if(this.joinFlag){
                this.joinFields.add(param);
                this.joinFlag = false;
            }else{
                super.addParam(param);
            }
        }
    }

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + " | Params:";
        for(String param: this.parameters){
            str += " " + param;
        }

        str += " | Joining fields:";
        for(String join: this.joinFields){
            str += " " + join;
        }

        str += " | Joins:";
        for(String join: this.joins){
            str += " " + join;
        }

        str += " | Operators:";
        for(String operator: this.operators){
            str += " " + operator;
        }
        return str;
    }

    public ArrayList<String> getJoins() {
        return joins;
    }

    public void setJoins(ArrayList<String> joins) {
        this.joins = joins;
    }

    public ArrayList<String> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<String> operators) {
        this.operators = operators;
    }

    public ArrayList<String> getJoinFields() {
        return joinFields;
    }

    public void setJoinFields(ArrayList<String> joinFields) {
        this.joinFields = joinFields;
    }
}
