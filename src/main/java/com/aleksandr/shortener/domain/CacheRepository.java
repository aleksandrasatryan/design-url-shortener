package com.aleksandr.shortener.domain;

public interface CacheRepository {

    void saveLongUrl(String id, String value);

    String getLongUrl(String id);

}
