package com.example.ht.ui.login;

import java.util.ArrayList;
import java.util.List;

/**
 * Class exposing authenticated user details to the UI.
 */

class User {
    public static User admin = new User("admin", "admin1ADMIN!");
    public static List<User> UserList = new ArrayList<User>();

    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void addUser(User user) {
        UserList.add(user);
        System.out.println(UserList.size());
    }

    public static boolean logCheck(String username) {
        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean passwordCheck(String username, String password) {
        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).username.equals(username)) {
                if (UserList.get(i).password.equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}

class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}