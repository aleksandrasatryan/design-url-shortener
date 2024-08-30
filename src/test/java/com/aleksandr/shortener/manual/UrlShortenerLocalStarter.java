package com.aleksandr.shortener.manual;

import com.aleksandr.shortener.integration.TestIntegrationConfiguration;
import com.aleksandr.test.infrastructure.spring.BaseTestApplication;
import org.springframework.context.annotation.Configuration;


public class UrlShortenerLocalStarter extends BaseTestApplication {

    public static void main(final String[] args) {
        new UrlShortenerLocalStarter().run(args);
    }

    @Configuration
    static class LocalApplicationConfiguration extends TestIntegrationConfiguration {

    }

}