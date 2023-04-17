/**
 * 
 */
package com.thirumal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.thirumal.model.ShortenUrl;
import com.thirumal.service.UrlShortnerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping()
public class UrlShortnerController {
	
	Logger logger = LoggerFactory.getLogger(UrlShortnerController.class);
	
	@Autowired
	private UrlShortnerService urlShortnerService;

	@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	public ShortenUrl createShortUrl(@Validated @RequestBody ShortenUrl shortenUrl,
			@RequestHeader(value = "UserAcceptLanguage", defaultValue = "en-IN") String locale) {
		logger.debug("Creating the short URL for {}", shortenUrl);
		return urlShortnerService.createShortUrl(shortenUrl);
	}
	
	@GetMapping(value = "/")
    public RedirectView defaultRedirectUrl(@RequestHeader HttpHeaders headers) {
		logger.debug("Redirecting Empty URL {}", headers);       
        return urlShortnerService.getRedirectUrl(headers);
    }
	
	@GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Received shortened url to redirect: {}", id);       
        return urlShortnerService.getRedirectUrl(id);
    }
	
}
