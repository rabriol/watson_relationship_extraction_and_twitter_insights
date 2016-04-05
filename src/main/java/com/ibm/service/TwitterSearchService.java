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

    /**
     * Makes the call to twitterinsights service and extract all tweets found
     *
     * @param nickName the name of the interested user
     * @param startDate follows the pattern CCYY-MM-DD and is the initial date used on the query to retrieve the tweets
     * @param endDate follows the pattern CCYY-MM-DD and is the end date used on the query to retrieve the tweets
     * @return a list contaning all tweets found based on the query parameters
     * @throws Exception when some error occurs
     */
    public List<String> search(String nickName, String startDate, String endDate) throws Exception {
        String uri = buildUri(nickName, startDate, endDate);

        String json = client.invokeByGet(TWITTERINSIGHTS_SERVICE, uri);

        List<String> result = extractTweets(json);

        return result;
    }

    public WatsonClient getClient() {
        return client;
    }

    public void setClient(WatsonClient client) {
        this.client = client;
    }

    // starts reading the json from tweets -> message -> body
    // the body is where are in fact the tweets
    private List<String> extractTweets(String json) throws ParseException {
        List<String> messages = new ArrayList<>();

        JSONObject root = (JSONObject) new JSONParser().parse(json);
        JSONArray tweets = (JSONArray) root.get("tweets");

        Iterator<JSONObject> iterator = tweets.iterator();

        while(iterator.hasNext()) {
            JSONObject node = iterator.next();
            JSONObject message = (JSONObject)node.get("message");
            messages.add((String) message.get("body"));
        }

        return messages;
    }

    // compose the the uri following the pattern "from:nickname AND posted:CCYY-MM-DD,CCYY-MM-DD"
    private String buildUri(String nickName, String startDate, String endDate) throws UnsupportedEncodingException {
        String parameter = new StringBuilder()
                .append("from:")
                .append(nickName)
                .append(" AND posted:")
                .append(startDate)
                .append(",")
                .append(endDate)
                .toString();

        String paramEncoded = URLEncoder.encode(parameter, "UTF-8");

        //the parameter size is fixed in 100 just considering this is a experimental project
        String url = new StringBuilder()
                .append(TWITTER_INSIGHTS_URI)
                .append("?q=")
                .append(paramEncoded)
                .append("&size=100")
                .toString();

        return url;
    }
}
