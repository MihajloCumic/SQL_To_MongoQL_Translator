package adapter.implementation.param.implementation;

import adapter.implementation.param.specification.Param;
import sqlquery.Clause;
import sqlquery.FromClause;

public class FromParam extends Param {

    public FromParam(Clause clause) {
        super(clause);
    }

    @Override
    public Param parseParam() {

        FromClause clause = (FromClause) this.clause;

        Param.setCollection(clause.getParameters().get(0));

        if(clause.getParameters().size() == 1) return this;

        String param = "";

        for(int i  = 1; i < clause.getParameters().size(); i++){
            String comma = i > 1? "| ": "";
            param += comma + concatJoins(clause.getParameters().get(i), clause.getJoinFields().get(i - 1));
        }

        this.param = param;
        return this;
    }

    private String concatJoins(String collection, String joinField){
        String lookup = "{ $lookup: { from: \"" + collection + "\", localField: \"" + joinField + "\", foreignField: \"" + joinField + "\", as: \"" + collection + "\"}} |";
        String unwind = "{ $unwind: \"$" + collection + "\"}";
        return lookup + unwind;
    }
}
