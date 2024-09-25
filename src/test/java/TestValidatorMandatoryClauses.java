import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class TestValidatorMandatoryClauses {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String validStr1 = "select * from employees where name like \"Mike\"";
        String validStr2 = "select name, last_name, avg(salary) from employees join departments on (employees.department_id = department.departments_id) where age in (60, 65, 70) group by name, last_name having avg(salary) > 20000 order by name desc";
        String missingKeyWords1 = "select * employees where name like \"Mike\" order by name desc";
        String missingKeyWords2 = "* from employees where salary > 100";
        String missingKeyWords3 = "* departments employees where salary > 100";
        String selectMissingParams = "select from departments where age in (60, 70, 90)";
        String fromMissingParams = "select * from where age in (60, 70, 90)";
        String strJoin = "select * from employees join departments using dep_id join jobs on (job_id)";


        SQLQuery query = (SQLQuery) parser.parse(strJoin);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
    }
}
