package by.it_academy.jd2.MJD29522.fitness.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO<T> {

    @JsonProperty("number")
    private int number = 0;
    @JsonProperty("size")
    private int size = 20;
    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

    @JsonProperty("first")
    private boolean first;

    @JsonProperty("number_of_elements")
    private int numberOfElements;

    @JsonProperty("last")
    private boolean last;

    @JsonProperty("content")
    List<T> content;

}

