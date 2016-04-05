package com.ibm.service;

import com.ibm.http.WatsonClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by peo_rboliveira on 05/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TwitterSearchServiceTest {

    @Mock
    private WatsonClient client;

    @InjectMocks
    private TwitterSearchService twitterSearchService;

    @Test
    public void testEmptyJsonResult() throws Exception {

        String jsonResult = "{\n" +
                "  \"search\": {\n" +
                "    \"results\": 0,\n" +
                "    \"current\": 0\n" +
                "  },\n" +
                "  \"tweets\": [],\n" +
                "  \"related\": {\n" +
                "    \"next\": {\n" +
                "      \"href\": \"https://cdeservice.mybluemix.net:443/api/v1/messages/search?q=from%3Arafaeu+AND+posted%3A2016-04-01%2C2016-04-04&from=0&size=5\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        when(client.invokeByGet(anyString(), anyString())).thenReturn(jsonResult);

        List<String> result = twitterSearchService.search("rafaeu", "2016-04-01", "2016-04-02");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testJsonResultWithSizeOne() throws Exception {

        String jsonResult = "{\n" +
                "  \"search\": {\n" +
                "    \"results\": 2,\n" +
                "    \"current\": 1\n" +
                "  },\n" +
                "  \"tweets\": [\n" +
                "    {\n" +
                "      \"cde\": {\n" +
                "        \"author\": {\n" +
                "          \"gender\": \"unknown\",\n" +
                "          \"parenthood\": {\n" +
                "            \"isParent\": \"unknown\",\n" +
                "            \"evidence\": \"\"\n" +
                "          },\n" +
                "          \"location\": {\n" +
                "            \"country\": \"United States\",\n" +
                "            \"city\": \"Chicago\",\n" +
                "            \"state\": \"Illinois\"\n" +
                "          },\n" +
                "          \"maritalStatus\": {\n" +
                "            \"isMarried\": \"unknown\",\n" +
                "            \"evidence\": \"\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"content\": {\n" +
                "          \"sentiment\": {\n" +
                "            \"evidence\": [\n" +
                "              {\n" +
                "                \"polarity\": \"NEGATIVE\",\n" +
                "                \"sentimentTerm\": \"overwhelming\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"polarity\": \"POSITIVE\",\n" +
                "                \"sentimentTerm\": \"worthwhile\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"polarity\": \"AMBIVALENT\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"cdeInternal\": {\n" +
                "        \"tracks\": [\n" +
                "          {\n" +
                "            \"id\": \"2713720a-f341-4ca0-acd1-f8b1e6c0d00f\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"message\": {\n" +
                "        \"postedTime\": \"2016-04-02T20:34:15.000Z\",\n" +
                "        \"verb\": \"post\",\n" +
                "        \"link\": \"http://twitter.com/dhh/statuses/716363133254811648\",\n" +
                "        \"inReplyTo\": {\n" +
                "          \"link\": \"http://twitter.com/LubaRaph/statuses/716351207934181376\"\n" +
                "        },\n" +
                "        \"generator\": {\n" +
                "          \"displayName\": \"Echofon\",\n" +
                "          \"link\": \"http://www.echofon.com/\"\n" +
                "        },\n" +
                "        \"body\": \"@LubaRaph @cmuratori The smugness was at times overwhelming, but the description was still worthwhile.\",\n" +
                "        \"favoritesCount\": 0,\n" +
                "        \"objectType\": \"activity\",\n" +
                "        \"actor\": {\n" +
                "          \"summary\": \"Creator of Ruby on Rails, Founder & CTO at Basecamp (formerly 37signals), NYT Best-selling author of REWORK and REMOTE, and Le Mans class-winning racing driver.\",\n" +
                "          \"image\": \"https://pbs.twimg.com/profile_images/2556368541/alng5gtlmjhrdlr3qxqv_normal.jpeg\",\n" +
                "          \"statusesCount\": 29601,\n" +
                "          \"utcOffset\": \"-18000\",\n" +
                "          \"languages\": [\n" +
                "            \"en\"\n" +
                "          ],\n" +
                "          \"preferredUsername\": \"dhh\",\n" +
                "          \"displayName\": \"DHH\",\n" +
                "          \"postedTime\": \"2008-04-27T20:19:25.000Z\",\n" +
                "          \"link\": \"http://www.twitter.com/dhh\",\n" +
                "          \"verified\": true,\n" +
                "          \"friendsCount\": 121,\n" +
                "          \"twitterTimeZone\": \"Central Time (US & Canada)\",\n" +
                "          \"favoritesCount\": 496,\n" +
                "          \"listedCount\": 8237,\n" +
                "          \"objectType\": \"person\",\n" +
                "          \"links\": [\n" +
                "            {\n" +
                "              \"rel\": \"me\",\n" +
                "              \"href\": \"http://david.heinemeierhansson.com\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"location\": {\n" +
                "            \"displayName\": \"Chicago, USA\",\n" +
                "            \"objectType\": \"place\"\n" +
                "          },\n" +
                "          \"id\": \"id:twitter.com:14561327\",\n" +
                "          \"followersCount\": 162335\n" +
                "        },\n" +
                "        \"provider\": {\n" +
                "          \"displayName\": \"Twitter\",\n" +
                "          \"link\": \"http://www.twitter.com\",\n" +
                "          \"objectType\": \"service\"\n" +
                "        },\n" +
                "        \"twitter_filter_level\": \"low\",\n" +
                "        \"twitter_entities\": {\n" +
                "          \"urls\": [],\n" +
                "          \"hashtags\": [],\n" +
                "          \"user_mentions\": [\n" +
                "            {\n" +
                "              \"indices\": [\n" +
                "                0,\n" +
                "                9\n" +
                "              ],\n" +
                "              \"screen_name\": \"LubaRaph\",\n" +
                "              \"id_str\": \"2883917482\",\n" +
                "              \"name\": \"Raphael Luba\",\n" +
                "              \"id\": 2883917482\n" +
                "            },\n" +
                "            {\n" +
                "              \"indices\": [\n" +
                "                10,\n" +
                "                20\n" +
                "              ],\n" +
                "              \"screen_name\": \"cmuratori\",\n" +
                "              \"id_str\": \"26452299\",\n" +
                "              \"name\": \"Casey Muratori\",\n" +
                "              \"id\": 26452299\n" +
                "            }\n" +
                "          ],\n" +
                "          \"symbols\": []\n" +
                "        },\n" +
                "        \"twitter_lang\": \"en\",\n" +
                "        \"id\": \"tag:search.twitter.com,2005:716363133254811648\",\n" +
                "        \"retweetCount\": 0,\n" +
                "        \"gnip\": {\n" +
                "          \"profileLocations\": [\n" +
                "            {\n" +
                "              \"geo\": {\n" +
                "                \"coordinates\": [\n" +
                "                  -87.65005,\n" +
                "                  41.85003\n" +
                "                ],\n" +
                "                \"type\": \"point\"\n" +
                "              },\n" +
                "              \"address\": {\n" +
                "                \"country\": \"United States\",\n" +
                "                \"subRegion\": \"Cook County\",\n" +
                "                \"countryCode\": \"US\",\n" +
                "                \"locality\": \"Chicago\",\n" +
                "                \"region\": \"Illinois\"\n" +
                "              },\n" +
                "              \"displayName\": \"Chicago, Illinois, United States\",\n" +
                "              \"objectType\": \"place\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"language\": {\n" +
                "            \"value\": \"en\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"object\": {\n" +
                "          \"summary\": \"@LubaRaph @cmuratori The smugness was at times overwhelming, but the description was still worthwhile.\",\n" +
                "          \"postedTime\": \"2016-04-02T20:34:15.000Z\",\n" +
                "          \"link\": \"http://twitter.com/dhh/statuses/716363133254811648\",\n" +
                "          \"id\": \"object:search.twitter.com,2005:716363133254811648\",\n" +
                "          \"objectType\": \"note\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"related\": {\n" +
                "    \"next\": {\n" +
                "      \"href\": \"https://cdeservice.mybluemix.net:443/api/v1/messages/search?q=from%3Adhh+AND+posted%3A2016-04-01%2C2016-04-04&from=1&size=1\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        when(client.invokeByGet(anyString(), anyString())).thenReturn(jsonResult);

        List<String> result = twitterSearchService.search("rafaeu", "2016-04-01", "2016-04-02");

        assertFalse(result.isEmpty());
        assertTrue(result.size() == 1);

        String expected = "@LubaRaph @cmuratori The smugness was at times overwhelming, but the description was still worthwhile.";

        assertEquals(expected, result.get(0));
    }
}