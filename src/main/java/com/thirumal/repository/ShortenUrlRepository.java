/**
 * 
 */
package com.thirumal.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.thirumal.model.ShortenUrl;

/**
 * @author Thirumal
 *
 */
@Repository
public interface ShortenUrlRepository extends ReactiveCrudRepository<ShortenUrl, Long> {

}
