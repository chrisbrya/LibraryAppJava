package com.library.system.service;

import com.library.system.Model.User;
import com.library.system.interfaces.UserHelperInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserHelperInterfaceImpl implements UserHelperInterface {

    private int userCount = 0;
    public User currentUser;
    private Scanner scanner;
    private ArrayList<User> userArrayList;

    public UserHelperInterfaceImpl() {
        this.scanner = new Scanner(System.in);
        this.userArrayList = new ArrayList<>();
    }


    @Override
    public void createUser(int isAdmin) {

        User user = new User();
        user.setIsAdmin(isAdmin);

        System.out.print("\n\t\tFirst Name: ");
        user.setFirstName(scanner.nextLine());

        System.out.print("\n\t\tLast Name: ");
        user.setLastName(scanner.nextLine());

        System.out.print("\n\t\tStreet Address: ");
        user.setStreet(scanner.nextLine());

        System.out.print("\n\t\tCity: ");
        user.setCity(scanner.nextLine());

        System.out.print("\n\t\tState: ");
        user.setState(scanner.nextLine());

        System.out.print("\n\t\tZipcode: ");
        user.setZipcode(scanner.nextLine());

        System.out.print("\n\t\tDate of Birth ex: dd/mm/yyyy: ");
        String dateString = scanner.nextLine();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/mm/yyyy").parse(dateString);
        } catch (Exception e) {
            System.out.println("Improper Date format! Date format should be entered as: dd/mm/yyyy");
            ;
        }

        System.out.print("\n\n\t\tSocial Security Number: ");
        user.setSsn(scanner.nextLine());

        System.out.print("\n\n\t\tCredit Score: ");
        String phoneNumber = scanner.nextLine();
        user.setCreditScore(Integer.parseInt(phoneNumber));

        System.out.print("\n\n\t\tPhone Number: ");
        user.setPhone(scanner.nextLine());

        System.out.print("\n\n\t\tEmail: ");
        user.setEmail(scanner.nextLine());

        System.out.print("\n\n\t\tUsername: ");
        user.setUsername(scanner.nextLine());

        System.out.print("\n\n\t\tPassword: ");
        user.setPassword(scanner.nextLine());

        user.setUserID(getUniqueId());

        userCount++;

        userArrayList.add(user);
    }

    @Override
    public boolean login(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            for(User user: userArrayList){
                if (validation(user.getUsername(), user.getPassword(), username, password)){
                    this.currentUser = user;
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public String getUniqueId() {
        return new UniqueIdGeneratorInterfaceImpl().getUniqueID();
    }

    @Override
    public boolean validation(String username, String password, String otherUsername, String otherPassword) {
        if (username.equals(otherUsername) && password.equals(otherPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void displayAllUser() {
        int number = 0;
        for(User user: userArrayList){
            number++;
            System.out.println(number+".) "+"UserId: "+user.getUserID()+", "+user.getFirstName()+", "+user.getLastName());
            number--;
            System.out.println("User Index: "+number+"\n");
            number++;
        }
    }

    @Override
    public void displayUser(User userDetails) {

        String isAdmin = userDetails.getIsAdmin() == 1 ? "User" : "Admin";

        System.out.println("User Type: "+isAdmin);


        System.out.println(userDetails.toString());

    }

    @Override
    public int getUserIndexByUserID(String userId) {
        int index = -1;
        for(User user: userArrayList) {
            index++;
            if(user.getUserID().equals(userId)){
                return index;
            }
        }
        return index;
    }

    @Override
    public User getUser(int userIndex) {
        return userArrayList.get(userIndex);
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<User> getArrayList() {
        return userArrayList;
    }

    public void setArrayList(ArrayList<User> arrayList) {
        this.userArrayList = arrayList;
    }
}
