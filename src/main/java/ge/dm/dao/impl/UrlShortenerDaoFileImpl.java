package ge.dm.dao.impl;

import ge.dm.dao.interfaces.UrlShortenerDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UrlShortenerDaoFileImpl implements UrlShortenerDao {

    private BufferedWriter bw;

    private BufferedReader br;

    private static final String DELIMITER = ",";

    private static final String FILE_NAME = "Urls.txt";

    public UrlShortenerDaoFileImpl() throws IOException {
        bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
        br = new BufferedReader(new FileReader(FILE_NAME));
    }

    @Override
    public void storeShortenedUrl(String url, String hash) throws IOException {
        bw.write(hash);
        bw.write(DELIMITER);
        bw.write(hash);
        bw.newLine();
    }

    @Override
    public String getOriginalUrl(String hash) throws IOException {
        String line = br.readLine();
        while (line != null) {
            String[] urlData = line.split(DELIMITER);
            if (urlData[0].equals(hash)) {
                return urlData[1];
            }
            line = br.readLine();
        }
        return null;
    }
}
