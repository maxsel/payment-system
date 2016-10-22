package com.tofi.shop.service.impl.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tofi.shop.service.*;
import com.tofi.shop.service.impl.bank.Models.TransferResult;
import com.tofi.shop.service.impl.bank.Models.UserExists;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService{
    private final String _bankServer = "localhost";
    private final String _bankServerPort = "8080";
    private final String _api = "/tofi-bank/rest";
    private final String _shopCardNumber = "CTOXYEB";
    private final Map<APIMethod, String> _methods = new HashMap<APIMethod, String>() {{
        put(APIMethod.CheckUserExists, "/checkUserExits");
        put(APIMethod.Transfer, "/transfer");
    }};

    @Override
    public boolean pay(String cardNumber, String cvv, long amount) throws IOException {
        String url = getApiUrl(_methods.get(APIMethod.Transfer),
                new Parameter("card1", cvv),
                new Parameter("card2", cardNumber),
                new Parameter("accountNumber", _shopCardNumber),
                new Parameter("amount", Long.toString(amount)));
        TransferResult transferResult = new ObjectMapper().readValue(URLRequestSender.getResponse(url), TransferResult.class);
        return transferResult.getResult();
    }

    @Override
    public boolean userExists(String cardNumber, String cvv) throws IOException {
        String url = getApiUrl(_methods.get(APIMethod.CheckUserExists),
                new Parameter("Card1", cardNumber),
                new Parameter("Card2", cvv));
        UserExists userExists = new ObjectMapper().readValue(URLRequestSender.getResponse(url), UserExists.class);
        return userExists.getExists();
    }

    private String getBankUrl(){ return _bankServer + ':' + _bankServerPort + _api; }

    private String getApiUrl(String methodUrl, Parameter... params){
        String url = getBankUrl() + methodUrl  + "?";
        for (int index = 0; index < params.length; index++){
            url += params[index].getName() + '=' + params[index].getValue();
            if (index != params.length-1) {
                url += "&";
            }
        }
        return url;
    }
}