package com.ibm.controller;

import com.ibm.logic.MentionExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by brito on 4/3/16.
 */
@Controller
public class WebController {

    @Autowired
    private MentionExtractor mentionExtractor;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    //considering this is a experimental project, where the main goal is to test watson's service
    //there is no validation for date pattern
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(@RequestParam("nickName") String nickName,
                       @RequestParam("startDate") String startDate,
                       @RequestParam("endDate") String endDate,
                       Model model) {
        try {
            Map<String, Long> result = mentionExtractor.find(nickName, startDate, endDate);

            return result.toString();
        } catch (Exception e) {
            return "";
        }
    }
}