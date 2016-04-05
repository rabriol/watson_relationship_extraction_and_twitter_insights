package com.ibm.logic;

import com.ibm.service.RelationShipExtractionService;
import com.ibm.service.TwitterSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by peo_rboliveira on 04/04/16.
 */
@Service
public class MentionExtractor {

    @Autowired
    private TwitterSearchService twitterSearchService;

    @Autowired
    private RelationShipExtractionService relationShipExtractionService;

    /**
     * Makes all the logic including:
     *
     * <ul>
     *     <li>call the twitterinsights service to get all tweets from a specific user</li>
     *     <li>call the relationship_extraction to discovery if that person has mentioned somebody</li>
     * </ul>
     *
     * @param nickName is the name of the interested user
     * @param startDate follows the pattern CCYY-MM-DD and is the initial date used on the query to retrieve the tweets
     * @param endData follows the pattern CCYY-MM-DD and is the end date used on the query to retrieve the tweets
     * @return a map where the key is the name of the mentioned person and the value is how many time he/she was found based on the tweets analyzed
     */
    public Map<String, Long> find(String nickName, String startDate, String endData) {

        List<String> messages = twitterSearchService.search(nickName, startDate, endData);

        List<String> mentions = new ArrayList<>();
        for (String message : messages) {
            mentions.addAll(relationShipExtractionService.extract(message));
        }

        return mentions
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
