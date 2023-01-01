package com.megapreneur.urlshortener.repositories;

import com.megapreneur.urlshortener.models.Urls;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UrlsRepository extends MongoRepository<Urls, String> {

    Optional<Urls> findByShortenedUrl(String url);
}
