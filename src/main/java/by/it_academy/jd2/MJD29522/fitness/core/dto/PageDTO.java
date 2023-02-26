package by.it_academy.jd2.MJD29522.fitness.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

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

    public PageDTO() {
    }

    public PageDTO(int number, int size, int totalPages,
                   long totalElements, boolean first, int numberOfElements,
                   boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberOfElements=" + numberOfElements +
                ", last=" + last +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDTO<?> pageDTO = (PageDTO<?>) o;
        return number == pageDTO.number && size == pageDTO.size && totalPages == pageDTO.totalPages && totalElements == pageDTO.totalElements && first == pageDTO.first && numberOfElements == pageDTO.numberOfElements && last == pageDTO.last && Objects.equals(content, pageDTO.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalPages, totalElements, first, numberOfElements, last, content);
    }
}

