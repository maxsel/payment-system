package com.tofi.shop.service.impl.bank;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLRequestSender {
    public static String getResponse(String url)throws IOException{
        URL urlRequest = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urlRequest.openConnection();
        connection.setRequestMethod("GET");
        return connection.getResponseMessage();
    }
}