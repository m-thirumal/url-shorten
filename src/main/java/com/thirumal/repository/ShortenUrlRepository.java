/**
 * 
 */
package com.thirumal.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.thirumal.model.ShortenUrl;

import reactor.core.publisher.Mono;

/**
 * @author Thirumal
 *
 */
@Repository
public interface ShortenUrlRepository extends ReactiveCrudRepository<ShortenUrl, Long> {

	Mono<ShortenUrl> findById(Long id);
}
