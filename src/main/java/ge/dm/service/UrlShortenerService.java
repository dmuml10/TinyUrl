package ge.dm.service;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.random.RandomGenerator;

public class UrlShortenerService {

    private final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    public String shortenUrl(String url) {

        long salt = randomGenerator.nextLong();

        String urlSalt = url + salt;

        String hash = Hashing.sha256().hashString(urlSalt, StandardCharsets.UTF_8).toString();

        logger.info("Generated hash: {} for URL: {}", hash, url);
        return hash.substring(0, 6);
    }

    public String getOriginalUrl(String urlHash) {
        return null;
    }

}
