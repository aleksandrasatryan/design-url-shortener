package com.aleksandr.shortener.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Short URL Health", description = "API")
public class HealthController {

    @GetMapping("/v1/health")
    public String health() {
        return "OK";
    }

}

