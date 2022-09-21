package com.immobel.service;

import com.immobel.dto.QuoteDto;
import com.immobel.dto.QuoteSaveDto;
import com.immobel.model.Quote;
import com.immobel.repository.QuoteRepository;
import com.immobel.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImp implements QuoteService {

    private final QuoteRepository quoteRepository;

    private final ModelMapper modelMapper;

    @Override
    public QuoteDto createQuote(QuoteSaveDto quoteSaveDto) {
        Quote quote = modelMapper.map(quoteSaveDto, Quote.class);

        quoteRepository.save(quote);
        return modelMapper.map(quote, QuoteDto.class);

    }

    @Override
    public QuoteDto updateQuoteDto(QuoteSaveDto quoteSaveDto, Long quoteId) {
        // here we can Throw custom design error not found exception
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(null);

        quote.setAuthor(quoteSaveDto.getAuthor());
        quote.setText(quoteSaveDto.getText());
        quoteRepository.save(quote);
        return modelMapper.map(quote, QuoteDto.class);
    }

    @Override
    public QuoteDto getQuoteById(Long quoteId) {
        // here we can Throw custom design error not found exception
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(null);
        return modelMapper.map(quote, QuoteDto.class);
    }

    @Override
    public Page<Quote> getQuotes(Integer currentPage, Integer pageSize,
                                 String sortedBy) {

        Page<Quote> quotes = quoteRepository.findAll(AppUtils.getPageableInfo(currentPage, pageSize, sortedBy));
        return quotes;
    }

    @Override
    public void deleteQuoteById(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }
}
