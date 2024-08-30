package com.aleksandr.shortener.integration;

import com.aleksandr.test.command.DeployUsingSamCommand;
import com.aleksandr.test.command.PurgeInfrastructureCommand;
import com.aleksandr.test.command.StartInfrastructureCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class TestIntegrationCommand {

    static final StartInfrastructureCommand START_COMMAND = StartInfrastructureCommand.builder()
            .needAws(true)
            .build();

    static final DeployUsingSamCommand DEPLOY_COMMAND = DeployUsingSamCommand.builder()
            .samPath("template.yml")
            .build();

    static final PurgeInfrastructureCommand PURGE_COMMAND = PurgeInfrastructureCommand.builder()
            .build();

}
