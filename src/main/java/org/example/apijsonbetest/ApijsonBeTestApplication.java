package org.example.apijsonbetest;

import apijson.framework.APIJSONApplication;
import apijson.framework.APIJSONCreator;
import apijson.framework.APIJSONParser;
import apijson.orm.FunctionParser;
import apijson.orm.Parser;
import apijson.orm.SQLConfig;
import apijson.orm.Verifier;
import org.example.apijsonbetest.config.DemoFunctionParser;
import org.example.apijsonbetest.config.DemoParser;
import org.example.apijsonbetest.config.DemoSQLConfig;
import org.example.apijsonbetest.config.DemoVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApijsonBeTestApplication {

    static {
        APIJSONApplication.DEFAULT_APIJSON_CREATOR = new APIJSONCreator<Long>() {
            @Override
            public Parser<Long> createParser() {
                return new DemoParser();
            }

            @Override
            public FunctionParser createFunctionParser() {
                return new DemoFunctionParser();
            }

            @Override
            public Verifier<Long> createVerifier() {
                return new DemoVerifier();
            }

            @Override
            public SQLConfig createSQLConfig() {
                return new DemoSQLConfig();
            }

        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApijsonBeTestApplication.class, args);
        // 上线生产环境前改为 false，可不输出 APIJSONORM 的日志 以及 SQLException 的原始(敏感)信息
        APIJSONParser.IS_PRINT_BIG_LOG = true;
        APIJSONApplication.init();
    }

}
