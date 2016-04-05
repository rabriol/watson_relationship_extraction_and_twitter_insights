package com.ibm.controller;

import com.ibm.logic.PeopleExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brito on 4/3/16.
 */
@RestController
public class WebController {

    @Autowired
    private PeopleExtractor peopleExtractor;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find() {



        return "";
    }
}