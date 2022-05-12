package com.library.system.interfaces;

public interface MainDriverInterface {

    public void mainStartScreen();

    public void mainRegisterUser();

    public void mainLogin();

    public void mainUserMenu();

    public void mainAdminMenu();

    public void adminViewAllBooks();

    public void adminViewAllRequests();

    public void mainDisplayAllUser();

    public void mainDisplayUserDetails(int userIndex);

    public void mainUserSeeBooks();

    public void mainUserViewRequest();

    public void mainUserCheckoutBook();

    public void mainAdminApproveOrDeny();

    public void mainAdminApproveOrDenyMenu();

    public void mainAdminRegisterBook();

    public void userReturnBook();
}
