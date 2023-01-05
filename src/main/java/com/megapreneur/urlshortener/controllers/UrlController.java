package com.megapreneur.urlshortener.controllers;

import com.megapreneur.urlshortener.dtos.GenerateUrlRequest;
import com.megapreneur.urlshortener.dtos.GenerateUrlResponse;
import com.megapreneur.urlshortener.exceptions.UrlNotFoundException;
import com.megapreneur.urlshortener.models.Urls;
import com.megapreneur.urlshortener.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public ResponseEntity<?> getShortenedUrl()
    @DeleteMapping("/{originalUrl}")
    public void deleteOriginalUrl(@PathVariable String originalUrl) throws UrlNotFoundException{
        urlsService.deleteUrlUsingOriginalUrl(originalUrl);
    }
    @DeleteMapping("/{shortenedUrl}")
    public void deleteShortenedUrl(@PathVariable String shortenedUrl) throws UrlNotFoundException{
        urlsService.deleteUrlUsingShortenedUrl(shortenedUrl);
    }

}
