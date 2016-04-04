package com.ibm.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by peo_rboliveira on 04/04/16.
 */

@Service
public class WatsonClient {
    private static final int FIRST_ELEMENT = 0;
    private final String VCAP_SERVICES = "VCAP_SERVICES";
    private final String CREDENTIALS = "credentials";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String URL = "url";

    private String username;
    private String password;
    private String baseUrl;

    private JSONArray watson;
    private JSONObject watsonInstance;
    private JSONObject watsonCredentials;

    public String invokeByGet(String serviceName, String suffix) throws Exception {
        Executor executor = createExecutor(serviceName);

        try {
            URI uri = new URI(baseUrl + suffix).normalize();

            return executor
                        .execute(Request.Get(uri)
                            .setHeader("Accept", "application/json"))
                        .returnContent()
                        .asString();
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    public String invokeByPost(String serviceName, String suffix, Map<String, String> parameters) throws Exception {
        Executor executor = createExecutor(serviceName);

        try {
            URI uri = new URI(baseUrl + suffix).normalize();

            List<NameValuePair> nameValuePairs = new ArrayList<>();

            for (String key : parameters.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, parameters.get(key)));
            }

            return executor
                        .execute(Request.Post(uri)
                            .setHeader("Accept", "application/json")
                            .bodyForm(nameValuePairs))
                        .returnContent()
                        .asString();
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    private Executor createExecutor(String serviceName) {
        try {
            String vCapServices = System.getenv(VCAP_SERVICES);

            JSONObject vCap = (JSONObject) new JSONParser().parse(vCapServices);

            this.watson = (JSONArray) vCap.get(serviceName);
            this.watsonInstance = (JSONObject) watson.get(FIRST_ELEMENT);
            this.watsonCredentials = (JSONObject) watsonInstance.get(CREDENTIALS);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        this.username = (String) watsonCredentials.get(USERNAME);
        this.password = (String) watsonCredentials.get(PASSWORD);
        this.baseUrl = (String) watsonCredentials.get(URL);

        return Executor.newInstance().auth(username, password);
    }
}
