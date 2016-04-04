package com.ibm.service;

import com.ibm.http.WatsonClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by brito on 4/3/16.
 */
@Service
public class TwitterSearchService {
    private static final String TWITTER_INSIGHTS_URI = "/api/v1/messages/search";
    public static final String TWITTERINSIGHTS_SERVICE = "twitterinsights";

    @Autowired
    private WatsonClient client;

    public List<String> search(String nickName, String startDate, String endDate) {
        try {
            String url = buildUrl(nickName, startDate, endDate);

            String json = client.invokeByGet(TWITTERINSIGHTS_SERVICE, url);

            List<String> result = extractTweets(json);

            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private List<String> extractTweets(String json) throws ParseException {
        List<String> messages = new ArrayList<>();

        JSONObject root = (JSONObject) new JSONParser().parse(json);
        JSONArray tweets = (JSONArray) root.get("tweets");

        Iterator<String> iterator = tweets.iterator();

        while(iterator.hasNext()) {
            JSONObject node = (JSONObject) new JSONParser().parse(iterator.next());
            JSONObject message = (JSONObject)node.get("message");
            messages.add((String) message.get("body"));
        }

        return messages;
    }

    private String buildUrl(String nickName, String startDate, String endDate) throws UnsupportedEncodingException {
        String parameter = new StringBuilder()
                .append("from:")
                .append(nickName)
                .append(" AND posted:")
                .append(startDate)
                .append(",")
                .append(endDate)
                .toString();

        String paramEncoded = URLEncoder.encode(parameter, "UTF-8");

        String url = new StringBuilder()
                .append(TWITTER_INSIGHTS_URI)
                .append("?")
                .append(paramEncoded)
                .append("&size=100")
                .toString();

        return url;
    }
}
