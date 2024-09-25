package adapter.implementation.param.implementation;

import adapter.implementation.param.specification.Param;
import sqlquery.Clause;
import sqlquery.WhereClause;

import java.util.ArrayList;

public class WhereParam extends Param {
    public WhereParam(Clause clause) {
        super(clause);
    }

    @Override
    public Param parseParam() {
        WhereClause clause = (WhereClause) this.clause;
        ArrayList<String> whereParams = clause.getParameters();

        ArrayList<String> params = new ArrayList<>();

        int curr = 0;
        int inParamCnt = 0;
        for(String op: clause.getOperators()){

                if(op.equalsIgnoreCase("=") || op.equalsIgnoreCase("like")){
                    String str = "{\"" + whereParams.get(curr) + "\": " + whereParams.get(curr + 1) + "}";
                    params.add(str);
                    curr += 2;
                    continue;
                }
                if(!op.equalsIgnoreCase("in")){
                    params.add(this.parseOperators(op, whereParams.get(curr), whereParams.get(curr + 1)));
                    curr += 2;
                    continue;
                }
                params.add(inParams(whereParams.get(curr), clause, inParamCnt));
                inParamCnt++;
                curr++;
        }

        ArrayList<String> logicalParams = new ArrayList<>();
        int reverseParams = params.size() - 1;
        String param = "";
        for(int i = clause.getLogicalOperators().size() - 1; i >=0; i--){
            param += "{$" + clause.getLogicalOperators().get(i) + ": [" + params.get(reverseParams) + ", ";
            reverseParams--;
        }

        param += params.get(reverseParams);

        for(int i = 0; i < clause.getLogicalOperators().size(); i++){
            param += "]}";
        }

        this.param = param;
        return this;
    }

    private String inParams(String par, WhereClause clause, int inParamCnt){
        String str = "{\"" + par + "\":{ $in: [";
        for(int i = 0; i < clause.getInParamsCounter().get(inParamCnt); i++){
            if(i != 0) str += ", ";
            str += clause.getInParams().get(i);
        }
        str += "]}}";
        return str;
    }

    private String parseOperators(String op, String par1, String par2){

        String str = "{\"" + par1 + "\": {";

        if(op.equalsIgnoreCase(">")){
            str += "$gt:" + par2 + "}}";
            return str;
        }
        if(op.equalsIgnoreCase(">=")){
            str += "$gte:" + par2 + "}}";
            return str;
        }
        if(op.equalsIgnoreCase("<")){
            str += "$lt:" + par2 + "}}";
            return str;
        }
        if(op.equalsIgnoreCase("<=")){
            str += "$lte:" + par2 + "}}";
            return str;
        }

        return null;
    }
}
