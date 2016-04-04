package com.ibm.controller;

import com.ibm.logic.PeopleExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by brito on 4/3/16.
 */
@Controller
public class WebController {

    @Autowired
    private PeopleExtractor peopleExtractor;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(Model model) {



        return "";
    }
}