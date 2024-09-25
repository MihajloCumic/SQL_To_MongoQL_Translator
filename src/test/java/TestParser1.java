import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;

import java.util.Arrays;

public class TestParser1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();

        String str = "select avg( salary), name, count(department_id ), last_name from employees join left department on (employee.department_id = department.department_id) where name like \"Mike\" and age = 40 and avg(salary) > 1000 group by name, last_name having avg(salary) > 8000 or min(salary) > 500 order by last_name asc, name desc";

        String strInTest = "select name, last_name from hr.employees where name like \"mike\" and department_id in (60, 90, 100)";
        String threeJoinTest = "select name, last_name from hr.employees join left dep using dep_id join jobs on (employees.job_id = jobs.job_id)";
        String joinTest = "select name from employees join dep using (dep_id)";

        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());
    }
}
