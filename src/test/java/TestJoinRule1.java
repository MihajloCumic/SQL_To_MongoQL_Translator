import parser.implementation.SQLParser;
import parser.specification.Parser;
import sqlquery.SQLQuery;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class TestJoinRule1 {

    public static void main(String[] args) {
        Parser parser = new SQLParser();
        Validator validator = new SQLValidator();

        String strValid1 = "select name from employee join dep using (dep_id)";
        String strValid2 = "select name from employee join dep";
        String strValid3 = "select name from employee join dep using (dep_id) join jobs on (e.job_id = jobs.job_id)";
        String strValid4 = "select name from employee join dep using (dep_id) join jobs";
        String strValid5 = "select name from employee join dep using (dep_id) join jobs on (e.job_id = jobs.job_id) join country using (country_id)";
        String strValid6 = "select name from employee join dep using (dep_id) join jobs using(job_id) join country";
        String strValid7 = "select name from employee join dep using (dep_id) join jobs on (e.job_id = jobs.job_id) join country using (country_id) join city on (city_id = j.city_id)";
        String strValid8 = "select name from employee join dep using (dep_id) join jobs on (e.job_id = jobs.job_id) join country using (country_id) join city";



        SQLQuery query = (SQLQuery) parser.parse(strValid7);

        System.out.println(query.toString());

        System.out.println(validator.validate(query));
    }
}
