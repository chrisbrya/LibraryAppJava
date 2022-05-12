package com.library.system.Model;

import java.util.Date;

public class Book {
    private String ibn;
    private String bookName;
    private String author;
    private Date publishDate;
    private int pages;
    private boolean isAvailable;

    public Book(){}

    public Book(String ibn, String bookName, String author, Date publishDate, boolean isAvailable, int pages) {
        this.ibn = ibn;
        this.bookName = bookName;
        this.author = author;
        this.publishDate = publishDate;
        this.pages = pages;
        this.isAvailable = isAvailable;
    }

    public String getIbn() {
        return ibn;
    }

    public void setIbn(String ibn) {
        this.ibn = ibn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book " +
                "ISBN = " + ibn +
                ", Book Name = " + bookName +
                ", Author = " + author +
                ", Publish Date = " + publishDate +
                ", Pages = " + pages +
                ", Is Available = " + isAvailable
                ;
    }
    public String convertIsAvailableToString(){
        if (this.isAvailable == true) {
            return "Available";
        } else {
            return "Not Available";
        }
    }
}
