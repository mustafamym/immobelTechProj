package com.immobel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteSaveDto {

    @NotNull(message = "Text can't be empty")
    private String text;

    @NotNull(message = "Author name can't be empty")
    private String author;
}
