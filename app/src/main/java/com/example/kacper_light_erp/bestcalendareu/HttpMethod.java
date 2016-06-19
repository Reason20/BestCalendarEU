package com.example.kacper_light_erp.bestcalendareu;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public enum HttpMethod {
    GET("GET"), POST("POST");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
