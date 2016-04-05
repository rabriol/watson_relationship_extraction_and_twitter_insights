package com.ibm.service;

import com.ibm.http.WatsonClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by peo_rboliveira on 05/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RelationShipExtractionServiceTest {
    @Mock
    private WatsonClient client;

    @InjectMocks
    private RelationShipExtractionService relationShipExtractionService;

    @Test
    public void testJsonResultSizeIsNotEmpty() throws Exception {

        String jsonResult = "{\n" +
                " \"doc\": {\n" +
                "  \"entities\": {\n" +
                "   \"entity\": [\n" +
                "    {\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"eid\": \"-E0\",\n" +
                "     \"generic\": false,\n" +
                "     \"level\": \"NAM\",\n" +
                "     \"mentref\": [\n" +
                "      {\n" +
                "       \"mid\": \"-M0\",\n" +
                "       \"text\": \"Bryce Harper\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"score\": 1.0,\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"PERSON\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"eid\": \"-E1\",\n" +
                "     \"generic\": false,\n" +
                "     \"level\": \"NAM\",\n" +
                "     \"mentref\": [\n" +
                "      {\n" +
                "       \"mid\": \"-M1\",\n" +
                "       \"text\": \"Denard Span\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"score\": 1.0,\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"PERSON\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"eid\": \"-E2\",\n" +
                "     \"generic\": false,\n" +
                "     \"level\": \"NOM\",\n" +
                "     \"mentref\": [\n" +
                "      {\n" +
                "       \"mid\": \"-M2\",\n" +
                "       \"text\": \"league\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"score\": 1.0,\n" +
                "     \"subtype\": \"SPORTS\",\n" +
                "     \"type\": \"ORGANIZATION\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"id\": \"\",\n" +
                "  \"mentions\": {\n" +
                "   \"mention\": [\n" +
                "    {\n" +
                "     \"begin\": 0,\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"corefScore\": 1.0,\n" +
                "     \"eid\": \"-E0\",\n" +
                "     \"end\": 11,\n" +
                "     \"etype\": \"PERSON\",\n" +
                "     \"head-begin\": 0,\n" +
                "     \"head-end\": 11,\n" +
                "     \"metonymy\": false,\n" +
                "     \"mid\": \"-M0\",\n" +
                "     \"mtype\": \"NAM\",\n" +
                "     \"role\": \"PERSON\",\n" +
                "     \"score\": 0.98617954254232764733,\n" +
                "     \"text\": \"Bryce Harper\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"begin\": 17,\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"corefScore\": 1.0,\n" +
                "     \"eid\": \"-E1\",\n" +
                "     \"end\": 27,\n" +
                "     \"etype\": \"PERSON\",\n" +
                "     \"head-begin\": 17,\n" +
                "     \"head-end\": 27,\n" +
                "     \"metonymy\": false,\n" +
                "     \"mid\": \"-M1\",\n" +
                "     \"mtype\": \"NAM\",\n" +
                "     \"role\": \"PERSON\",\n" +
                "     \"score\": 0.89558320235391641884,\n" +
                "     \"text\": \"Denard Span\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"begin\": 38,\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"corefScore\": 1.0,\n" +
                "     \"eid\": \"-E2\",\n" +
                "     \"end\": 43,\n" +
                "     \"etype\": \"ORGANIZATION\",\n" +
                "     \"head-begin\": 38,\n" +
                "     \"head-end\": 43,\n" +
                "     \"metonymy\": false,\n" +
                "     \"mid\": \"-M2\",\n" +
                "     \"mtype\": \"NOM\",\n" +
                "     \"role\": \"ORGANIZATION\",\n" +
                "     \"score\": 0.55084657679338322467,\n" +
                "     \"text\": \"league\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"relations\": {\n" +
                "   \"relation\": [\n" +
                "    {\n" +
                "     \"rel_entity_arg\": [\n" +
                "      {\n" +
                "       \"argnum\": 1,\n" +
                "       \"eid\": \"-E1\"\n" +
                "      },\n" +
                "      {\n" +
                "       \"argnum\": 2,\n" +
                "       \"eid\": \"-E2\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"relmentions\": {\n" +
                "      \"relmention\": [\n" +
                "       {\n" +
                "        \"class\": \"SPECIFIC\",\n" +
                "        \"modality\": \"ASSERTED\",\n" +
                "        \"rel_mention_arg\": [\n" +
                "         {\n" +
                "          \"argnum\": 1,\n" +
                "          \"mid\": \"-M1\",\n" +
                "          \"text\": \"Denard Span\"\n" +
                "         },\n" +
                "         {\n" +
                "          \"argnum\": 2,\n" +
                "          \"mid\": \"-M2\",\n" +
                "          \"text\": \"league\"\n" +
                "         }\n" +
                "        ],\n" +
                "        \"rmid\": \"-R1-1\",\n" +
                "        \"score\": 0.31088749686879701972,\n" +
                "        \"tense\": \"UNSPECIFIED\"\n" +
                "       }\n" +
                "      ]\n" +
                "     },\n" +
                "     \"rid\": \"-R1\",\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"employedBy\"\n" +
                "    }\n" +
                "   ],\n" +
                "   \"version\": \"KLUE2_cascaded:2011_10_25\"\n" +
                "  },\n" +
                "  \"sents\": {\n" +
                "   \"sent\": [\n" +
                "    {\n" +
                "     \"begin\": 0,\n" +
                "     \"dependency_parse\": \"Bryce NNP 1 -I Harper NNP 4 NP and CC 4 -E Denard NNP 4 -I Span NNP 5 NP lead VBP -1 VP the DT 7 -I league NN 5 NP in IN 7 PP home NN 10 -I runs NNS 8 NP \",\n" +
                "     \"end\": 10,\n" +
                "     \"parse\": {\n" +
                "      \"score\": -8.3254841581277787554,\n" +
                "      \"text\": \"[S [NP [NP Bryce_NNP Harper_NNP NP] and_CC [NP Denard_NNP Span_NNP NP] NP] [VP lead_VBP [NP [NP the_DT league_NN NP] [PP in_IN [NP home_NN runs_NNS NP] PP] NP] VP] S]\"\n" +
                "     },\n" +
                "     \"sid\": 0,\n" +
                "     \"text\": \"Bryce Harper and Denard Span lead the league in home runs\",\n" +
                "     \"tokens\": {\n" +
                "      \"token\": [\n" +
                "       {\n" +
                "        \"POS\": \"NNP\",\n" +
                "        \"begin\": 0,\n" +
                "        \"end\": 4,\n" +
                "        \"lemma\": \"bryce\",\n" +
                "        \"text\": \"Bryce\",\n" +
                "        \"tid\": 0,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NNP\",\n" +
                "        \"begin\": 6,\n" +
                "        \"end\": 11,\n" +
                "        \"lemma\": \"harper\",\n" +
                "        \"text\": \"Harper\",\n" +
                "        \"tid\": 1,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"CC\",\n" +
                "        \"begin\": 13,\n" +
                "        \"end\": 15,\n" +
                "        \"lemma\": \"and\",\n" +
                "        \"text\": \"and\",\n" +
                "        \"tid\": 2,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NNP\",\n" +
                "        \"begin\": 17,\n" +
                "        \"end\": 22,\n" +
                "        \"lemma\": \"denard\",\n" +
                "        \"text\": \"Denard\",\n" +
                "        \"tid\": 3,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NNP\",\n" +
                "        \"begin\": 24,\n" +
                "        \"end\": 27,\n" +
                "        \"lemma\": \"pan\",\n" +
                "        \"text\": \"Span\",\n" +
                "        \"tid\": 4,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"VBP\",\n" +
                "        \"begin\": 29,\n" +
                "        \"end\": 32,\n" +
                "        \"lemma\": \"lead\",\n" +
                "        \"text\": \"lead\",\n" +
                "        \"tid\": 5,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"DT\",\n" +
                "        \"begin\": 34,\n" +
                "        \"end\": 36,\n" +
                "        \"lemma\": \"the\",\n" +
                "        \"text\": \"the\",\n" +
                "        \"tid\": 6,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NN\",\n" +
                "        \"begin\": 38,\n" +
                "        \"end\": 43,\n" +
                "        \"lemma\": \"league\",\n" +
                "        \"text\": \"league\",\n" +
                "        \"tid\": 7,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"IN\",\n" +
                "        \"begin\": 45,\n" +
                "        \"end\": 46,\n" +
                "        \"lemma\": \"in\",\n" +
                "        \"text\": \"in\",\n" +
                "        \"tid\": 8,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NN\",\n" +
                "        \"begin\": 48,\n" +
                "        \"end\": 51,\n" +
                "        \"lemma\": \"home\",\n" +
                "        \"text\": \"home\",\n" +
                "        \"tid\": 9,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NNS\",\n" +
                "        \"begin\": 53,\n" +
                "        \"end\": 56,\n" +
                "        \"lemma\": \"run\",\n" +
                "        \"text\": \"runs\",\n" +
                "        \"tid\": 10,\n" +
                "        \"type\": 4096\n" +
                "       }\n" +
                "      ]\n" +
                "     },\n" +
                "     \"usd_dependency_parse\": \"Bryce NNP 1 name Harper NNP 4 npmod and CC 4 cc Denard NNP 4 name Span NNP 5 nsubj lead VBP -1 root the DT 7 det league NN 5 dobj in IN 10 case home NN 10 compound runs NNS 7 nmod\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"sgml_char_info\": null,\n" +
                "  \"sgml_sent_info\": null,\n" +
                "  \"text\": \"Bryce Harper and Denard Span lead the league in home runs\"\n" +
                " }\n" +
                "}";

        when(client.invokeByPost(anyString(), anyString(), anyMap())).thenReturn(jsonResult);

        String parameter = "Bryce Harper and Denard Span lead the league in home runs";

        List<String> result = relationShipExtractionService.extract(parameter);

        assertFalse(result.isEmpty());
        assertEquals(result.size(), 2);
        assertTrue(result.contains("Bryce Harper"));
        assertTrue(result.contains("Denard Span"));
    }

    @Test
    public void testJsonResultIsEmpty() throws Exception {

        String jsonResult = "{\n" +
                " \"doc\": {\n" +
                "  \"entities\": {\n" +
                "   \"entity\": [\n" +
                "    {\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"eid\": \"-E0\",\n" +
                "     \"generic\": false,\n" +
                "     \"level\": \"NAM\",\n" +
                "     \"mentref\": [\n" +
                "      {\n" +
                "       \"mid\": \"-M1\",\n" +
                "       \"text\": \"Paris\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"score\": 1.0,\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"GPE\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"eid\": \"-E1\",\n" +
                "     \"generic\": false,\n" +
                "     \"level\": \"PRO\",\n" +
                "     \"mentref\": [\n" +
                "      {\n" +
                "       \"mid\": \"-M0\",\n" +
                "       \"text\": \"I\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"score\": 1.0,\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"PERSON\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"id\": \"\",\n" +
                "  \"mentions\": {\n" +
                "   \"mention\": [\n" +
                "    {\n" +
                "     \"begin\": 0,\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"corefScore\": 1.0,\n" +
                "     \"eid\": \"-E1\",\n" +
                "     \"end\": 0,\n" +
                "     \"etype\": \"PERSON\",\n" +
                "     \"head-begin\": 0,\n" +
                "     \"head-end\": 0,\n" +
                "     \"metonymy\": false,\n" +
                "     \"mid\": \"-M0\",\n" +
                "     \"mtype\": \"PRO\",\n" +
                "     \"role\": \"PERSON\",\n" +
                "     \"score\": 0.9965128704675197957,\n" +
                "     \"text\": \"I\"\n" +
                "    },\n" +
                "    {\n" +
                "     \"begin\": 25,\n" +
                "     \"class\": \"SPC\",\n" +
                "     \"corefScore\": 1.0,\n" +
                "     \"eid\": \"-E0\",\n" +
                "     \"end\": 29,\n" +
                "     \"etype\": \"GPE\",\n" +
                "     \"head-begin\": 25,\n" +
                "     \"head-end\": 29,\n" +
                "     \"metonymy\": false,\n" +
                "     \"mid\": \"-M1\",\n" +
                "     \"mtype\": \"NAM\",\n" +
                "     \"role\": \"LOCATION\",\n" +
                "     \"score\": 0.87710312729578643332,\n" +
                "     \"text\": \"Paris\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"relations\": {\n" +
                "   \"relation\": [\n" +
                "    {\n" +
                "     \"rel_entity_arg\": [\n" +
                "      {\n" +
                "       \"argnum\": 1,\n" +
                "       \"eid\": \"-E1\"\n" +
                "      },\n" +
                "      {\n" +
                "       \"argnum\": 2,\n" +
                "       \"eid\": \"-E0\"\n" +
                "      }\n" +
                "     ],\n" +
                "     \"relmentions\": {\n" +
                "      \"relmention\": [\n" +
                "       {\n" +
                "        \"class\": \"SPECIFIC\",\n" +
                "        \"modality\": \"ASSERTED\",\n" +
                "        \"rel_mention_arg\": [\n" +
                "         {\n" +
                "          \"argnum\": 1,\n" +
                "          \"mid\": \"-M0\",\n" +
                "          \"text\": \"I\"\n" +
                "         },\n" +
                "         {\n" +
                "          \"argnum\": 2,\n" +
                "          \"mid\": \"-M1\",\n" +
                "          \"text\": \"Paris\"\n" +
                "         }\n" +
                "        ],\n" +
                "        \"rmid\": \"-R1-1\",\n" +
                "        \"score\": 0.6199742923809924422,\n" +
                "        \"tense\": \"UNSPECIFIED\"\n" +
                "       }\n" +
                "      ]\n" +
                "     },\n" +
                "     \"rid\": \"-R1\",\n" +
                "     \"subtype\": \"OTHER\",\n" +
                "     \"type\": \"locatedAt\"\n" +
                "    }\n" +
                "   ],\n" +
                "   \"version\": \"KLUE2_cascaded:2011_10_25\"\n" +
                "  },\n" +
                "  \"sents\": {\n" +
                "   \"sent\": [\n" +
                "    {\n" +
                "     \"begin\": 0,\n" +
                "     \"dependency_parse\": \"I PRP 2 NP 'm VBP 2 -E going VBG -1 VP on IN 2 PP vacation NN 3 NP to TO 2 PP Paris NNP 5 NP \",\n" +
                "     \"end\": 6,\n" +
                "     \"parse\": {\n" +
                "      \"score\": -4.5472423164342572477,\n" +
                "      \"text\": \"[S [NP I_PRP NP] [VP 'm_VBP [VP going_VBG [PP on_IN [NP vacation_NN NP] PP] [PP to_TO [NP Paris_NNP NP] PP] VP] VP] S]\"\n" +
                "     },\n" +
                "     \"sid\": 0,\n" +
                "     \"text\": \"I'm going on vacation to Paris\",\n" +
                "     \"tokens\": {\n" +
                "      \"token\": [\n" +
                "       {\n" +
                "        \"POS\": \"PRP\",\n" +
                "        \"begin\": 0,\n" +
                "        \"end\": 0,\n" +
                "        \"lemma\": \"i\",\n" +
                "        \"text\": \"I\",\n" +
                "        \"tid\": 0,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"VBP\",\n" +
                "        \"begin\": 1,\n" +
                "        \"end\": 2,\n" +
                "        \"lemma\": \"be\",\n" +
                "        \"text\": \"'m\",\n" +
                "        \"tid\": 1,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"VBG\",\n" +
                "        \"begin\": 4,\n" +
                "        \"end\": 8,\n" +
                "        \"lemma\": \"go\",\n" +
                "        \"text\": \"going\",\n" +
                "        \"tid\": 2,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"IN\",\n" +
                "        \"begin\": 10,\n" +
                "        \"end\": 11,\n" +
                "        \"lemma\": \"on\",\n" +
                "        \"text\": \"on\",\n" +
                "        \"tid\": 3,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NN\",\n" +
                "        \"begin\": 13,\n" +
                "        \"end\": 20,\n" +
                "        \"lemma\": \"vacation\",\n" +
                "        \"text\": \"vacation\",\n" +
                "        \"tid\": 4,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"TO\",\n" +
                "        \"begin\": 22,\n" +
                "        \"end\": 23,\n" +
                "        \"lemma\": \"to\",\n" +
                "        \"text\": \"to\",\n" +
                "        \"tid\": 5,\n" +
                "        \"type\": 0\n" +
                "       },\n" +
                "       {\n" +
                "        \"POS\": \"NNP\",\n" +
                "        \"begin\": 25,\n" +
                "        \"end\": 29,\n" +
                "        \"lemma\": \"paris\",\n" +
                "        \"text\": \"Paris\",\n" +
                "        \"tid\": 6,\n" +
                "        \"type\": 4096\n" +
                "       }\n" +
                "      ]\n" +
                "     },\n" +
                "     \"usd_dependency_parse\": \"I PRP 2 nsubj 'm VBP 2 aux going VBG -1 root on IN 4 case vacation NN 2 nmod to TO 6 case Paris NNP 2 nmod\"\n" +
                "    }\n" +
                "   ]\n" +
                "  },\n" +
                "  \"sgml_char_info\": null,\n" +
                "  \"sgml_sent_info\": null,\n" +
                "  \"text\": \"I'm going on vacation to Paris\"\n" +
                " }\n" +
                "}\n";

        when(client.invokeByPost(anyString(), anyString(), anyMap())).thenReturn(jsonResult);

        String parameter = "I'm going on vacation to Paris";

        List<String> result = relationShipExtractionService.extract(parameter);

        assertTrue(result.isEmpty());
    }

}