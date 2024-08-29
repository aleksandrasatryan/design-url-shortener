package com.aleksandr.shortener.common;

import com.aleksandr.test.command.DeployUsingSamCommand;
import com.aleksandr.test.command.PurgeInfrastructureCommand;
import com.aleksandr.test.command.StartInfrastructureCommand;
import com.aleksandr.test.infrastructure.helper.EventQueueServiceType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.aleksandr.test.infrastructure.helper.EventQueueServiceType.SQS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCommand {

    private static final Map<String, EventQueueServiceType> QUEUES = Map.of(
            "TODO", SQS
    );

    private static final List<String> STORAGES = List.of(
    );

    static final StartInfrastructureCommand START_COMMAND = StartInfrastructureCommand.builder()
            .needAws(true)
            .build();

    static final DeployUsingSamCommand DEPLOY_COMMAND = DeployUsingSamCommand.builder()
            .samPath("template.yml")
            .build();

    public static final PurgeInfrastructureCommand PURGE_COMMAND = PurgeInfrastructureCommand.builder()
            .queues(QUEUES)
            .storages(STORAGES)
            .build();

}
