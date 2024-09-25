package adapter.implementation.param.specification;

import sqlquery.Clause;

public abstract class Param {

    private static String collection = null;
    protected String param = "";

    protected Clause clause;

    public Param(Clause clause){
        this.clause = clause;
    }


    abstract public Param parseParam();

    public static String getCollection() {
        return collection;
    }

    public static void setCollection(String collection) {
        Param.collection = collection;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
