package com.example.ebshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookSearch {
    private String isbn;
    private String name;
    private boolean status;
    private String authorISBN;
    private String publisherISBN;
    private BigDecimal firstPrice;
    private BigDecimal lastPrice;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAuthorISBN() {
        return authorISBN;
    }

    public void setAuthorISBN(String authorISBN) {
        this.authorISBN = authorISBN;
    }

    public String getPublisherISBN() {
        return publisherISBN;
    }

    public void setPublisherISBN(String publisherISBN) {
        this.publisherISBN = publisherISBN;
    }

    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }
}
