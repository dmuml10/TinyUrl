package ge.dm;

import ge.dm.dao.impl.UrlShortenerDaoFileImpl;
import ge.dm.dao.interfaces.UrlShortenerDao;
import ge.dm.service.UrlShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

public class ShortenUrlTest {

    @Mock
    private static UrlShortenerDao urlShortenerDao;

    @BeforeAll
    public static void initialize() {
        try {
            urlShortenerDao = new UrlShortenerDaoFileImpl();
        } catch (Exception ex) {
            //Ignore
        }
    }

    @Test
    public void shortenUrl() {
        UrlShortenerService uss = new UrlShortenerService(urlShortenerDao);

        String url = "facebook.com";
        String hash = uss.shortenUrl(url);
        Assertions.assertEquals(6, hash.length());
    }

    @Test
    public void shortenSameUrls() {
        UrlShortenerService uss = new UrlShortenerService(urlShortenerDao);
        String url = "facebook.com";
        String hash1 = uss.shortenUrl(url);
        String hash2 = uss.shortenUrl(url);
        Assertions.assertNotEquals(hash1, hash2);
    }

}
