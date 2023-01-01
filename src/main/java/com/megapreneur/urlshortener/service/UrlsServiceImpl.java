package com.megapreneur.urlshortener.service;

import com.google.common.hash.Hashing;
import com.megapreneur.urlshortener.dtos.GenerateUrlRequest;
import com.megapreneur.urlshortener.dtos.GenerateUrlResponse;
import com.megapreneur.urlshortener.exceptions.UrlNotFoundException;
import com.megapreneur.urlshortener.exceptions.UrlShortenerException;
import com.megapreneur.urlshortener.models.Urls;
import com.megapreneur.urlshortener.repositories.UrlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.megapreneur.urlshortener.validation.ValidateUrl.isValidUrl;

@Service
public class UrlsServiceImpl implements UrlsService{
    @Autowired
    private UrlsRepository urlRepository;
    @Override
    public GenerateUrlResponse generateShortUrl(GenerateUrlRequest request) throws UrlShortenerException {
        if (isValidUrl(request.getUrl())){
            String encodedUrl = encodeUrl(request.getUrl());
            Urls url = Urls.builder()
                    .originalUrl(request.getUrl())
                    .shortenedUrl(encodedUrl)
                    .dateCreated(LocalDateTime.now())
                    .build();
            urlRepository.save(url);

            return getGenerateUrlResponse(url);
        }
        throw  new UrlShortenerException("Invalid url!");
    }

    private static GenerateUrlResponse getGenerateUrlResponse(Urls url) {
        GenerateUrlResponse response = new GenerateUrlResponse();
        response.setMessage("Your new url is " + url.getShortenedUrl());
        return response;
    }

    @Override
    public String updateShortenUrl(String url, String customUrl) {
        Urls savedUrl = urlRepository.findByShortenedUrl(url).orElseThrow(() -> new UrlNotFoundException("Url not found!"));
        if(!isValidUrl(customUrl)) {
            throw new UrlShortenerException("Invalid Url");
        }
        savedUrl.setShortenedUrl(customUrl);
        urlRepository.save(savedUrl);
        return savedUrl.getShortenedUrl();
    }

    @Override
    public void deleteUrlUsingShortenedUrl(String shortenedUrl) {
        Urls savedUrl = urlRepository.findByShortenedUrl(shortenedUrl).orElseThrow(() ->new UrlNotFoundException("Url not found"));
        urlRepository.delete(savedUrl);
    }

    @Override
    public void deleteUrlUsingOriginalUrl(String originalUrl) {
        Urls url = urlRepository.findByOriginalUrl(originalUrl).orElseThrow(() -> new UrlNotFoundException("Url not found"));
        urlRepository.delete(url);
    }

    @Override
    public String getDecodedUrl(String shortenedUrl) {
        Urls url = urlRepository.findByShortenedUrl(shortenedUrl).orElseThrow(() -> new UrlNotFoundException("Url not found"));
        return url.getOriginalUrl();
    }

    @Override
    public String getEncodedUrl(String originalUrl) {
        Urls url = urlRepository.findByOriginalUrl(originalUrl).orElseThrow(() -> new UrlNotFoundException("Url not found"));
        return url.getShortenedUrl();
    }

    private String encodeUrl(String url){
        LocalDateTime time = LocalDateTime.now();
        return Hashing.murmur3_32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
    }

}
