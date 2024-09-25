package validator.implementation;

import sqlquery.*;
import validator.implementation.rules.*;
import validator.specification.Validator;

import java.util.ArrayList;

public class SQLValidator implements Validator {

    private ArrayList<Rule> rules;
    private String errorMessage;

    public SQLValidator(){
        this.rules = new ArrayList<>();
        this.rules.add(new MandatoryRule("Mandatory query parts", "Missing mandatory query parts."));
        this.rules.add(new MandatoryParamsRule("Missing parameters", "Missing parameteres."));
        this.rules.add(new GroupByRule("Group by", "Everything that is not under aggregation function in the select clause needs to be in the group by."));
        this.rules.add(new WhereAggregationRule("Where aggregation", "Functions of aggregation cannot be in the where clause"));
        this.rules.add(new JoinRule("Join condition", "Each join must have a condition (using or on)."));
        this.rules.add(new OrderByRule("Order by condition", "Each column in order by must have asc || desc thath goes with it."));
        this.rules.add(new NumberOfJoinsRule("Number of joins", "Query cannot have more than two joins"));


    }

    @Override
    public boolean validate(SQLQuery query) {
        for(Rule rule: this.rules){
            if(!rule.check(query)){
                this.errorMessage = rule.getErrorMessage();
                return false;
            }
        }

        return true;
    }

    @Override
    public String getError() {
        return this.errorMessage == null? "Error": this.errorMessage;
    }


}
