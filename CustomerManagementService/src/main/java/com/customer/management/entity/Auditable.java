package com.customer.management.entity;

import static jakarta.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	@CreatedDate
	@JsonProperty("created_on")
	protected Date createdOn;

	@CreatedBy
	protected String createdBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date modifiedOn;

	@LastModifiedBy
	protected String modifiedBy;

}
