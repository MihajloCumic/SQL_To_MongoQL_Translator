package adapter.implementation.mapper.implementation;

import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.implementation.OrderByParam;
import adapter.implementation.param.implementation.SelectParam;
import adapter.implementation.param.implementation.WhereParam;
import adapter.implementation.param.specification.Param;
import mongoql.FindMongoQuery;
import mongoql.MongoQuery;

import java.util.ArrayList;

public class FindMapper implements Mapper {
    @Override
    public MongoQuery mapParams(ArrayList<Param> params) {
        String find = "{}";
        String projection = "{}";
        String sort = "{}";

        for(Param param: params){
            if(param.getParam().equalsIgnoreCase("")) continue;
            if(param instanceof WhereParam){
                find = param.getParam();
                continue;
            }
            if(param instanceof SelectParam){
                projection = param.getParam();
                continue;
            }
            if(param instanceof OrderByParam){
                sort = param.getParam();
            }
        }

        return new FindMongoQuery(sort, find, projection);
    }
}
