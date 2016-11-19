package com.tofi.shop.service.impl.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLRequestSender {
    private static final Logger LOG = LogManager.getLogger(URLRequestSender.class);

    public static String getResponse(String url)throws IOException{
        URL urlRequest = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urlRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.getOutputStream();
        byte [] dataRead = new byte[500];
        //int data =
        new BufferedInputStream( connection.getInputStream()).read(dataRead,0,500);
        //LOG.debug(data);
        String str = new String(dataRead, StandardCharsets.UTF_8);
        LOG.debug(str);
        return str;
    }
}