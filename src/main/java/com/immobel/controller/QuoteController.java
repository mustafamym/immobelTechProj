package com.immobel.controller;

import com.immobel.dto.QuoteDto;
import com.immobel.dto.QuoteSaveDto;
import com.immobel.model.Quote;
import com.immobel.service.QuoteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "Quotes")
@RequestMapping(path = "/api", produces = "application/json")
@Slf4j
@Validated
@RequiredArgsConstructor

public class QuoteController {

	private final QuoteService quoteService;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "create quote", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = QuoteDto.class))}),
			@ApiResponse(responseCode = "409", description = "", content = @Content),
			@ApiResponse(responseCode = "500", description = "", content = @Content)})

	@PostMapping("/quotes")
	public ResponseEntity<QuoteDto> createQuote(@RequestBody @Valid QuoteSaveDto quoteSaveDto) {

		QuoteDto quoteDto = quoteService.createQuote(quoteSaveDto);
		return new ResponseEntity<>(quoteDto, HttpStatus.CREATED);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "update quote", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = QuoteDto.class))}),
			@ApiResponse(responseCode = "409", description = "", content = @Content),
			@ApiResponse(responseCode = "500", description = "", content = @Content)})

	@PutMapping("/quotes/{quoteId}")
	public ResponseEntity<Object> updateQuote(@RequestBody QuoteSaveDto quoteSaveDto,
											  @PathVariable Long quoteId) {

		QuoteDto quoteDto = quoteService.updateQuoteDto(quoteSaveDto, quoteId);
		return new ResponseEntity<>(quoteDto, HttpStatus.CREATED);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Quote list retrieved", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error.", content =
			@Content)})
	@GetMapping("/quotes")
	public ResponseEntity<Object> getPageableQuote(
			@RequestParam(value = "currentPage", required = false,
					defaultValue = "0") Integer currentPage,
			@RequestParam(value = "pageSize", required = false,
					defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sortedBy", required = false) String sortedBy) {

		Page<Quote> billGroupsDto = quoteService.getQuotes(currentPage, pageSize, sortedBy);
		return new ResponseEntity<>(billGroupsDto, HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Quote list retrieved", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error.", content =
			@Content)})
	@GetMapping("/quotes/{quoteId}")
	public ResponseEntity<Object> getPageableQuoteById(@PathVariable Long quoteId) {

		QuoteDto quoteDto = quoteService.getQuoteById(quoteId);
		return new ResponseEntity<>(quoteDto, HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "delete quote", content =
			@Content),
			@ApiResponse(responseCode = "500", description = "internal error", content =
			@Content)})
	@DeleteMapping("/quotes/{quoteId}")
	public ResponseEntity<Object> deleteQuoteById(@PathVariable Long quoteId) {

		quoteService.deleteQuoteById(quoteId);

		return new ResponseEntity<>("Quote delete sucess", HttpStatus.OK);


	}
}
