package com.epam.atm.homework5.selenium.businessobjects;

public class GmailUser {

    private static final String USER_LOGIN = "tyled6@gmail.com";
    private static final String USER_PASSWORD = "qwerty23";

    public String getLogin(){
        return USER_LOGIN;
    }

    public String getPassword(){
        return USER_PASSWORD;
    }

    public static GmailUser getDefaultUser(){
        return new GmailUser();
    }
}
