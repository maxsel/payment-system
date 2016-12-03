package com.tofi.shop.controller;

import com.tofi.shop.domain.User;
import com.tofi.shop.domain.UserRole;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserRoleService;
import com.tofi.shop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.regex.Pattern;

@Controller
public class WelcomeController {
    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);
    private UserService userService;
    private UserRoleService roleService;
    private final UserRole ROLE_ADMIN;
    private final UserRole ROLE_USER;

    @Autowired @Qualifier("authMgr")
    private AuthenticationManager authMgr;

    @Autowired
    private UserDetailsService userDetailsSvc;

    @Inject
    public WelcomeController(UserService userService, UserRoleService roleService) throws ServiceException {
        this.userService = userService;
        this.roleService = roleService;
        ROLE_ADMIN = roleService.findById(1);
        ROLE_USER = roleService.findById(2);
        LOG.trace("Test trace log: WelcomeController created");
    }

    @RequestMapping(value = {"/"})
    public String redirectToNewsList() {
        return "redirect:/items-list";
    }


    @RequestMapping("/register")
    public String showRegisterPage() throws ServiceException {
        return "register";
    }

    @ModelAttribute("user")
    public User populateUserDTO() throws ServiceException {
        return new User();
    }

    @ModelAttribute("newUser")
    public User populateNewUserDTO() throws ServiceException {
        return new User();
    }

    @RequestMapping("/signup")
    public String signUp(@Valid @ModelAttribute("newUser") User user,
                         BindingResult bindingResult, Model model) throws ServiceException {
        try {
            int loginLength = user.getLogin().length();
            int passwordLength = user.getPassword().length();
            String vardId = user.getCardId();
            LOG.debug(loginLength);
            if (2 > loginLength || loginLength > 50) {
                model.addAttribute("error", "Length of login has to be between 2 and 50");
                return "register";
            }
            if (5 > passwordLength || passwordLength > 50) {
                model.addAttribute("error", "Length of password has to be between 5 and 50");
                return "register";
            }
            String regex = "\\b(4[0-9]{12}(?:[0-9]{3})?)\\b";

            if (!user.getCardId().matches(regex)){
                model.addAttribute("error", "invalid card number");
                return "register";
            }

            user.setRoles(new HashSet<>());
            user.getRoles().add(ROLE_USER);
            userService.create(user);
            // login after registration
            // https://gerrydevstory.com/2014/03/11/spring-security-auto-login-after-successful-registration/
            try {
                UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getLogin());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
                authMgr.authenticate(auth);

                // redirect to secured main page if authentication successful
                if(auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    return "redirect:/";
                }
            } catch (Exception e) {
                LOG.debug("Problem authenticating user" + user.getLogin(), e);
            }
        } catch (ServiceException e) {
            LOG.error(e);
            return "error";
        }
        return "redirect:/items-list";
    }
}
