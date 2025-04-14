package com.shortener.urlshortener;

import java.util.HashMap;
import java.util.Map;

public class UrlShortener {
    private static final String BASE_URL = "http://sho.rt/";
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final Map<String, String> longToShort = new HashMap<>();
    private final Map<String, String> shortToLong = new HashMap<>();

    public String shortenUrl(String originalUrl) {
        if (longToShort.containsKey(originalUrl)) {
            return longToShort.get(originalUrl);
        }

        long hashCode = Math.abs((long) originalUrl.hashCode());
        String shortKey = encodeBase62(hashCode);
        String shortUrl = BASE_URL + shortKey;

        longToShort.put(originalUrl, shortUrl);
        shortToLong.put(shortUrl, originalUrl);

        return shortUrl;
    }

    public String getOriginalUrlFromShortened(String shortUrl) {
        return shortToLong.getOrDefault(shortUrl, "URL not found!");
    }

    private String encodeBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        while (sb.length() < 5) {
            sb.append('0'); // pad to ensure 5-character output
        }
        return sb.reverse().toString();
    }
}
