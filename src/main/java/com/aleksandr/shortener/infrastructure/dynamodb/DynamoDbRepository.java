package com.aleksandr.shortener.infrastructure.dynamodb;

import com.aleksandr.shortener.domain.ShortUrl;
import com.aleksandr.shortener.domain.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@Repository
public class DynamoDbRepository implements ShortUrlRepository {

    private static final String TABLE_NAME = "ShortUrl";
    public static final String GSI_LONG_URL = "LongUrl";

    private final DynamoDbTable<ShortUrlItem> table;
    private final DynamoDbIndex<ShortUrlItem> gsi;

    @Autowired
    public DynamoDbRepository(DynamoDbEnhancedClient client) {
        table = client.table(TABLE_NAME, TableSchema.fromBean(ShortUrlItem.class));
        gsi = table.index(GSI_LONG_URL);
    }

    @Override
    public void save(ShortUrl entity) {
        ShortUrlItem item = ShortUrlItem.from(entity);

        Expression expression = Expression.builder()
                .expression("attribute_not_exists(id)")
                .build();

        table.putItem(request -> request
                .item(item)
                .conditionExpression(expression)
        );
    }

    @Override
    public ShortUrl getById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        ShortUrlItem item = table.getItem(key);
        return ShortUrlItem.to(item);
    }

    @Override
    public ShortUrl getByLongUrl(String longUrl) {
        Key key = Key.builder()
                .partitionValue(longUrl)
                .build();

        QueryEnhancedRequest request = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(key))
                .limit(1)
                .build();

        SdkIterable<Page<ShortUrlItem>> page = gsi.query(request);
        List<ShortUrlItem> item = page.stream()
                .flatMap(p -> p.items().stream())
                .toList();

        if (item.isEmpty()) {
            return null;
        }
        return ShortUrlItem.to(item.getFirst());
    }

}