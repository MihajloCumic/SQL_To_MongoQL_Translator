package adapter.implementation.param.implementation;

import adapter.implementation.param.specification.Param;
import sqlquery.Clause;
import sqlquery.FromClause;
import sqlquery.GroupByClause;
import sqlquery.SelectClause;

import java.util.ArrayList;

public class AggregationParam extends Param {

    private GroupByClause groupByClause;

    public AggregationParam(Clause clause,  GroupByClause groupByClause) {
        super(clause);
        this.groupByClause = groupByClause;
    }

    @Override
    public Param parseParam() {
        SelectClause clause = (SelectClause) this.clause;

        if(clause.getAggregations().isEmpty()) return this;

        String param = "{ $group: {";
        String _idParam = "_id: ";


        if(groupByClause == null){
            _idParam += "null";
        }else{
            _idParam += " \"$" + groupByClause.getParameters().get(groupByClause.getParameters().size() - 1) + "\"";
        }

        param += _idParam;

        for(int i = 0; i < clause.getAggregations().size(); i++){
            String aggregation = clause.getAggregations().get(i);
            String aggregationCol = clause.getAggregationParams().get(i);

            String agrName = ", \"" + aggregation + "_" + aggregationCol + "\": ";
            String agrFn = "{ $" + aggregation + ":  \"$" + aggregationCol + "\" }";

            param += agrName + agrFn;
        }

        param += "}}";

        this.param = param;
        return this;
    }
}
