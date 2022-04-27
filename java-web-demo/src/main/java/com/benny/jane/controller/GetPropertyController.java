package com.benny.jane.controller;


import com.benny.jane.config.property.GetPersonInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPropertyController {

    @Value("${name}")
    private String name;

    @Value("${country}")
    private String country;

    @Autowired
    private GetPersonInfoProperties getPersonInfoProperties;

    @GetMapping("/get/name")
    public String getName() {
        return name;
    }

    @GetMapping("/get/country")
    public String getCountry() {
        return country;
    }

    @GetMapping("/get/custom/info/name")
    public String getCustomInfo() {
        return getPersonInfoProperties.getName() + ": " + getPersonInfoProperties.getAge();
    }


}
