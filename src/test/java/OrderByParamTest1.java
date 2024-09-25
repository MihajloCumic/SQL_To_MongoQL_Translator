import adapter.implementation.param.implementation.OrderByParam;
import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.specification.Param;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.Clause;
import sqlquery.OrderByClause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class OrderByParamTest1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select name, last_name, avg(salary), sum(department_salary) from employees group by name, last_name order by avg(salary) asc, name desc, max(salary desc)";

        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
        OrderByClause clause = null;
        for(Clause cls: query.getQuery()){
            if(cls instanceof OrderByClause){
                clause = (OrderByClause) cls;
                break;
            }
        }

        Param param = new OrderByParam(clause);
        System.out.println(param.parseParam());
    }
}
