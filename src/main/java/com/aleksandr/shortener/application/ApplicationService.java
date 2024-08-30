package com.aleksandr.shortener.application;

import com.aleksandr.common.domain.model.Actor;
import com.aleksandr.shortener.domain.CacheRepository;
import com.aleksandr.shortener.domain.ShortUrl;
import com.aleksandr.shortener.domain.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ShortUrlService domainService;

    private final CacheRepository cacheRepository;

    public ShortUrl save(String longUrl, String owner, Actor actor) {
        return domainService.save(longUrl, owner, actor);
    }

    public String getLongUrl(String id) {
        String cache = cacheRepository.getLongUrl(id);
        if (cache != null) {
            return cache;
        }

        ShortUrl entity = domainService.get(id);
        if (entity != null) {
            cacheRepository.saveLongUrl(id, entity.getLongUrl());
            return entity.getLongUrl();
        }

        return null;
    }

}
