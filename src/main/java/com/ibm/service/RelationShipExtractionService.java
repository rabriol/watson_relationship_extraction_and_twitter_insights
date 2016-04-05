package com.ibm.service;

import com.ibm.http.WatsonClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by brito on 4/3/16.
 */
@Service
public class RelationShipExtractionService {
    private static final String RELATIONSHIP_EXTRACTION_URI = "/v1/sire/0";
    public static final String IE_EN_NEWS = "ie-en-news";
    public static final String JSON = "json";
    public static final String RELATIONSHIP_EXTRACTION_SERVICE = "relationship_extraction";
    public static final String SID = "sid";
    public static final String RT = "rt";
    public static final String TXT = "txt";
    public static final String NAM = "NAM";
    public static final String PERSON = "PERSON";
    public static final double SCORE_QUALITY = 0.5;

    @Autowired
    private WatsonClient client;

    /**
     * Makes the call to the relationship extraction service to discover all persons, that are mentioned in the tweets
     *
     * @param text the parameter to be submitted to the service where are extract the persons
     * @return the list containing all persons that were discovered
     */
    public List<String> extract(String text) throws Exception {
        String uri = RELATIONSHIP_EXTRACTION_URI;

        Map<String, String> parameters = new HashMap<>();
        parameters.put(SID, IE_EN_NEWS);
        parameters.put(RT, JSON);
        parameters.put(TXT, text);

        String json = client.invokeByPost(RELATIONSHIP_EXTRACTION_SERVICE, uri, parameters);

        List<String> people = extractPeople(json);

        return people;
    }

    // starts reading the json from doc -> mentions -> mention.
    // when it detects a node with the values {mtype=NAM,role=PERSON, score >= 0.5}
    // it adds the person to the list
    private List<String> extractPeople(String json) throws ParseException {
        List<String> people = new ArrayList<>();

        JSONObject root = (JSONObject) new JSONParser().parse(json);
        JSONObject doc = (JSONObject) root.get("doc");
        JSONObject mentions = (JSONObject) doc.get("mentions");
        JSONArray mention = (JSONArray) mentions.get("mention");

        if (mention == null) {
            return new ArrayList<>();
        }

        Iterator<JSONObject> iterator = mention.iterator();

        while (iterator.hasNext()) {
            JSONObject node = iterator.next();
            String mtype = (String) node.get("mtype");
            String role = (String) node.get("role");
            Double score = (Double) node.get("score");

            if (NAM.equals(mtype) && PERSON.equals(role) && score >= SCORE_QUALITY) {
                people.add((String) node.get("text"));
            }
        }

        return people;
    }
}