package com.ibm.logic;

import com.ibm.service.RelationShipExtractionService;
import com.ibm.service.TwitterSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by peo_rboliveira on 05/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MentionExtractorTest {

    @Mock
    private RelationShipExtractionService relationShipExtractionService;

    @Mock
    private TwitterSearchService twitterSearchService;

    @InjectMocks
    private MentionExtractor mentionExtractor;

    @Test
    public void testResultEmpty() throws Exception {

        when(twitterSearchService.search(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());
        when(relationShipExtractionService.extract(anyString())).thenReturn(new ArrayList<String>());

        Map<String, Long> result = mentionExtractor.find("rafaeu", "2016-04-01", "2016-04-06");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testResultIsNotEmpty() throws Exception {

        List<String> tweets = new ArrayList<>();
        tweets.add("Bryce Harper and Denard Span lead the league in home runs");

        when(twitterSearchService.search(anyString(), anyString(), anyString())).thenReturn(tweets);

        List<String> persons = new ArrayList<>();
        persons.add("Bryce Harper");
        persons.add("Denard Span");

        when(relationShipExtractionService.extract(anyString())).thenReturn(persons);

        Map<String, Long> result = mentionExtractor.find("rafaeu", "2016-04-01", "2016-04-06");

        assertFalse(result.isEmpty());
        assertTrue(result.size() == 2);
    }
}