package adapter.implementation.mapper;

import adapter.implementation.mapper.implementation.AggregateMapper;
import adapter.implementation.mapper.implementation.FindMapper;
import adapter.implementation.mapper.specification.Mapper;
import sqlquery.*;

import java.util.List;

public class MapperBuilder {
    private SQLQuery query;

    public MapperBuilder(SQLQuery query){
        this.query = query;
    }

    public Mapper getMapper(){

        for(Clause clause: this.query.getQuery()){
            if(clause instanceof SelectClause){
                if(!((SelectClause) clause).getAggregations().isEmpty()){
                    return new AggregateMapper();
                }
                continue;
            }
            if(clause instanceof FromClause){
                if(!((FromClause) clause).getJoins().isEmpty()){
                    return new AggregateMapper();
                }
                continue;
            }
            if(clause instanceof GroupByClause){
                return new AggregateMapper();
            }

        }
        return new FindMapper();
    }
}
