package adapter.implementation.param;

import adapter.implementation.param.implementation.*;
import adapter.implementation.param.specification.Param;
import sqlquery.*;

import java.util.ArrayList;
import java.util.List;

public class ParamBuilder {
    private SQLQuery query;

    public ParamBuilder(SQLQuery query){
        this.query = query;
    }

    public List<Param> buildParams(){
        List<Param> params = new ArrayList<>();

        SelectClause selectClause = null;
        GroupByClause groupByClause = null;
        for(Clause clause: query.getQuery()){
            if(clause instanceof SelectClause){
                params.add(new SelectParam(clause));
                selectClause = (SelectClause) clause;
                continue;
            }
            if(clause instanceof WhereClause){
                params.add(new WhereParam(clause));
                continue;
            }
            if(clause instanceof FromClause){
                params.add(new FromParam(clause));
                continue;
            }
            if(clause instanceof OrderByClause){
                params.add(new OrderByParam(clause));
                continue;
            }
            if(clause instanceof GroupByClause){
                groupByClause = (GroupByClause) clause;
            }
        }

        params.add(new AggregationParam(selectClause, groupByClause));


        for(Param param: params){
            param.parseParam();
        }

        return params;
    }
}
