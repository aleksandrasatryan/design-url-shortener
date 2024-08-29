package com.aleksandr.shortener.manual;

import com.aleksandr.shortener.common.TestConfiguration;
import com.aleksandr.test.infrastructure.spring.BaseTestApplication;
import org.springframework.context.annotation.Configuration;

public class LocalApplicationStarter extends BaseTestApplication {

    public static void main(final String[] args) {
        new LocalApplicationStarter().run(args);
    }

    @Configuration
    static class LocalApplicationConfiguration extends TestConfiguration {

        @Override
        protected void setEnvVariables() {
            super.setEnvVariables();
            System.setProperty("APPLICATION", LocalApplicationStarter.class.getName());
        }
    }

}