package com.shortener.urlshortener;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UrlShortener shortener = new UrlShortener();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the URL Shortener!");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Retrieve original URL from shortened URL");
            System.out.println("Type 'quit' or 'q' to exit.");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                System.out.println("Exiting. Goodbye!");
                break;
            }

            switch (input) {
                case "1":
                    System.out.print("Enter the URL to shorten: ");
                    String originalUrl = scanner.nextLine().trim();
                    String shortUrl = shortener.shortenUrl(originalUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case "2":
                    System.out.print("Enter the shortened URL: ");
                    String inputShortUrl = scanner.nextLine().trim();
                    String original = shortener.getOriginalUrlFromShortened(inputShortUrl);
                    System.out.println("Original URL: " + original);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
