/**
 * 
 */
package com.thirumal.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.thirumal.exception.BadRequestException;
import com.thirumal.exception.DatabaseException;
import com.thirumal.model.ShortenUrl;
import com.thirumal.repository.ShortenUrlRepository;

/**
 * @author Thirumal
 *
 */
@Service
public class UrlShortnerService {

	Logger logger = LoggerFactory.getLogger(UrlShortnerService.class);
	
	@Value("${redirect.default-url}")
	String defaultRedirectUrl;
	
	@Autowired
	private ShortenUrlRepository shortenUrlRepository;
	
	/**
	 * Convert long URL to short URL and store the information in the database
	 * @param shortenUrl
	 * @return
	 */
	public ShortenUrl createShortUrl(ShortenUrl shortenUrl) {
		logger.debug("Creating short URL for {}", shortenUrl);
		try {
			isValidURL(shortenUrl.getOriginalUrl());
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
			throw new BadRequestException("URL is not valid");
		}
		shortenUrl.setShortUrl(shortenUrl.getShortUrlHostAndProtocol()); 
		shortenUrl = shortenUrlRepository.save(shortenUrl).block();
		if (shortenUrl == null) {
			logger.error("Not able to insert to database {}", shortenUrl);
			throw new DatabaseException("Not able to save the URL to database");
		}
		int pk = shortenUrl.getShortenUrlId().intValue();
		String shortUrl = shortenUrl.getShortUrl() + "/" + idToShortURL(pk);
		shortenUrl.setShortUrl(shortUrl);
		shortenUrl = shortenUrlRepository.save(shortenUrl).block();
		logger.debug("The shorent URL is {}", shortenUrl);
		return shortenUrl;
	}
	
	/**
	 * Generate primary key from the path and redirect with original URL
	 * @param shortPath
	 * @return Original URL in redirect status 302
	 */
	public RedirectView getRedirectUrl(String shortPath) {
		logger.debug("Short path is {}", shortPath);
		int primaryKey = shortURLtoId(shortPath);
		logger.debug("Primary key for the short path is {}", primaryKey);
		ShortenUrl shortenUrl = shortenUrlRepository.findById(Long.valueOf(primaryKey)).block();
		RedirectView redirectView = new RedirectView();
		if (shortenUrl == null) {
			redirectView.setUrl(defaultRedirectUrl);
		} else {
			redirectView.setUrl(shortenUrl.getOriginalUrl());
		}
		return redirectView;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public RedirectView getRedirectUrl(HttpHeaders request) {
		logger.debug("Request {}", request);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(defaultRedirectUrl);
		return redirectView;
	}
	
	/**
	 * Function to generate a short URL from integer ID
	 * @param interger id / primary key
	 * @return short URL
	 */ 
    private String idToShortURL(int n) { 
        // Map to store 62 possible characters 
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();       
        StringBuilder shorturl = new StringBuilder();       
        // Convert given integer id to a base 62 number 
        while (n > 0) { 
            // use above map to store actual character 
            // in short url 
            shorturl.append(map[n % 62]);
            n = n / 62; 
        }       
        // Reverse shortURL to complete base conversion 
        return shorturl.reverse().toString(); 
    } 
    
    
    /**
     * Function to get integer ID back from a short URL
     * @param shortURL
     * @return primaryKey
     */ 
    private int shortURLtoId(String shortURL) { 
        int id = 0; // initialize result       
        // A simple base conversion logic 
        for (int i = 0; i < shortURL.length(); i++) { 
            if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z') { 
            	id = id * 62 + shortURL.charAt(i) - 'a'; 
            }
            if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z') { 
            	id = id * 62 + shortURL.charAt(i) - 'A' + 26; 
            }
            if ('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9') { 
            	id = id * 62 + shortURL.charAt(i) - '0' + 52;
            }
        } 
        return id; 
    }

    /**
     * Validate the given URL is valid or not
     * @param url
     * @return true, if the URL is valid
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    boolean isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

}
