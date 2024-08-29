package com.aleksandr.shortener.presentation;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ShortUrlController {

    @GetMapping("/v1/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/v1/shorten")
    public String shortenUrl() {
        String shortUrl = "todo";
        return shortUrl;
    }

    @GetMapping("/v1/{shortUrl}")
    public String getLongUrl(@PathVariable final String shortUrl) {
        String longUrl = "todo";
        return longUrl;
    }

}

