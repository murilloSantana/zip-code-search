package com.zipcode.zipcodesearch.analytics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/analytics")
public class HealthCheckController {

    @RequestMapping(path = "/healthCheck", method = RequestMethod.GET)
    public String health() {
        return "foward:/actuator/health";
    }

}