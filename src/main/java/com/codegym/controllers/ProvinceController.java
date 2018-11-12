package com.codegym.controllers;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("/")
    public ModelAndView listProvince(){
        Iterable<Province> listProvince = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list", "provinces", listProvince);
        return modelAndView;
    }
}
