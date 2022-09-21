package com.immobel.repository;

import com.immobel.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
