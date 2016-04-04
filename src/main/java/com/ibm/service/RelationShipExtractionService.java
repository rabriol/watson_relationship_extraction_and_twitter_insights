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

    @Autowired
    private WatsonClient client;

    public List<String> extract(String text) {
        try {
            String uri = RELATIONSHIP_EXTRACTION_URI;

            Map<String, String> parameters = new HashMap<>();
            parameters.put(SID, IE_EN_NEWS);
            parameters.put(RT, JSON);
            parameters.put(TXT, text);

            String json = client.invokeByPost(RELATIONSHIP_EXTRACTION_SERVICE, uri, parameters);

            List<String> people = extractPeople(json);

            return people;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private List<String> extractPeople(String json) throws ParseException {
        List<String> people = new ArrayList<>();

        JSONObject root = (JSONObject) new JSONParser().parse(json);
        JSONObject doc = (JSONObject) root.get("doc");
        JSONObject entities = (JSONObject) doc.get("entities");
        JSONArray entity = (JSONArray) entities.get("entity");

        Iterator<String> iterator = entity.iterator();

        while(iterator.hasNext()) {
            JSONObject node = (JSONObject) new JSONParser().parse(iterator.next());
            String level = (String)node.get("level");
            String type = (String)node.get("type");
            String score = (String)node.get("score");

            if ("NAM".equals(level) && "PERSON".equals(type) && Double.valueOf(score) >= 0.5) {
                JSONObject mentref = (JSONObject)node.get("mentref");
                people.add((String) mentref.get("text"));
            }
        }

        return people;
    }

}
