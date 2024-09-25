import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.specification.Param;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.Clause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class TestSelectParam1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select name, last_name, avg(salary), sum(department_salary) from employees group by name, last_name";




        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
        SelectClause clause = null;
        for(Clause cls: query.getQuery()){
            if(cls instanceof SelectClause){
                clause = (SelectClause) cls;
                break;
            }
        }

        Param param = new SelectParam(clause);
        System.out.println(param.parseParam().getParam());
    }
}
