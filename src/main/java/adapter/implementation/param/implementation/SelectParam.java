package adapter.implementation.param.implementation;

import adapter.implementation.param.specification.Param;
import sqlquery.Clause;
import sqlquery.SelectClause;

public class SelectParam extends Param {
    public SelectParam(Clause clause) {
        super(clause);
    }

    @Override
    public Param parseParam() {
        SelectClause clause = (SelectClause) this.clause;

        if(clause.getParameters().contains("*")) return this;

        String param = "{";
        for(String str: clause.getParameters()){
            param += "\"" + str + "\":1,";
        }

        for(int i = 0; i < clause.getAggregations().size(); i++){
            String agr = clause.getAggregations().get(i);
            String agrPrm = clause.getAggregationParams().get(i);
            param += "\"" + agr + "_" + agrPrm + "\":1,";
        }

//        param += "\"_id\":0}";
        param += "}";

        this.param = param;
        return this;
    }
}
