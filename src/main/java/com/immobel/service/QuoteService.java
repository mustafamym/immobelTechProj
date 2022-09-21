package com.immobel.service;


import com.immobel.dto.QuoteDto;
import com.immobel.dto.QuoteSaveDto;
import com.immobel.model.Quote;
import org.springframework.data.domain.Page;

public interface QuoteService {

    QuoteDto createQuote(QuoteSaveDto quoteSaveDto);

    QuoteDto updateQuoteDto(QuoteSaveDto quoteSaveDto, Long quoteId);

    QuoteDto getQuoteById(Long quouteId);

    Page<Quote> getQuotes(Integer currentPage, Integer pageSize,
                          String sortedBy);

    void deleteQuoteById(Long quoteId);

}
