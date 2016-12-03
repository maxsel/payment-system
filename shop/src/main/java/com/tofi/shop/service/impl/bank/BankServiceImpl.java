package com.tofi.shop.service.impl.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.omg.SendingContext.CodeBasePackage.URLSeqHelper;
import com.tofi.shop.service.*;
import com.tofi.shop.service.impl.bank.Models.TransferResult;
import com.tofi.shop.service.impl.bank.Models.UserExists;
import com.tofi.shop.service.impl.bank.Models.UserMoney;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService{
    private final String _bankServer = "http://localhost";
//    private final String _bankServer = "http://192.168.43.67";
    private final String _bankServerPort = "8081";
    private final String _api = "/tofi-bank/rest";
    private final String _shopCardNumber = "CTOXYEB";
    private final String _targetCurrency = "BYN";
    private static final Logger LOG = LogManager.getLogger(BankServiceImpl.class);

    private final Map<APIMethod, String> _methods = new HashMap<APIMethod, String>() {{
        put(APIMethod.CheckUserExists, "/checkUserExists");
        put(APIMethod.Transfer, "/transfer");
        put(APIMethod.CheckCurrency, "/userMoney");
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
                new Parameter("card1", cardNumber),
                new Parameter("card2", cvv));
        UserExists userExists = new ObjectMapper().readValue(URLRequestSender.getResponse(url), UserExists.class);
        return userExists.getExists();
    }

    @Override
    public boolean checkCurrency(String cardNumber) throws IOException {
        String url = getApiUrl(_methods.get(APIMethod.CheckCurrency),
                new Parameter("Card1", cardNumber));
        LOG.debug(url);
        String response = URLRequestSender.getResponse(url);
        LOG.debug(response);
        UserMoney userMoney = new ObjectMapper().readValue(response, UserMoney.class);
        return userMoney.getCurrency().equals(_targetCurrency);
    }

    @Override
    public boolean checkAmountOfMoney(String cardNumber, int amountOfMoney) throws IOException {
        String url = getApiUrl(_methods.get(APIMethod.CheckCurrency),
                new Parameter("Card1", cardNumber));
        LOG.debug(url);
        String response = URLRequestSender.getResponse(url);
        LOG.debug(response);
        UserMoney userMoney = new ObjectMapper().readValue(response, UserMoney.class);
        return amountOfMoney <= userMoney.getAmount();
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