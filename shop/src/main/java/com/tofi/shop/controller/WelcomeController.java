package com.tofi.shop.controller;

import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);

    @RequestMapping(value = {"/"})
    public String redirectToNewsList() {
        return "redirect:/items-list";
    }


    @RequestMapping("/register")
    public String showRegisterPage() throws ServiceException {
        return "register";
    }
}
