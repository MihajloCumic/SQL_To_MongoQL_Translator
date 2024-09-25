package adapter.implementation.param.implementation;

import adapter.implementation.param.specification.Param;
import sqlquery.Clause;
import sqlquery.OrderByClause;

public class OrderByParam extends Param {
    public OrderByParam(Clause clause) {
        super(clause);
    }

    @Override
    public Param parseParam() {
        OrderByClause clause = (OrderByClause) this.clause;
        String param = "{";

        for(int i = 0; i < clause.getParameters().size(); i++){
            String order = clause.getOrderList().get(i).equalsIgnoreCase("asc")? "1": "-1";
            String comma = i == 0? "": ", ";
            param += comma + "\"" + clause.getParameters().get(i) + "\": " + order;
        }

        param += "}";

        this.param = param;
        return this;
    }
}
