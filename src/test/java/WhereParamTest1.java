import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.implementation.WhereParam;
import adapter.implementation.param.specification.Param;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.Clause;
import sqlquery.SQLQuery;
import sqlquery.SelectClause;
import sqlquery.WhereClause;

public class WhereParamTest1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();

        String str = "select avg( salary), name, count(department_id ), last_name from employees join left department on (employee.department_id = department.department_id) where name like \"Mike\" and age = 40 and avg(salary) > 1000 group by name, last_name having avg(salary) > 8000 or min(salary) > 500 order by last_name, name desc";

        String strInTest = "select name, last_name from hr.employees where name like \"mike\" and department_id < 100 or salary >= 10000";
        String strInTest1 = "select name, last_name from hr.employees where name like \"mike\" or salary >= 10000  and dep in (1,2,3,4,5) or age >= 60 ";
        String threeJoinTest = "select name, last_name from hr.employees join left dep using dep_id join jobs on (employees.job_id = jobs.job_id)";
        String joinTest = "select name from employees join dep using (dep_id)";
        String normalnanString = "select name from employees where salary > 10000 or salary < 15000";

        SQLQuery query = (SQLQuery) parser.parse(normalnanString);

        System.out.println(query.toString());

        WhereClause clause = null;
        for(Clause cls: query.getQuery()){
            if(cls instanceof WhereClause){
                clause = (WhereClause) cls;
                break;
            }
        }

        Param param = new WhereParam(clause);
        System.out.println(param.parseParam().getParam());
    }
}
