package com.immobel;

import com.immobel.dto.QuoteSaveDto;
import com.immobel.model.Quote;
import com.immobel.repository.QuoteRepository;
import com.immobel.service.QuoteService;
import com.immobel.service.QuoteServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {
        QuoteServiceImp.class, QuoteRepository.class, ModelMapper.class})
public class QuoteServiceTest {

    @MockBean
    QuoteRepository quoteRepository;

    @Autowired
    QuoteService quoteService;

    @Test
    @DisplayName("quote Save methods test")
    void testAttachPaymentMethod() {
        QuoteSaveDto quoteSaveDto = new QuoteSaveDto();

        quoteSaveDto.setAuthor("ames gosselin");
        quoteSaveDto.setText("Java");

        Quote entity = new Quote();
        entity.setId(14l);
        entity.setAuthor("ames gosselin");
        entity.setText("Java");
        Mockito.when(quoteRepository.save(Mockito.any(Quote.class))).thenReturn(entity);
        quoteService.createQuote(quoteSaveDto);
        Mockito.verify(quoteRepository, Mockito.times(1)).save(Mockito.any(Quote.class));

    }
}
