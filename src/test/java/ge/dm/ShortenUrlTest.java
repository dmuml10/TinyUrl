package ge.dm;

import ge.dm.dao.impl.UrlShortenerDaoFileImpl;
import ge.dm.dao.interfaces.UrlShortenerDao;
import ge.dm.service.UrlShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

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
        String hash = uss.shortenUrl(url, Optional.empty());
        Assertions.assertEquals(6, hash.length());
    }

    @Test
    public void shortenSameUrls() {
        UrlShortenerService uss = new UrlShortenerService(urlShortenerDao);
        String url = "facebook.com";
        String hash1 = uss.shortenUrl(url, Optional.empty());
        String hash2 = uss.shortenUrl(url, Optional.empty());
        Assertions.assertNotEquals(hash1, hash2);
    }

    @Test
    public void shortenWithAlias() {
        var alias = Optional.of("fb");
        UrlShortenerService uss = new UrlShortenerService(urlShortenerDao);
        String url = "facebook.com";
        String hash = uss.shortenUrl(url, alias);
        Assertions.assertEquals(alias.get(), hash);
    }

}
