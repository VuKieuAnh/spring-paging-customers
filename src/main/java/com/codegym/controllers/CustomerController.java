package com.codegym.controllers;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();
    @GetMapping()
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("list", "customers", customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView createModelandView = new ModelAndView("create", "customer", new Customer());
        return createModelandView;
    }
}
