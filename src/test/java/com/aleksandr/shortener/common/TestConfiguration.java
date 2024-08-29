package com.aleksandr.shortener.common;

import com.aleksandr.test.infrastructure.helper.LocalInfrastructureHelper;
import com.aleksandr.test.infrastructure.spring.BaseTestConfiguration;

public class TestConfiguration extends BaseTestConfiguration {

    @Override
    public LocalInfrastructureHelper getLocalInfrastructureHelper() {
        return LocalInfrastructureHelper.builder()
                .startCommand(TestCommand.START_COMMAND)
                .deploySamCommand(TestCommand.DEPLOY_COMMAND)
                .purgeCommand(TestCommand.PURGE_COMMAND)
                .build();
    }

}
