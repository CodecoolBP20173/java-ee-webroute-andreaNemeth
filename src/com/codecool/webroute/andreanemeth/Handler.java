package com.codecool.webroute.andreanemeth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Handler implements HttpHandler{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Class<Router> object = Router.class;
        String response = "response";
        int responseCode = 404;


        for(Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getAnnotation(WebRoute.class);
                WebRoute webRoute = (WebRoute) annotation;

                if (webRoute.value().equals(httpExchange.getRequestURI().toString()) && webRoute.requestMethod().equals(httpExchange.getRequestMethod())) {
                    try {
                        response = (String) method.invoke(object.newInstance());
                        responseCode = 200;
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        httpExchange.sendResponseHeaders(responseCode, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}