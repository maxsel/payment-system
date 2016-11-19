package com.tofi.shop.service.impl.bank;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLRequestSender {
    public static String getResponse(String url)throws IOException{
        URL urlRequest = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urlRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.getOutputStream();
        byte [] dataRead = new byte[500];
        //int data =
        new BufferedInputStream( connection.getInputStream()).read(dataRead,0,500);
        //System.out.println(data);
        String str = new String(dataRead, StandardCharsets.UTF_8);
        System.out.println(str);
        return str;
    }
}