package com.aleksandr.shortener.infrastructure.dynamodb;

import com.aleksandr.common.domain.model.Actor;
import com.aleksandr.common.domain.model.Audit;
import com.aleksandr.shortener.domain.ShortUrl;
import lombok.Data;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.time.OffsetDateTime;

import static com.aleksandr.shortener.infrastructure.dynamodb.DynamoDbRepository.GSI_LONG_URL;

@Data
@DynamoDbBean
public class ShortUrlItem {

    @Getter(onMethod_ = @DynamoDbPartitionKey)
    private String id;

    @Getter(onMethod_ = @DynamoDbSecondaryPartitionKey(indexNames = GSI_LONG_URL))
    private String longUrl;

    private String owner;

    private String creator;

    private OffsetDateTime createdOn;

    private String modifier;

    private OffsetDateTime modifiedOn;

    @Getter(onMethod_ = @DynamoDbVersionAttribute)
    private Integer version;

    public static ShortUrlItem from(ShortUrl entity) {
        Actor creator = entity.getAudit().getCreator();
        Actor modifier = entity.getAudit().getCreator();

        ShortUrlItem item = new ShortUrlItem();
        item.setId(entity.getId());
        item.setLongUrl(entity.getLongUrl());
        item.setOwner(entity.getOwner());
        item.setCreator(creator != null ? creator.getActor() : null);
        item.setCreatedOn(creator != null ? creator.getTime() : null);
        item.setModifier(modifier != null ? modifier.getActor() : null);
        item.setModifiedOn(modifier != null ? modifier.getTime() : null);

        return item;
    }

    public static ShortUrl to(ShortUrlItem item) {
        if (item == null) {
            return null;
        }

        Actor creator = Actor.of(item.getCreator(), item.getCreatedOn());
        Actor modifier = Actor.of(item.getModifier(), item.getModifiedOn());

        return ShortUrl.builder()
                .id(item.getId())
                .longUrl(item.getLongUrl())
                .owner(item.getOwner())
                .audit(Audit.of(creator, modifier))
                .build();
    }

}
