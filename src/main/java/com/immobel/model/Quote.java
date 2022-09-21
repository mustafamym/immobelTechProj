package com.immobel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@Entity(name ="Quotes")
public class Quote extends BaseEntity<Long>{

    @GeneratedValue
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    private String text;

    private  String author;
}
