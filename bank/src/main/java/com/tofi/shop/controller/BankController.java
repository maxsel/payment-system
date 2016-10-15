package com.tofi.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import com.tofi.shop.Models.*;

@RestController
@RequestMapping("/rest")
public class BankController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/string")
    public String string(@RequestParam(value="", defaultValue="simple string") String string) {
        return string;
    }

    /**********************************************************/
    /*
        Bank Logic
     */

    /*
        Chek if user with such card exists
     */

    @RequestMapping("/checkUserExists")
    public @ResponseBody UserExists checkUserExists(@RequestParam(value="card1", defaultValue="") String card1,
                                       @RequestParam(value="card2", defaultValue="") String card2) {
        //return "{\"result\": true}";
        return new UserExists(true);
    }

    /*
        get amount of mone on specific card
     */

    @RequestMapping("/userMoney")
    public @ResponseBody UserMoney userMoney(@RequestParam(value="card1", defaultValue="") String card1) {
        if(card1.equals("1")) {
            return new UserMoney(100,"BYN");
            //return "{\"amount\": 100, \"currency\": \"BYR\"}";
        } else {
            //return "{\"amount\": 30, \"currency\": \"USD\"}";
            return new UserMoney(30,"USD");
        }
    }

    /*
        add amount of mone on specific card
     */

    @RequestMapping("/addMoney")
    public @ResponseBody AddMoneyResult string(@RequestParam(value="card1", defaultValue="") String card1,
                                       @RequestParam(value="card2", defaultValue="") String card2,
                                       @RequestParam(value="amount", defaultValue="") String amount) {
        //return "{\"result\": true}";
        return new AddMoneyResult(true);
    }

    /*
        transfer "amount" of money from <card1 card2> to "accountNubmer"
     */

    @RequestMapping("/transfer")
    public @ResponseBody TransferResult string(@RequestParam(value="card1", defaultValue="") String card1,
                                       @RequestParam(value="card2", defaultValue="") String card2,
                                       @RequestParam(value="accountNumber", defaultValue="") String accountNumber,
                                       @RequestParam(value="amount", defaultValue="") String amount) {
        //return "{\"result\": true}";
        return new TransferResult(true);
    }

    // надо ли добавлять сюда пересчет валюты
}
