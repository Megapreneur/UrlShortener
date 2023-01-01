package com.megapreneur.urlshortener.exceptions;

public class UrlShortenerException extends IllegalArgumentException {
    public UrlShortenerException(String message){
        super(message);
    }
}
