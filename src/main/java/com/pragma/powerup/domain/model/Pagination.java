package com.pragma.powerup.domain.model;

import java.util.List;

public class Pagination<T> {
    private List<T> content;
    private Long totalElements;
    private int totalPages;
    private int currentPage;
    private boolean ascending;
    private boolean empty;

    public Pagination(boolean ascending, int currentPage, int totalPages, Long totalElements, List<T> content) {

        this.ascending = ascending;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
        this.empty = content.isEmpty();
    }

    public Pagination() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}