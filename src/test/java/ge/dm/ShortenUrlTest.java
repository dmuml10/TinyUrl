package ge.dm;

import ge.dm.service.UrlShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShortenUrlTest {


    @Test
    public void shortenUrl() {
        UrlShortenerService uss = new UrlShortenerService();

        String url = "facebook.com";
        String hash = uss.shortenUrl(url);
        Assertions.assertEquals(6, hash.length());
    }

    @Test
    public void shortenSameUrls() {
        UrlShortenerService uss = new UrlShortenerService();
        String url = "facebook.com";
        String hash1 = uss.shortenUrl(url);
        String hash2 = uss.shortenUrl(url);
        Assertions.assertNotEquals(hash1, hash2);
    }

}
