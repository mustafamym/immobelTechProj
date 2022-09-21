package com.immobel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected Long createdBy;

    @Column(name = "create_date", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime createDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected Long lastModifiedBy;

    @Column(name = "modify_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime modifyDate;

}
