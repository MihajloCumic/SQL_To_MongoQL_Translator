package adapter.implementation.mapper.specification;

import adapter.implementation.param.specification.Param;
import mongoql.MongoQuery;

import java.util.ArrayList;

public interface Mapper {
    MongoQuery mapParams(ArrayList<Param> params);
}
