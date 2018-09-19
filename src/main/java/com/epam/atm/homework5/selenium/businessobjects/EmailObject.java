package com.epam.atm.homework5.selenium.businessobjects;

public class EmailObject {
    private static final String TO = "ok130493@gmail.com";
    private static final String SUBJECT = "hello message";
    private static final String MESSAGE = "hi, how?";

    public String getToValue() {
        return TO;
    }

    public String getSubjectValue() {
        return SUBJECT;
    }

    public String getMessageValue() {
        return MESSAGE;
    }

    public static EmailObject getDefaultEmail() {
        return new EmailObject();
    }
}
