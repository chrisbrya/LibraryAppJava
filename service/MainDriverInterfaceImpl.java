package com.library.system.service;

import com.library.system.Model.Book;
import com.library.system.Model.Requests;
import com.library.system.Model.User;
import com.library.system.interfaces.MainDriverInterface;

import java.util.Scanner;

public class MainDriverInterfaceImpl implements MainDriverInterface {


    Scanner scanner = new Scanner(System.in);
    UserHelperInterfaceImpl userHelperInterfaceImpl = new UserHelperInterfaceImpl();
    BookInterfaceImpl bookInterface = new BookInterfaceImpl();
    RequestsInterfaceImpl requestsInterfaceImpl = new RequestsInterfaceImpl();


    @Override
    public void mainStartScreen() {
        System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome to A Cup and A Book Library Application\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1.) Register User  \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2.) Login \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");
        String choice = scanner.nextLine();

        switch(Integer.parseInt(choice)){
            case 1:
                mainRegisterUser();
                break;

            default:
                mainLogin();
                break;
        }
    }

    @Override
    public void mainRegisterUser() {

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1.) Create User\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2.) Create Admin\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");
        String choice = scanner.nextLine();

        userHelperInterfaceImpl.createUser(Integer.parseInt(choice)-1);

        mainStartScreen();

    }

    @Override
    public void mainLogin() {
        System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tUsername: \n");
        String username = this.scanner.nextLine();

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPassword: \n");
        String password = this.scanner.nextLine();

        boolean loggedIn = this.userHelperInterfaceImpl.login(username, password);
        //System.out.println("This is the admin "+this.userHelperInterfaceImpl.currentUser.getIsAdmin());
        if(loggedIn == true){
            if(this.userHelperInterfaceImpl.currentUser.getIsAdmin() == 1)
            {
                mainAdminMenu();
            }else
            {
                mainUserMenu();
            }
        } else {
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tIncorrect Login Information! Try Again");
            mainStartScreen();
        }

    }

    @Override
    public void mainUserCheckoutBook() {
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter the index of the Book that you want to Checkout: ");

        String index = this.scanner.nextLine();
        Book book = this.bookInterface.getBook(Integer.parseInt(index));

        this.requestsInterfaceImpl.generateRequest(this.userHelperInterfaceImpl.currentUser.getUserID(),
                book.getIbn());

        mainUserSeeBooks();

    }

    @Override
    public void mainAdminApproveOrDeny() {

        System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1) Approve or Deny Request \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2) Back \n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");
        int choice = Integer.parseInt(this.scanner.nextLine());

        switch (choice){
            case 1:
                mainAdminApproveOrDenyMenu();
                break;
            case 2:
                mainAdminMenu();
                break;
            default:
                System.out.println("Invalid Input");
                mainAdminApproveOrDeny();
                break;
        }
    }

    @Override
    public void mainAdminApproveOrDenyMenu() {
        System.out.print("\n\nEnter Index of Request: \n");

        int choice = Integer.parseInt(this.scanner.nextLine());

        System.out.print("Enter 'a' to Approve or 'd' to Deny: \n");
        String approveOrDenyString = this.scanner.nextLine();
        char approveOrDeny = approveOrDenyString.charAt(0);

        this.requestsInterfaceImpl.approveOrDeny(choice, approveOrDeny);
        System.out.println("Status Has Been Updated");
        mainAdminApproveOrDeny();
    }

    @Override
    public void mainAdminRegisterBook() {
        this.bookInterface.registerBook();
        adminViewAllBooks();
    }

    @Override
    public void userReturnBook() {



        String userId = this.userHelperInterfaceImpl.currentUser.getUserID();
        int requestIndex = this.requestsInterfaceImpl.getRequestByUserId(userId);

        if(requestIndex == -1){
            System.out.println("There are no available Requests");
        } else{
            this.requestsInterfaceImpl.requestsList.remove(requestIndex);
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThank you for returning your Book. Have a great day!");
        }
        mainUserMenu();
    }

    @Override
    public void mainUserSeeBooks() {
        bookInterface.displayAllBooks();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1) Checkout Book \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2) Back\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");

        String choice = this.scanner.nextLine();

        switch(Integer.parseInt(choice)) {
            case 1:
                mainUserCheckoutBook();
                break;
            case 2:
                mainUserMenu();
                break;
            default:
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tInvalid Input!");
                mainUserSeeBooks();
                break;
        }
    }

    @Override
    public void mainUserViewRequest() {
        int requestIndex = this.requestsInterfaceImpl.getRequestByUserId(this.userHelperInterfaceImpl.currentUser.getUserID());

        Requests currentUserRequests = this.requestsInterfaceImpl.getRequest(requestIndex);

        int bookIndex = this.bookInterface.getBookIndexByIbn(currentUserRequests.getIbn());

        Book currentBookRequested = this.bookInterface.getBook(bookIndex);

        String status = currentUserRequests.convertStatusToString();
        System.out.println("Book: "+currentBookRequested.getBookName()+" Author: "+currentBookRequested.getAuthor()+" Year: "+currentBookRequested.getPublishDate()+" ISBN: "+currentBookRequested.getIbn()+" Status: "+status);

        mainUserMenu();
    }

    @Override
    public void mainUserMenu(){

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome User "+this.userHelperInterfaceImpl.currentUser.getFirstName());
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1) See Books \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2) View my Requests \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t3) Return Book\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t4) Logout\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");

        String choice = this.scanner.nextLine();

        switch(Integer.parseInt(choice)){
            case 1:
                mainUserSeeBooks();
                break;
            case 2:
                mainUserViewRequest();
                break;
            case 3:
                userReturnBook();
                break;
            case 4:
                mainLogin();
                break;
            default:
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tInvalid Input!");
                mainUserMenu();
                break;
        }
    }



    @Override
    public void mainAdminMenu(){
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome "+this.userHelperInterfaceImpl.currentUser.getFirstName()+ " to the Admin Menu");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1.) Requests\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2.) Book Inventory \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t3.) Display All Users \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t4.) Logout\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");

        String choice = scanner.nextLine();

        switch(Integer.parseInt(choice)){
            case 1:
                adminViewAllRequests();
                mainAdminMenu();
                break;
            case 2:
                adminViewAllBooks();
                mainAdminMenu();
                break;
            case 3:
                mainDisplayAllUser();
                mainAdminMenu();
                break;


            default:
                mainLogin(); //make logout
                break;
        }
    }

    @Override
    public void adminViewAllBooks() {
        this.bookInterface.displayAllBooks();

        System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1) Register Book \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2) Back\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice: ");

        int choice = Integer.parseInt(this.scanner.nextLine());

        switch (choice){
            case 1:
                mainAdminRegisterBook();
                break;
            case 2:
                mainAdminMenu();
                break;
            default:
                System.out.println("Invalid Input!");
                adminViewAllBooks();
                break;
        }

    }

    @Override
    public void adminViewAllRequests() {

        Book book;
        User user;
        int userIndex, bookIndex, requestIndex = 0;
        for (Requests requests: this.requestsInterfaceImpl.requestsList) {

            bookIndex = this.bookInterface.getBookIndexByIbn(requests.getIbn());

//            System.out.println("Checkpoint 1 in ");
            book = this.bookInterface.getBook(bookIndex);
//            System.out.println("Checkpoint 1 out ");

//            System.out.println("Checkpoint 2 in ");
            userIndex = this.userHelperInterfaceImpl.getUserIndexByUserID(requests.getUserId());
//            System.out.println("Checkpoint 2 out ");

//            System.out.println("Checkpoint 3 in ");
            System.out.println("userIndex: "+userIndex);
            user = this.userHelperInterfaceImpl.getUser(userIndex);
//            System.out.println("Checkpoint 3 out ");


            System.out.println("Request for: "+user.getFirstName()+", "+user.getLastName()+" Book Name: "+book.getBookName()+" Author: "+book.getAuthor()+" Pages: "+book.getPages()+" Request Index: "+requestIndex);

            requestIndex++;
            //System.out.println(requests.toString());
        }
        mainAdminApproveOrDeny();
    }

    @Override
    public void mainDisplayAllUser() {

        userHelperInterfaceImpl.displayAllUser();

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Enter User Index to See Details: ");

        String choice = scanner.nextLine();
        User userDetails = userHelperInterfaceImpl.getUser(Integer.parseInt(choice));

        userHelperInterfaceImpl.displayUser(userDetails);
        System.out.println("\n");

        mainDisplayAllUser();
    }

    @Override
    public void mainDisplayUserDetails(int userIndex) {
        User user = userHelperInterfaceImpl.getUser(userIndex);
        user.toString();
        mainAdminMenu();
    }
}
