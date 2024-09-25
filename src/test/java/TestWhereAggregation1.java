import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class TestWhereAggregation1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select name, last_name from employees where avg(salary) > 10000";
        String str1 = "select name, last_name from employees where min(salary) > 10000";
        String str2 = "select name, last_name from employees where max(salary) > 10000";
        String str3 = "select name, last_name from employees where count(salary) > 10000";
        String str4 = "select name, last_name from employees where sum(salary) > 10000";
        String validString = "select name, last_name from employees where salary > 10000";
        String validString1 = "select name, last_name from employees";
        String invalidOrderBy = "select name from employees order by name , age desc";


        SQLQuery query = (SQLQuery) parser.parse(invalidOrderBy);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
    }
}
