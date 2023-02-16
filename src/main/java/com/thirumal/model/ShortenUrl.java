/**
 * 
 */
package com.thirumal.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thirumal
 *
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder@ToString
@Table("public.shorten_url")
public class ShortenUrl implements Serializable {

	private static final long serialVersionUID = 2090525162664731853L;
	
	@Id
	@Column("shorten_url_id")
	private Long shortenUrlId;
	@Column("original_url")
	private String originalUrl;
	@NotNull@Transient
	private String shortUrlHostAndProtocol;
	@Column("short_url")
	private String shortUrl;
	@Column("row_created_on")@Transient
	private LocalDateTime rowCreatedOn;
	@Column("expire_on")
	private LocalDateTime expireOn;

}
