/**
 * 
 */
package com.thirumal.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirumal.mapper.JsonToMapConverter;
import com.thirumal.mapper.MapToJsonConverter;

import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;

/**
 * @author Thirumal
 *
 */
@Configuration
@AllArgsConstructor
@EnableR2dbcRepositories("com.thirumal.model.Click")
public class ReactivePostgresConfig extends AbstractR2dbcConfiguration {
	
	private final ObjectMapper objectMapper;
	  
	/**
	 * JSON to MAP & Map - JSON conversion is added
	 */
	@Bean
	@Override
	public R2dbcCustomConversions r2dbcCustomConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new JsonToMapConverter(objectMapper));
		converters.add(new MapToJsonConverter(objectMapper));
		return new R2dbcCustomConversions(getStoreConversions(), converters);
	}

	@Override
	public ConnectionFactory connectionFactory() {
		return null;// No need to set the connection factory
	}

}
