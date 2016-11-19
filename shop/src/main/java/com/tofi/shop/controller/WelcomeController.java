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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Controller
public class WelcomeController {
    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);
    private UserService userService;
    private UserRoleService roleService;
    private final UserRole ROLE_ADMIN;
    private final UserRole ROLE_USER;

    @Inject
    public WelcomeController(UserService userService, UserRoleService roleService) throws ServiceException {
        this.userService = userService;
        this.roleService = roleService;
        ROLE_ADMIN = roleService.findById(1);
        ROLE_USER = roleService.findById(2);
        LOG.trace("----------Test log--------------");
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
    public String signUp(@ModelAttribute("newUser") User user,
                         BindingResult bindingResult) throws ServiceException {
        try {
            if (bindingResult.hasErrors()) {
                LOG.debug("There are binding errors:" + bindingResult.getAllErrors());
                return "edit-authors";
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

                // redirect into secured main page if authentication successful
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

    @Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
    @Autowired
    private UserDetailsService userDetailsSvc;
}
