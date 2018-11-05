package com.codegym.controllers;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/save")
    public ModelAndView save(Customer customer){
        customer.setId((int)(Math.random() * 10000));
        customerService.save(customer);
        ModelAndView saveModelandView = new ModelAndView("list", "customers", customerService.findAll());
        saveModelandView.addObject("success", "successed");
        return saveModelandView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView editModelandView = new ModelAndView("edit", "customer", customerService.findById(id));
        return editModelandView;
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView delModelandView = new ModelAndView("delete","customer", customerService.findById(id));
        return delModelandView;
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id) {
        ModelAndView viewCustomerDetail = new ModelAndView("detail","customer", customerService.findById(id));
        return viewCustomerDetail;
    }
}
