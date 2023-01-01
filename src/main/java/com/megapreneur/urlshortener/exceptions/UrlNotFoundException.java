package com.megapreneur.urlshortener.exceptions;

public class UrlNotFoundException extends UrlShortenerException {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
