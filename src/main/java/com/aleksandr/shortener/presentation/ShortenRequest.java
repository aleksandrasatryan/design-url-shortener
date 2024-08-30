package com.aleksandr.shortener.presentation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ShortenRequest {

    @NonNull
    @Schema(
            description = "long url",
            example = "https://en.wikipedia.org/wiki/Portal:Current_events/Sports",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private final String url;

    @NonNull
    @Schema(
            description = "owner of long url",
            example = "wikipedia",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private final String owner;

}
