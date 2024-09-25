import adapter.implementation.param.implementation.FromParam;
import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.implementation.WhereParam;
import adapter.implementation.param.specification.Param;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.*;

public class FromParamTest1 {

    public static void main(String[] args) {
        Parser parser = new SQLParser();

        String str = "select first_name, department_id, departments.department_name, jobs.job_title from employees join departments on (department_id) join jobs using (job_id)";

        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());

        FromClause clause = null;
        SelectClause clause1 = null;
        for(Clause cls: query.getQuery()){
            if(cls instanceof FromClause){
                clause = (FromClause) cls;
                continue;
            }
            if(cls instanceof SelectClause){
                clause1 = (SelectClause) cls;
                continue;
            }
        }

        Param param = new FromParam(clause);
        Param param1 = new SelectParam(clause1);
        System.out.println(param1.parseParam());
        System.out.println(param.parseParam());
        System.out.println(Param.getCollection());
    }
}
