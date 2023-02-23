package by.it_academy.jd2.MJD29522.fitness.core.dto;

public class Page {

    private int number = 0;
    private int size = 20;

    private int total_pages;
    private int total_elements;
    private boolean first;
    private int number_of_elements;
    private boolean last;
    private UserCreateDTO userCreateDTO;

    public Page() {
    }

    public Page(int number, int size, int total_pages, int total_elements,
                boolean first, int number_of_elements, boolean last, UserCreateDTO userCreateDTO) {
        this.number = number;
        this.size = size;
        this.total_pages = total_pages;
        this.total_elements = total_elements;
        this.first = first;
        this.number_of_elements = number_of_elements;
        this.last = last;
        this.userCreateDTO = userCreateDTO;
    }

    public Page(int number, int size, int total_pages, int total_elements,
                boolean first, int number_of_elements, boolean last) {
        this.number = number;
        this.size = size;
        this.total_pages = total_pages;
        this.total_elements = total_elements;
        this.first = first;
        this.number_of_elements = number_of_elements;
        this.last = last;
    }

    public UserCreateDTO getUserCreateDTO() {
        return userCreateDTO;
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

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_elements() {
        return total_elements;
    }

    public void setTotal_elements(int total_elements) {
        this.total_elements = total_elements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumber_of_elements() {
        return number_of_elements;
    }

    public void setNumber_of_elements(int number_of_elements) {
        this.number_of_elements = number_of_elements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}

