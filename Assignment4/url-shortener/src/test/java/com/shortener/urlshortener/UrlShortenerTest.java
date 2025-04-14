package com.shortener.urlshortener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlShortenerTest {
    private UrlShortener urlShortener;

    @BeforeEach
    public void setUp() {
        urlShortener = new UrlShortener();
    }

    // Test 1: General Test - Shortening a URL
    @Test
    public void testShortenUrl() {
        String originalUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String shortUrl = urlShortener.shortenUrl(originalUrl);

        assertNotNull(shortUrl);
        assertTrue(shortUrl.startsWith("http://sho.rt/"));
    }

    // Test 2: General Test - Retrieving Original URL from Shortened URL
    @Test
    public void testGetOriginalUrlFromShortened() {
        String originalUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String shortUrl = urlShortener.shortenUrl(originalUrl);

        String retrievedUrl = urlShortener.getOriginalUrlFromShortened(shortUrl);
        assertEquals(originalUrl, retrievedUrl);
    }

    // Test 3: General Test - Shortening the same URL again (should return the same shortened URL)
    @Test
    public void testSameUrlReturnsSameShortenedUrl() {
        String originalUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String shortUrl1 = urlShortener.shortenUrl(originalUrl);
        String shortUrl2 = urlShortener.shortenUrl(originalUrl);

        assertEquals(shortUrl1, shortUrl2);
    }

    // Test 4: General Test - Trying to retrieve an invalid shortened URL
    @Test
    public void testInvalidShortenedUrl() {
        String originalUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String shortUrl = urlShortener.shortenUrl(originalUrl);

        String invalidShortUrl = "http://sho.rt/invalid";
        String retrievedUrl = urlShortener.getOriginalUrlFromShortened(invalidShortUrl);

        assertEquals("URL not found!", retrievedUrl);
    }

    // Test 5: Performance Test - Shortening and retrieving multiple URLs (simple performance check)
    @Test
    public void testPerformance() {
        int[] testSizes = {100, 500, 1000, 2000, 5000};

        System.out.println("Performance Test Results:");
        System.out.println("+------------------+--------------------------+");
        System.out.println("| Number of URLs   | Time Taken (milliseconds)|");
        System.out.println("+------------------+--------------------------+");

        for (int size : testSizes) {
            long startTime = System.nanoTime();

            for (int i = 0; i < size; i++) {
                String originalUrl = "https://example.com/page" + i;
                String shortUrl = urlShortener.shortenUrl(originalUrl);
                String retrievedUrl = urlShortener.getOriginalUrlFromShortened(shortUrl);
                assertEquals(originalUrl, retrievedUrl);
            }

            long endTime = System.nanoTime();
            long durationMillis = (endTime - startTime) / 1_000_000;

            System.out.printf("| %-16d | %-24d |\n", size, durationMillis);
        }

        System.out.println("+------------------+--------------------------+");
    }


    // Test 6: Edge Case - Handling an empty URL
    @Test
    public void testEmptyUrl() {
        String shortUrl = urlShortener.shortenUrl("");
        assertNotNull(shortUrl);
        assertTrue(shortUrl.startsWith("http://sho.rt/"));
    }

    // Test 7: Edge Case - Handling very long URL
    @Test
    public void testLongUrl() {
        String longUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ" +
                         "/very-long-path/with-several-segments-and-parameters";
        String shortUrl = urlShortener.shortenUrl(longUrl);
        
        assertNotNull(shortUrl);
        assertTrue(shortUrl.startsWith("http://sho.rt/"));
    }
}
