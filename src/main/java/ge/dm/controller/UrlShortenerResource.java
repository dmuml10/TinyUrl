package ge.dm.controller;


import ge.dm.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UrlShortenerResource {


    private UrlShortenerService urlShortenerService;
    @Autowired
    public UrlShortenerResource(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/")
    String getUrl(@PathVariable String urlHash) {
        return urlShortenerService.getOriginalUrl(urlHash);
    }

    @PostMapping("/shorten")
    String shortenUrl(@PathVariable String url, @PathVariable Optional<String> alias) {
        return urlShortenerService.shortenUrl(url, alias);
    }
}
