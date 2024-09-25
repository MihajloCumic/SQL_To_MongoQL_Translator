import adapter.implementation.mapper.implementation.FindMapper;
import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.implementation.*;
import adapter.implementation.param.specification.Param;
import com.mongodb.client.MongoCursor;
import executor.MongoDBController;
import executor.implementation.FindExecutor;
import executor.specification.Executor;
import mongoql.MongoQuery;
import org.bson.Document;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.*;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

import java.util.ArrayList;

public class FindExecutorTest1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select first_name, last_name, salary from employees where  salary > 10000 and salary <= 17000 order by salary desc";
        String str1 = "select * from employees where last_name like \"King\" and first_name like /Janette/";
        String str2 = "select first_name, last_name, salary from employees where  salary > 10000 and salary <= 17000 order by salary desc";


        SQLQuery query = (SQLQuery) parser.parse(str1);
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
        Executor executor = new FindExecutor(MongoDBController.getConnection());
        MongoCursor<Document> documents = executor.executeQuery( mongoQuery);

        while(documents.hasNext()){
            Document d = documents.next();
            System.out.println(d.toJson());
        }

        executor.getConnection().close();
    }
}
