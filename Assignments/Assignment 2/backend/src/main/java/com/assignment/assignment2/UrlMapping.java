package com.assignment.assignment2;

public class UrlMapping {
    public static final String API_PATH = "/api";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String BOOKS = API_PATH + "/books";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_BY_ID = "/{id}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = FIND_BY_ID;
    public static final String REPORT = "/report/{reportType}";
}