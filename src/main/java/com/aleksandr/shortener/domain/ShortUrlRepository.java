package com.aleksandr.shortener.domain;

public interface ShortUrlRepository {

    void save(ShortUrl entity);

    ShortUrl getById(String id);

    ShortUrl getByLongUrl(String longUrl);

}
