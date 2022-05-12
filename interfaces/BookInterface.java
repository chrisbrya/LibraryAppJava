package com.library.system.interfaces;

import com.library.system.Model.Book;

public interface BookInterface {

    void registerBook();

    String getIbnCharacters();

    int getIbnRandomNumber();

    void displayInventory();

    void displayAllBooks();

    Book getBook(int bookIndex);

    int getBookIndexByIbn(String ibn);
}
