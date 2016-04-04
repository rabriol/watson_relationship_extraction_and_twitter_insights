package com.ibm.logic;

import com.ibm.service.RelationShipExtractionService;
import com.ibm.service.TwitterSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peo_rboliveira on 04/04/16.
 */
@Service
public class PeopleExtractor {

    @Autowired
    private TwitterSearchService twitterSearchService;

    @Autowired
    private RelationShipExtractionService relationShipExtractionService;

    public void findPeople(String nickName, String startDate, String endData) {

        List<String> messages = twitterSearchService.search(nickName, startDate, endData);

        List<String> people = new ArrayList<>();
        for (String message : messages) {
            people.addAll(relationShipExtractionService.extract(message));
        }

        
    }
}
