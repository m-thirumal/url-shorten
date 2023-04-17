/**
 * 
 */
package com.thirumal.mapper;

import java.util.HashMap;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.r2dbc.postgresql.codec.Json;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thirumal
 *
 */
@Slf4j
@WritingConverter
@AllArgsConstructor
public class MapToJsonConverter implements Converter<HashMap<String, Object>, Json> {

	private final ObjectMapper objectMapper;
	 
	@Override
	public Json convert(HashMap<String, Object> source) {
		try {
			return Json.of(objectMapper.writeValueAsString(source));
		} catch (JsonProcessingException e) {
			log.error("Error occurred while serializing map to JSON: {}", source, e);
		}
		return Json.of("");
	}

}
