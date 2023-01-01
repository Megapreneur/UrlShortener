package com.megapreneur.urlshortener.service;

import com.megapreneur.urlshortener.dtos.GenerateUrlRequest;
import com.megapreneur.urlshortener.dtos.GenerateUrlResponse;

public interface UrlsService {
    GenerateUrlResponse generateShortUrl(GenerateUrlRequest request);
    String updateShortenUrl(String url, String customUrl);
    void deleteUrlUsingShortenedUrl(String shortenedUrl);
    void deleteUrlUsingOriginalUrl(String originalUrl);
    String getDecodedUrl(String shortenedUrl);
    String getEncodedUrl(String originalUrl);
}
