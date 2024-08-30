package com.aleksandr.shortener.domain;

import com.aleksandr.common.domain.model.Actor;
import com.aleksandr.common.domain.model.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

import java.util.Random;
import java.util.zip.CRC32;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private static final Random RANDOM = new Random();
    private static final String BASE_62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final ShortUrlRepository repository;

    @Retryable(retryFor = ConditionalCheckFailedException.class, maxAttempts = 5)
    public ShortUrl save(String longUrl, String owner, Actor actor) {
        ShortUrl existing = repository.getByLongUrl(longUrl);
        if (existing != null) {
            return existing;
        }

        ShortUrl entity = ShortUrl.builder()
                // will be retried with new random appended integer until we find an available ID
                .id(toShortHashValue(longUrl + RANDOM.nextInt(10000)))
                .owner(owner)
                .longUrl(longUrl)
                .audit(Audit.create(actor))
                .build();

        repository.save(entity);

        return entity;
    }

    public ShortUrl get(String id) {
        return repository.getById(id);
    }

    private String toShortHashValue(String longUrl) {
        CRC32 crc32 = new CRC32();
        crc32.update(longUrl.getBytes());
        return toBase62(crc32.getValue());
    }

    private String toBase62(long num) {
        int base = BASE_62_CHARS.length();
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE_62_CHARS.charAt((int) (num % base)));
            num /= base;
        }
        return sb.reverse().toString();

    }

}
