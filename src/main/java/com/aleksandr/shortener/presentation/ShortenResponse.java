package com.aleksandr.shortener.presentation;

import com.aleksandr.shortener.domain.ShortUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ShortenResponse {

    @NonNull
    @Schema(
            description = "generated short url",
            example = "http://localhost:8080/bAFCKk",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private final String shortUrl;

    public static ShortenResponse from(ShortUrl url, HttpServletRequest servlet) {
        String requestUrl = servlet.getRequestURL().toString();

        StringBuilder shortUrl = new StringBuilder();
        shortUrl.append(requestUrl.replace("v1/shorten", ""));
        if (!requestUrl.contains("localhost")) {
            shortUrl.append("dev/"); // temp solution to map to API Gateway stage
        }
        shortUrl.append(url.getId());

        return ShortenResponse.builder()
                .shortUrl(shortUrl.toString())
                .build();
    }

}
