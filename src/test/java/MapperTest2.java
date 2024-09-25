import adapter.implementation.mapper.implementation.AggregateMapper;
import adapter.implementation.mapper.implementation.FindMapper;
import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.implementation.*;
import adapter.implementation.param.specification.Param;
import mongoql.MongoQuery;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.*;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

import java.util.ArrayList;

public class MapperTest2 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select first_name, last_name from employees where first_name like \"King\" and salary > 10000 order by salary desc";

        SQLQuery query = (SQLQuery) parser.parse(str);
        System.out.println(query.toString());
        boolean validate = validator.validate(query);
        System.out.println(validate);
        if(!validate) return;

        ArrayList<Param> params = new ArrayList<>();
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
        if(groupByClause != null && selectClause != null){
            params.add(new AggregationParam(selectClause, groupByClause));
        }

        for(Param param: params){
            param.parseParam();
        }

        Mapper mapper = new FindMapper();
        MongoQuery mongoQuery = mapper.mapParams(params);
        mongoQuery.setCollection(Param.getCollection());
        System.out.println(mongoQuery.toString());
        System.out.println(mongoQuery.getCollection());
    }
}
