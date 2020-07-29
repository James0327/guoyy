package com.jw.tcly;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jw.tcly.utils.SerializationUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: test com.tcly.test.TaxTest
 * @Package: com.tcly.utils
 * @ClassName: TaxTest
 * @Author: james.guo
 * @Date: 2019/4/28 11:19
 * @Version: 1.0
 */
public class TaxTest {
    @Setter
    @Getter
    class T1 implements Serializable {
        private int i;
        private long j;
        private transient long m;
    }

    @Setter
    @Getter
    class T2 extends T1 implements Serializable {
        private String k;
    }

    @Test
    public void test3() {
        String flightTime = "2019-12-30 17:48";
        System.out.println(flightTime.substring(0, 10));

        T1 t1 = new T1();
        t1.setI(1);
        t1.setJ(2);

        String serialize = SerializationUtil.serialize(t1);
        System.out.println(serialize);

        //		String str = "CAEQAhsc";
        String str = "CAETFA==";
        T1 deserialize = SerializationUtil.deserialize(str, T1.class);
        System.out.println(ToStringBuilder.reflectionToString(deserialize));
    }

    @Test
    public void test2() {
        String json = "[{\"code\":\"PA\",\"opt\":\"3\"},{\"code\":\"VE\",\"opt\":\"3\"}]";

        List<JSONObject> list = JSONObject.parseObject(json, List.class);

        System.out.println(list);
    }

    @Test
    public void test1() {
        byte b1 = 1;
        byte b2 = 2;

        int i1 = (int)b1;
        int i2 = (int)b2;

        System.out.println(i1);
        System.out.println(i2);
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "    \"journeys\": [{\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110705\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111340\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5823\",\n" +
                "            \"operatingFlightNo\": \"MU5823\",\n" +
                "            \"aircraft\": \"737\",\n" +
                "            \"duration\": 395,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 40,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"SQD\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 8060.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"LONCR2123\",\n" +
                "            \"currency\": \"GBP\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110705\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111340\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5823\",\n" +
                "            \"operatingFlightNo\": \"MU5823\",\n" +
                "            \"aircraft\": \"737\",\n" +
                "            \"duration\": 395,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 40,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"SQD\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 13232.00,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"YTOC421JQ\",\n" +
                "            \"currency\": \"CAD\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110705\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111340\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5823\",\n" +
                "            \"operatingFlightNo\": \"MU5823\",\n" +
                "            \"aircraft\": \"737\",\n" +
                "            \"duration\": 395,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 40,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"SQD\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 14440.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"SYDA821EQ\",\n" +
                "            \"currency\": \"AUD\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110705\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111340\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5823\",\n" +
                "            \"operatingFlightNo\": \"MU5823\",\n" +
                "            \"aircraft\": \"737\",\n" +
                "            \"duration\": 395,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 40,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"SQD\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 1095000.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"TYOJA28DX\",\n" +
                "            \"currency\": \"JPY\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110705\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111340\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5823\",\n" +
                "            \"operatingFlightNo\": \"MU5823\",\n" +
                "            \"aircraft\": \"737\",\n" +
                "            \"duration\": 395,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 40,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"SQD\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 10129.00,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"FLL1S21C9\",\n" +
                "            \"currency\": \"USD\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110820\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111435\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5821\",\n" +
                "            \"operatingFlightNo\": \"MU5821\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 375,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 50,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"DSN\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 8060.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"LONCR2123\",\n" +
                "            \"currency\": \"GBP\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110820\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111435\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5821\",\n" +
                "            \"operatingFlightNo\": \"MU5821\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 375,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 50,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"DSN\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 13232.00,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"YTOC421JQ\",\n" +
                "            \"currency\": \"CAD\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110820\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111435\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5821\",\n" +
                "            \"operatingFlightNo\": \"MU5821\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 375,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 50,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"DSN\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 14440.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"SYDA821EQ\",\n" +
                "            \"currency\": \"AUD\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110820\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111435\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5821\",\n" +
                "            \"operatingFlightNo\": \"MU5821\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 375,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 50,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"DSN\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 1095000.0,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"TYOJA28DX\",\n" +
                "            \"currency\": \"JPY\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"segments\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"CEI\",\n" +
                "            \"depAirportCode\": \"CEI\",\n" +
                "            \"depDateTime\": \"201907101510\",\n" +
                "            \"arrCityCode\": \"KMG\",\n" +
                "            \"arrAirportCode\": \"KMG\",\n" +
                "            \"arrDateTime\": \"201907101740\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU2598\",\n" +
                "            \"operatingFlightNo\": \"MU2598\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 90,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 0,\n" +
                "            \"stops\": [],\n" +
                "            \"mainSegment\": true,\n" +
                "            \"cabin\": \"R\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"segIndex\": 2,\n" +
                "            \"segType\": 1,\n" +
                "            \"depCityCode\": \"KMG\",\n" +
                "            \"depAirportCode\": \"KMG\",\n" +
                "            \"depDateTime\": \"201907110820\",\n" +
                "            \"arrCityCode\": \"HRB\",\n" +
                "            \"arrAirportCode\": \"HRB\",\n" +
                "            \"arrDateTime\": \"201907111435\",\n" +
                "            \"flightShare\": false,\n" +
                "            \"marketingFlightNo\": \"MU5821\",\n" +
                "            \"operatingFlightNo\": \"MU5821\",\n" +
                "            \"aircraft\": \"738\",\n" +
                "            \"duration\": 375,\n" +
                "            \"depDateVariation\": 0,\n" +
                "            \"arrDateVariation\": 0,\n" +
                "            \"stopTime\": 50,\n" +
                "            \"stops\": [{\n" +
                "                \"stopAirportCode\": \"DSN\",\n" +
                "                \"groundTime\": 0\n" +
                "            }],\n" +
                "            \"mainSegment\": false,\n" +
                "            \"cabin\": \"B\",\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"cabinCount\": 9,\n" +
                "            \"cabinSource\": \"BAM-19\"\n" +
                "        }],\n" +
                "        \"baggages\": [{\n" +
                "            \"segIndex\": 1,\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"freePieces\": 0,\n" +
                "            \"weight\": 0,\n" +
                "            \"weightUnit\": \"KG\"\n" +
                "        }],\n" +
                "        \"prices\": [{\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"nucPrice\": 19484,\n" +
                "            \"nucQ\": 0,\n" +
                "            \"price\": 140000,\n" +
                "            \"taxPrice\": 10129.00,\n" +
                "            \"qprice\": 0\n" +
                "        }],\n" +
                "        \"fares\": [{\n" +
                "            \"id\": 1361886850595815493,\n" +
                "            \"farebasis\": \"RLSRWSA\",\n" +
                "            \"passengerType\": \"ADT\",\n" +
                "            \"minPeopleMum\": 1,\n" +
                "            \"maxPeopleMum\": 9,\n" +
                "            \"cabinClass\": \"Y\",\n" +
                "            \"airline\": \"MU\",\n" +
                "            \"currency\": \"THB\",\n" +
                "            \"pcc\": \"SZV122\",\n" +
                "            \"nego\": 0,\n" +
                "            \"gds\": \"ATP\",\n" +
                "            \"maxStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 12,\n" +
                "                    \"unit\": \"M\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"minStay\": {\n" +
                "                \"periodLimit\": {\n" +
                "                    \"count\": 0,\n" +
                "                    \"unit\": \"D\"\n" +
                "                }\n" +
                "            }\n" +
                "        }],\n" +
                "        \"agency\": {\n" +
                "            \"pcc\": \"FLL1S21C9\",\n" +
                "            \"currency\": \"USD\"\n" +
                "        }\n" +
                "    }]\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(json);

        System.out.println(jsonObject);

    }

}
