package validator.specification;

import sqlquery.SQLQuery;

public interface Validator {

    public boolean validate(SQLQuery query);
    public String getError();
}
