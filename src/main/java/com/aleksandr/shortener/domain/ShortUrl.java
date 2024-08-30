package com.aleksandr.shortener.domain;

import com.aleksandr.common.domain.model.Audit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@EqualsAndHashCode
public class ShortUrl {

    @NonNull
    private final String id;

    @NonNull
    private final String longUrl;

    @NonNull
    private final String owner;

    @NonNull
    private final Audit audit;

}
