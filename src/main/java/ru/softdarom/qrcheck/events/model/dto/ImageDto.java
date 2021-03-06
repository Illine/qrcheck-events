package ru.softdarom.qrcheck.events.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {

    @JsonProperty("imageId")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("format")
    private String format;

}