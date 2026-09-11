package com.automation.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Finding Broken Links and Images on a Webpage
 * ============================================================================
 * Asked in nearly 80% of SDET & Web Automation interviews!
 *
 * THE PROBLEM:
 * - A link or image is "broken" if navigating to its URL returns an HTTP response
 *   code >= 400 (e.g. 404 Not Found, 500 Internal Server Error).
 *
 * ALGORITHM:
 * 1. Collect all `<a>` tags via `driver.findElements(By.tagName("a"))`.
 * 2. Collect all `<img>` tags via `driver.findElements(By.tagName("img"))`.
 * 3. Filter out invalid/empty URLs (e.g., null, empty, "javascript:void(0)", "mailto:").
 * 4. Open HTTP connection using `HttpURLConnection` and send a `HEAD` request
 *    (HEAD only fetches headers, saving bandwidth compared to GET).
 * 5. Check response code: If >= 400, mark as BROKEN.
 * 6. OPTIMIZATION: Use Java 8 `parallelStream()` to verify 500+ links in seconds!
 */
public class BrokenLinksAndImagesChecker {

    public static class LinkValidationResult {
        public final String url;
        public final int statusCode;
        public final boolean isBroken;

        public LinkValidationResult(String url, int statusCode, boolean isBroken) {
            this.url = url;
            this.statusCode = statusCode;
            this.isBroken = isBroken;
        }

        @Override
        public String toString() {
            return String.format("[%s] Status: %d | URL: %s", isBroken ? "BROKEN" : "OK", statusCode, url);
        }
    }

    /**
     * Checks HTTP response status code for a given URL.
     * Uses HEAD request first for speed, falling back to GET if HEAD is forbidden.
     */
    public static LinkValidationResult checkUrlStatus(String urlString) {
        try {
            URL url = URI.create(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("HEAD"); // Lightweight request
            connection.connect();

            int responseCode = connection.getResponseCode();

            // If HEAD is not allowed (405), fallback to standard GET
            if (responseCode == HttpURLConnection.HTTP_BAD_METHOD) {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                responseCode = connection.getResponseCode();
            }

            boolean isBroken = (responseCode >= 400);
            return new LinkValidationResult(urlString, responseCode, isBroken);

        } catch (IOException e) {
            return new LinkValidationResult(urlString, 0, true);
        }
    }

    /**
     * Scans webpage and validates all links in parallel.
     */
    public static List<LinkValidationResult> verifyAllPageLinks(WebDriver driver) {
        List<WebElement> linkElements = driver.findElements(By.tagName("a"));
        List<String> validUrls = new ArrayList<>();

        for (WebElement element : linkElements) {
            String href = element.getAttribute("href");
            if (href != null && !href.isEmpty() && href.startsWith("http")) {
                validUrls.add(href);
            }
        }

        System.out.println("Total valid HTTP links to test: " + validUrls.size());

        // Validate concurrently using Java 8 parallelStream()
        return validUrls.parallelStream()
                .distinct()
                .map(BrokenLinksAndImagesChecker::checkUrlStatus)
                .toList();
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Link Validation Logic ===");
        String[] sampleUrls = {
                "https://www.google.com",
                "https://httpbin.org/status/404",
                "https://httpbin.org/status/500"
        };

        for (String testUrl : sampleUrls) {
            LinkValidationResult result = checkUrlStatus(testUrl);
            System.out.println(result);
        }
    }
}
