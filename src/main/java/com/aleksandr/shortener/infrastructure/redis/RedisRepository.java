package com.aleksandr.shortener.infrastructure.redis;

import com.aleksandr.shortener.domain.CacheRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RedisRepository implements CacheRepository {

    // assume RedisTemplate is used to interact with actual Redis/MemoryDB cluster
    private final Map<String, String> redisTemplate = new HashMap<>();

    @Override
    public void saveLongUrl(String id, String entity) {
        redisTemplate.put(id, entity);
    }

    @Override
    public String getLongUrl(String id) {
        return redisTemplate.get(id);
    }

}
