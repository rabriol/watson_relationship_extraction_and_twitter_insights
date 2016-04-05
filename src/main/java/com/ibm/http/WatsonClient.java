package com.ibm.http;

import com.ibm.exception.InfrastructureException;
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

    /**
     * Based on the serviceName informed it makes the call to the service with GET HTTP method
     *
     * @param serviceName is the name of the service to be retrieved based on VCAP_SERVICES
     * @param suffix is the action to be executed once the service was retrieved
     * @return a json
     * @throws InfrastructureException thrown when occur some error
     */
    public String invokeByGet(String serviceName, String suffix) {
        Executor executor = createExecutor(serviceName);

        try {
            URI uri = new URI(baseUrl + suffix).normalize();

            return executor
                        .execute(Request.Get(uri)
                            .setHeader("Accept", "application/json"))
                        .returnContent()
                        .asString();
        } catch (Exception ex) {
            throw new InfrastructureException(String
                    .format("Error when calling by GET the service: %s", serviceName), ex);
        }
    }

    /**
     * Based on the serviceName informed it makes the call to the service with POST HTTP method
     *
     * @param serviceName is the name of the service to be retrieved based on VCAP_SERVICES
     * @param suffix is the action to be executed once the service was retrieved
     * @param parameters all the parameters that should be included on the POST call
     * @return a json
     * @throws InfrastructureException thrown when occur some error
     */
    public String invokeByPost(String serviceName, String suffix, Map<String, String> parameters) {
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
            throw new InfrastructureException(String
                    .format("Error when calling by POST the service: %s", serviceName), ex);
        }
    }

    //responsible to create an instance of HttpClient Fluent Executor based on VCAP_SERVICES values
    private Executor createExecutor(String serviceName) {
        try {
//            String vCapServices = System.getenv(VCAP_SERVICES);
            String vCapServices = "{\n" +
                    "   \"relationship_extraction\": [\n" +
                    "      {\n" +
                    "         \"name\": \"relationship_extraction\",\n" +
                    "         \"label\": \"relationship_extraction\",\n" +
                    "         \"plan\": \"relationship_extraction_free_plan\",\n" +
                    "         \"credentials\": {\n" +
                    "            \"url\": \"https://gateway.watsonplatform.net/relationship-extraction-beta/api\",\n" +
                    "            \"sids\": [\n" +
                    "               {\n" +
                    "                  \"sid\": \"ie-es-news\",\n" +
                    "                  \"description\": \"information extraction from Spanish news\"\n" +
                    "               },\n" +
                    "               {\n" +
                    "                  \"sid\": \"ie-en-news\",\n" +
                    "                  \"description\": \"information extraction from English news\"\n" +
                    "               }\n" +
                    "            ],\n" +
                    "            \"password\": \"H35M85wxHgtB\",\n" +
                    "            \"username\": \"a19b6a76-f8fb-4cf8-b756-0f0916a262d1\"\n" +
                    "         }\n" +
                    "      }\n" +
                    "   ],\n" +
                    "   \"twitterinsights\": [\n" +
                    "      {\n" +
                    "         \"name\": \"insights_for_twitter\",\n" +
                    "         \"label\": \"twitterinsights\",\n" +
                    "         \"plan\": \"Free\",\n" +
                    "         \"credentials\": {\n" +
                    "            \"username\": \"238b96f8-3af8-4e0a-9996-401a2f7f167b\",\n" +
                    "            \"password\": \"8VRzSO3W07\",\n" +
                    "            \"host\": \"cdeservice.mybluemix.net\",\n" +
                    "            \"port\": 443,\n" +
                    "            \"url\": \"https://238b96f8-3af8-4e0a-9996-401a2f7f167b:8VRzSO3W07@cdeservice.mybluemix.net\"\n" +
                    "         }\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}";
            JSONObject vCap = (JSONObject) new JSONParser().parse(vCapServices);

            this.watson = (JSONArray) vCap.get(serviceName);
            this.watsonInstance = (JSONObject) watson.get(FIRST_ELEMENT);
            this.watsonCredentials = (JSONObject) watsonInstance.get(CREDENTIALS);


            this.username = (String) watsonCredentials.get(USERNAME);
            this.password = (String) watsonCredentials.get(PASSWORD);
            this.baseUrl = (String) watsonCredentials.get(URL);

            return Executor.newInstance().auth(username, password);
        } catch (Exception e) {
            throw new InfrastructureException("Error when creating the Http service client based on VCAP_SERVICES", e);
        }
    }
}
