package com.shorturl.shorturl.modals;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name="url")
public class Url implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="url_id_seq_generator")
	@SequenceGenerator(name="url_id_seq_generator", sequenceName="url_id_seq", allocationSize=1)
	private Long id;
	
	@Column(name="short_url", unique=true, nullable=false)
	private String shortUrl;
	
	@Column(name="long_url", nullable=false, unique=true)
	private String longUrl;
	
	@Column(name="createdby")
	private String createdBy;
	
	@Column(name="createdon")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

}
