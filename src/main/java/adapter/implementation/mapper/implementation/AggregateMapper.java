package adapter.implementation.mapper.implementation;

import adapter.implementation.mapper.specification.Mapper;
import adapter.implementation.param.implementation.*;
import adapter.implementation.param.specification.Param;
import mongoql.AggregateMongoQuery;
import mongoql.MongoQuery;

import java.util.ArrayList;

public class AggregateMapper implements Mapper {
    @Override
    public MongoQuery mapParams(ArrayList<Param> params) {

        String match = "";
        String project = "";
        String lookup = "";
        String group = "";
        String sort = "";

        for(Param param: params){
            if(param.getParam().equalsIgnoreCase("")) continue;
            if(param instanceof WhereParam){
                match = matchParam(param);
                continue;
            }
            if(param instanceof FromParam){
                lookup = param.getParam();
                continue;
            }
            if(param instanceof SelectParam){
                project = projectParam(param);
                continue;
            }
            if(param instanceof AggregationParam){
                group = param.getParam();
                continue;
            }
            if(param instanceof OrderByParam){
                sort = sortParam(param);
            }

        }

        System.out.println(project);
        return new AggregateMongoQuery(sort, match, project, lookup, group);
    }

    private String matchParam(Param param){

        return "{ $match: " + param.getParam() + "}";

    }

    private String projectParam(Param param){
        return "{ $project: " + param.getParam() + "}";
    }

    private String sortParam(Param param){
        return "{ $sort: " + param.getParam() + "}";
    }


}
