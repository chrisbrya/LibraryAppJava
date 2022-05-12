package com.library.system.interfaces;

import com.library.system.Model.User;

public interface UserHelperInterface {

    void createUser(int isAdmin);
    boolean login(String username, String password);
    String getUniqueId();
    boolean validation(String username, String password, String otherUsername, String otherPassword);
    void displayAllUser();
    void displayUser(User userDetails);
    int getUserIndexByUserID(String userId);
    User getUser(int userIndex);
}
