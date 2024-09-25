package sqlquery.factory.utils;

import sqlquery.factory.ClauseFactory;
import sqlquery.factory.implementation.*;

public class FactoryUtils {

    public static ClauseFactory getFactory(String factory){
        if(factory.equalsIgnoreCase("select")) return new SelectFactory();
        if(factory.equalsIgnoreCase("from")) return new FromFactory();
        if(factory.equalsIgnoreCase("where")) return new WhereFactory();
        if(factory.equalsIgnoreCase("group by")) return new GroupByFactory();
        if(factory.equalsIgnoreCase("order by")) return new OrderByFactory();
        if(factory.equalsIgnoreCase("having")) return new HavingFactory();
        return null;
    }
}
