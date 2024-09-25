package adapter.implementation;

import adapter.implementation.mapper.MapperBuilder;
import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.ParamBuilder;
import adapter.implementation.param.specification.Param;
import adapter.specification.QueryAdapter;
import mongoql.MongoQuery;
import sqlquery.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class QueryAdapterImp implements QueryAdapter {


    public QueryAdapterImp(){

    }

    @Override
    public MongoQuery getMongoQL(SQLQuery query) {
        ParamBuilder builder = new ParamBuilder(query);
        MapperBuilder mapperBuilder = new MapperBuilder(query);

        ArrayList<Param> params = (ArrayList<Param>) builder.buildParams();
        MongoQuery mongoQuery = mapperBuilder.getMapper().mapParams(params);
        mongoQuery.setCollection(Param.getCollection());

        return mongoQuery;
    }
}
