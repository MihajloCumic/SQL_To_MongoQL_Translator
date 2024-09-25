package framework;

import adapter.implementation.QueryAdapterImp;
import adapter.specification.QueryAdapter;
import com.mongodb.MongoClient;
import executor.ExecutorFactory;
import executor.specification.Executor;
import mongoql.MongoQuery;
import packager.implementation.MongoPackager;
import packager.specification.Packager;
import parser.implementation.SQLParser;
import parser.specification.Parser;
import validator.implementation.SQLValidator;
import validator.specification.Validator;

public class AppFramework {
    private Parser parser;
    private Validator validator;
    private QueryAdapter adapter;
    private Packager packager;

    public AppFramework(){
        this.parser = new SQLParser();
        this.validator = new SQLValidator();
        this.adapter = new QueryAdapterImp();
        this.packager = new MongoPackager();
    }

    public Executor getExecutor(MongoClient connection, MongoQuery mongoQuery){
        return ExecutorFactory.createExecutor(connection, mongoQuery);
    }

    public Parser getParser() {
        return parser;
    }

    public Validator getValidator() {
        return validator;
    }

    public QueryAdapter getAdapter() {
        return adapter;
    }

    public Packager getPackager() {
        return packager;
    }
}
