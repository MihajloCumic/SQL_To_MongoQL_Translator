import adapter.implementation.param.implementation.AggregationParam;
import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.specification.Param;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.Clause;
import sqlquery.GroupByClause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class AggregationParamTest1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select avg(salary), sum(department_salary), job_id from employees group by job_id, department_id";

        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
        SelectClause clause = null;
        GroupByClause clause1 = null;
        for(Clause cls: query.getQuery()){
            if(cls instanceof SelectClause){
                clause = (SelectClause) cls;
                continue;
            }
            if(cls instanceof GroupByClause){
                clause1 = (GroupByClause) cls;
            }
        }

        Param param = new SelectParam(clause);
        Param param1 = new AggregationParam(clause, clause1);
        System.out.println(param1.parseParam());
    }
}
