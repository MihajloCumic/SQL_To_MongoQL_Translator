package parser.implementation;

import parser.specification.Parser;
import sqlquery.Clause;
import sqlquery.SQLQuery;
import sqlquery.factory.ClauseFactory;
import sqlquery.factory.utils.FactoryUtils;

import java.util.Arrays;

public class SQLParser implements Parser {

    private SQLQuery query;
    private String[] keywords= {"select", "from", "where", "group by", "order by", "having"};

    public SQLParser(){
        this.query = new SQLQuery();
    }

    @Override
    public Object parse(String input) {
        input = input.replaceAll(",", " ").replaceAll("\\(", " ").replaceAll("\\)", "").replaceAll("\\s+", " ").trim();
        String[] clauses = input.split(" ");

        Clause currClause = null;
        for(int i = 0; i < clauses.length; i++){
            String str = clauses[i];
            if(Arrays.asList(this.keywords).contains(str)){
                ClauseFactory factory = FactoryUtils.getFactory(str);
                Clause clause = factory.createClause(str);
                this.query.addClause(clause);
                currClause = clause;
            }else if(clauses.length > i + 1 && Arrays.asList(this.keywords).contains(str + " " + clauses[i + 1])){
                String twoWordClause = str + " " + clauses[i + 1];
                ClauseFactory factory = FactoryUtils.getFactory(twoWordClause);
                Clause clause = factory.createClause(twoWordClause);
                this.query.addClause(clause);
                currClause = clause;
                ++i;
            }else {
                //Da ne bi bacao null pointer exeption kada currClause ne pokazuje na neki clause
                // slucaj kada se izostavi select u query-ju
                if(currClause != null){
                    currClause.addParam(str);
                }
            }

        }

        return this.query;
    }
}
