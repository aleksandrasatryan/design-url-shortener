package com.aleksandr.shortener.presentation;

import com.aleksandr.common.domain.model.Actor;
import com.aleksandr.shortener.application.ApplicationService;
import com.aleksandr.shortener.domain.ShortUrl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Tag(name = "Short URL", description = "APIs")
public class ShortUrlController {

    private final ApplicationService service;

    @Operation(summary = "request for long url shortening")
    @PostMapping(value = "/v1/shorten", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ShortenResponse shortenUrl(@RequestBody ShortenRequest request, HttpServletRequest servlet) {
        ShortUrl url = service.save(request.getUrl(), request.getOwner(), Actor.of("authenticatedUser"));

        return ShortenResponse.from(url, servlet);
    }

    @Operation(summary = "redirect to long url")
    @GetMapping("{id}")
    public RedirectView redirectToOriginal(@PathVariable final String id) {
        String longUrl = service.getLongUrl(id);

        if (longUrl != null) {
            RedirectView viw = new RedirectView(longUrl);
            viw.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return viw;
        }

        RedirectView view = new RedirectView(); // TODO: provide 404 not found page
        view.setStatusCode(HttpStatus.NOT_FOUND);
        return view;
    }

}

