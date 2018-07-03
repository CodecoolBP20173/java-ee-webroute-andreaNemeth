package com.codecool.webroute.andreanemeth;

public class Router {
    @WebRoute("/")
    String onIndex() {
        return "first page";
    }

    @WebRoute("/test")
    String onTest() {
        return "test";
    }
}