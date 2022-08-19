package ge.dm.dao.interfaces;

import java.io.IOException;

public interface UrlShortenerDao {

    void storeShortenedUrl(String url, String hash) throws IOException;

    String getOriginalUrl(String hash) throws IOException;
}
