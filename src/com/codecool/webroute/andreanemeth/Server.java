package com.codecool.webroute.andreanemeth;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        server.createContext("/", new Handler());
        server.setExecutor(null);
        server.start();
    }
}