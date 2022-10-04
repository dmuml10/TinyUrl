package ge.dm.service;

import com.google.common.hash.Hashing;
import ge.dm.dao.interfaces.UrlShortenerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.random.RandomGenerator;

public class UrlShortenerService {

    private final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    private UrlShortenerDao urlShortenerDao;

    @Autowired
    public UrlShortenerService(UrlShortenerDao urlShortenerDao) {
        this.urlShortenerDao = urlShortenerDao;
    }

    public String shortenUrl(String url, Optional<String> alias) {

        if (alias.isPresent()) {
            try {
                urlShortenerDao.storeShortenedUrl(url, alias.get());
            } catch (Exception ex) {
                logger.warn("Unable to store shortened URL for: {} {}", url, ex);
                return null;
            }
            return alias.get();
        } else {


            long salt = randomGenerator.nextLong();

            String urlSalt = url + salt;

            String hash = Hashing.sha256().hashString(urlSalt, StandardCharsets.UTF_8).toString();

            String shortenedHash = hash.substring(0, 6);
            logger.info("Generated hash: {} for URL: {}", shortenedHash, url);

            try {
                urlShortenerDao.storeShortenedUrl(url, shortenedHash);
            } catch (Exception ex) {
                logger.warn("Unable to store shortened URL for: {} {}", url, ex);
                return null;
            }
            return shortenedHash;
        }
    }

    public String getOriginalUrl(String urlHash) {
        try {
            return urlShortenerDao.getOriginalUrl(urlHash);
        } catch (Exception ex) {
            logger.warn("Unable to get URL for hash: {} {}", urlHash, ex);
            return null;
        }
    }

}
