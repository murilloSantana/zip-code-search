package com.zipcode.zipcodesearch.analytics.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/analytics")
public class MetricController {

    @RequestMapping(path = "/metric", method = RequestMethod.GET)
    public String listAll() {
        return "forward:/actuator/metrics";
    }

    @RequestMapping(value = "/metric/{metricName}", method = RequestMethod.GET)
    public String findByName(@PathVariable("metricName") String metricName) {
        return "forward:/actuator/metrics/" + metricName;
    }

}