package ge.dm.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.random.RandomGenerator;

public class UrlShortenerService {

    private RandomGenerator randomGenerator = RandomGenerator.getDefault();

    public String shortenUrl(String url) {

        long salt = randomGenerator.nextLong();

        String urlSalt = url + salt;

        String hash = Hashing.sha256().hashString(urlSalt, StandardCharsets.UTF_8).toString();

        System.out.println("Hash: " + hash);
        return hash.substring(0, 6);
    }

    public String getOriginalUrl(String urlHash) {
        return null;
    }

}
