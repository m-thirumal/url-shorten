/**
 * 
 */
package com.thirumal.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.thirumal.model.Click;

/**
 * @author Thirumal
 *
 */
@Repository
public interface ClickRepository extends ReactiveCrudRepository<Click, Long> {

}
