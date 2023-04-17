/**
 * 
 */
package com.thirumal.mapper;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.r2dbc.postgresql.codec.Json;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thirumal
 *
 */
@Slf4j
@ReadingConverter
@AllArgsConstructor
public class JsonToMapConverter implements Converter<Json, HashMap<String, Object>> {

	private final ObjectMapper objectMapper;

	@Override
	public HashMap<String, Object> convert(Json json) {
		try {
			return objectMapper.readValue(json.asString(), new TypeReference<>() {
			});
		} catch (IOException e) {
			log.error("Problem while parsing JSON: {}", json, e);
		}
		return new HashMap<>();
	}
    
}
