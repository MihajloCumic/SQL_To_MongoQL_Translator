import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class TestGroupBy1 {
    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String str = "select name, avg(salary), last_name, avg(age) from employees group by last_name order by salary asc, age desc, salary asc";


        SQLQuery query = (SQLQuery) parser.parse(str);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
    }
}
