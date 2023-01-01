package com.megapreneur.urlshortener.controllers;

import com.megapreneur.urlshortener.dtos.GenerateUrlRequest;
import com.megapreneur.urlshortener.dtos.GenerateUrlResponse;
import com.megapreneur.urlshortener.models.Urls;
import com.megapreneur.urlshortener.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {
    @Autowired
    private UrlsService urlsService;

    @PostMapping("/getShortenUrl")
    public ResponseEntity<?> generateShortenedUrl(@RequestBody GenerateUrlRequest request){
        GenerateUrlResponse url = urlsService.generateShortUrl(request);
        return ResponseEntity.ok(url.getMessage());
    }

}
