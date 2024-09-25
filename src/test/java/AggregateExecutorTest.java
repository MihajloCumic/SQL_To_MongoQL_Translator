import adapter.implementation.mapper.implementation.AggregateMapper;
import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.implementation.*;
import adapter.implementation.param.specification.Param;
import com.mongodb.client.MongoCursor;
import executor.MongoDBController;
import executor.implementation.AggregateExecutor;
import executor.specification.Executor;
import mongoql.MongoQuery;
import org.bson.Document;
import packager.implementation.MongoPackager;
import packager.specification.Packager;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.*;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AggregateExecutorTest {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select department_id, avg(salary), max(salary) from employees  group by department_id";
        String str1 = "select first_name, last_name, jobs.job_title, departments.department_name from employees join jobs using(job_id) join departments using(department_id) where last_name like \"King\" and first_name = \"Steven\"";
        String str2 = "select first_name, departments.department_id, departments.department_name from employees join departments on (department_id)";


        SQLQuery query = (SQLQuery) parser.parse(str2);
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

        params.add(new AggregationParam(selectClause, groupByClause));


        for(Param param: params){
            param.parseParam();
        }

        Mapper mapper = new AggregateMapper();
        MongoQuery mongoQuery = mapper.mapParams(params);
        mongoQuery.setCollection(Param.getCollection());
        System.out.println(mongoQuery.toString());
        System.out.println(mongoQuery.getCollection());
        Executor executor = new AggregateExecutor(MongoDBController.getConnection());
        MongoCursor<Document> documents = executor.executeQuery(mongoQuery);

//        while(documents.hasNext()){
//            Document d = documents.next();
//            System.out.println(d.toJson().toString());
//        }

        Packager packager = new MongoPackager();
        List<Map<String, String>> rows = packager.packageData(documents);

        int collumnCount = rows.get(0).keySet().size();
        System.out.println(collumnCount);
        Object[] objects = rows.get(0).keySet().toArray();
        ArrayList<String> columns = new ArrayList<>();

        for(int i = 0; i < collumnCount; i++){
            columns.add((String)objects[i]);
        }
        for(int i = 0; i < collumnCount; i++){
            System.out.println(columns.get(i));
        }
        for(int i = 0; i < rows.size(); i++){
            for(int j = 0; j < columns.size(); j++){
                System.out.println(rows.get(i).get(columns.get(j)));
            }
        }

        executor.getConnection().close();


    }
}
