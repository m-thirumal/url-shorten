/**
 * 
 */
package com.thirumal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("public.click")
public class Click implements Serializable {

	private static final long serialVersionUID = 8282140525976896894L;

	@Id
	@Column("click_id")
	private Long clickId;
	@Column("shorten_url_id")
	private Long shortenUrlId; 
	@Column("client_details")
	private Map<String, Object> clientDetails;
	@Column("row_created_on") // This column is an alias of `Clicked On`
	private LocalDateTime rowCreatedOn;
}
