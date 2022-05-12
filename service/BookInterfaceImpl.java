package com.library.system.service;

import com.library.system.Model.Book;
import com.library.system.interfaces.BookInterface;
import com.library.system.interfaces.UniqueIdGeneratorInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookInterfaceImpl implements BookInterface {

    UniqueIdGeneratorInterfaceImpl uniqueIdGeneratorInterfaceimpl = new UniqueIdGeneratorInterfaceImpl();
    List<Book> booksList = new ArrayList<>();
    int bookCount = 4;

    public BookInterfaceImpl()
    {

        try{
            Date date1 = new SimpleDateFormat("mm/dd/yyyy").parse("06/26/1992");
            Date date2 = new SimpleDateFormat("mm/dd/yyyy").parse("10/23/2018");
            Date date3 = new SimpleDateFormat("mm/dd/yyyy").parse("01/11/1642");
            Date date4 = new SimpleDateFormat("mm/dd/yyyy").parse("04/11/2017");
            Book book1 = new Book(getIbnCharacters(), "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", date1, true, 223);
            Book book2 = new Book(getIbnCharacters(), "The Mamba Mentality: How I Play", "Kobe Bryant", date2, true, 208);
            Book book3 = new Book(getIbnCharacters(), "The Art of War", "Sun Tzu", date3, true, 224);
            Book book4 = new Book(getIbnCharacters(), "Rich Dad Poor Dad", "Robert T. Kiyosaki", date4, true, 336);
            booksList.add(book1);
            booksList.add(book2);
            booksList.add(book3);
            booksList.add(book4);
        } catch (ParseException e) {
            System.out.println("Improper format date should be entered as: mm/dd/yyyy ex: 01/05/1999");
        }

      //  this.uniqueIdGeneratorInterfaceimpl = new UniqueIdGeneratorInterfaceImpl();
    }

    @Override
    public void registerBook() {
        Book book = new Book();
        Scanner scanner = new Scanner(System.in);

        book.setAvailable(true);
        System.out.println("Add Book to Library. Enter Book's information: ");
        System.out.print("Book Name: ");
        book.setBookName(scanner.nextLine());

        System.out.print("Author: ");
        book.setAuthor(scanner.nextLine());

        System.out.print("Publishing Date ex. mm/dd/yyyy: ");
        String dateString = scanner.nextLine();
        Date date = null;

        try{
            date = new SimpleDateFormat("mm/dd/yyyy").parse(dateString);
            book.setPublishDate(date);
        } catch (ParseException e) {
            System.out.println("Improper format date should be entered as: mm/dd/yyyy ex: 01/05/1999");
        }

        book.setIbn(getIbnCharacters());

        System.out.print("Pages: ");
        String pages = scanner.nextLine();
        book.setPages(Integer.parseInt(pages));

        System.out.println("\nThis book has been added to the Library.");

        this.booksList.add(book);

        this.bookCount++;

    }

    @Override
    public String getIbnCharacters() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            stringBuilder.append(this.uniqueIdGeneratorInterfaceimpl.iDcharacterList
                    .charAt(getIbnRandomNumber()));
            
        }
        return stringBuilder.toString();
    }

    @Override
    public int getIbnRandomNumber() {
        return this.uniqueIdGeneratorInterfaceimpl.randomNumberGenerator(10);
    }

    @Override
    public void displayInventory() {
        int counter = 0;
        for (Book book: booksList) {
            counter++;
            System.out.println(counter+".) "+book.toString());
        }
    }

    @Override
    public void displayAllBooks() {
        int index = 0;

        for (Book book: booksList) {
            String status = book.convertIsAvailableToString();
            System.out.println("Index:"+index+" ISBN: "+book.getIbn()+"\n Name: "+book.getBookName()+"\n Author: "+book.getAuthor()+"\n Available: "+status);
            index++;
        }
    }

    @Override
    public Book getBook(int bookIndex) {
        return booksList.get(bookIndex);
    }

    @Override
    public int getBookIndexByIbn(String ibn) {
        int index = -1;

        for (Book book : booksList) {
            index++;
            if(book.getIbn().equals(ibn)){
                break;
            }
        }
        return index;
    }
}
