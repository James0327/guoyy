package com.jw.james;

import com.alibaba.fastjson.JSON;
import com.github.luben.zstd.Zstd;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.assertj.core.util.Sets;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.Base64;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.Test1
 * @Author: guoyiyong/james
 * @Date: 2020-06-28 23:16
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
public class MyTest {
    class T {
        private Map<String, String> map;

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

    @org.junit.jupiter.api.Test
    public void test6() throws Exception {
        String password = "XFPOFVQJDLOSYLINVBZKGLWAWIDGDVG2O3THJQYAVE24WAOKKY3Q";
        String projectId = "bjiflihgt.java.iflight.schedule.core";

        String decrypt = com.ly.dal.util.StringEncrypt.decrypt(password, projectId);
        // decrypt: XKKz56VCKlnJkjGvyfQNs
        System.out.println("decrypt: " + decrypt);
    }

    @Test
    public void test5() {
        String json = "{\"baggageInfo\":{\"baggageRuleList\":[{\"freeBaggageAllowance\":\"2PC\",\"globalSymbol\":\"EH\",\"invalidTime\":\"2022-08-19\",\"parkPermitted\":true,\"passengerType\":\"ADT\",\"powerTime\":\"2022-08-19\",\"segmentNum\":1,\"suitableCabinCodeWhenTransfer\":\"J\"}]},\"createDate\":\"2022-06-22 17:52:11\",\"engineType\":\"1T\",\"fareBasisInfos\":[{\"airline\":\"MU\",\"arrival\":\"SEL\",\"departure\":\"SHA\",\"direction\":\"TVL\",\"fareBasis\":\"JSE0WCN7\",\"fareType\":\"PUBLIC\",\"passengerType\":\"ADT\",\"referenceOne\":\"key(RuleTariff::Rule::OWRT::Routing::Footnote1::Footnote2::PassengerType::FareType::OriginAddonTariff::OriginAddonFootnote1::OriginAddonFootnote2::DestinationAddonTariff::DestinationAddonFootnote1::DestinationAddonFootnote2::TravelAgencyCode::IataNumber::DepartmentCode::Origin::Destination::FareSource::FbrBaseTariff::FbrBaseRule::AccountCode::FbrBaseFareBasis::OriginAddonRouting::DestinationAddonRouting::R3c025TblNo)\",\"referenceTwo\":\"008::CKR1::1::0030::6B::::ADT::BR ::::::::::::::SZV122::08318995::::SHA::SEL::ATPCO::::::::::::::\"}],\"flightSegments\":[{\"aircode\":\"MU\",\"arrival\":\"ICN\",\"arrivalCityCode\":\"SEL\",\"arrivalDate\":\"2022-08-19T13:55:00\",\"bag\":{\"allowedPieces\":\"2\",\"allowedWeight\":\"\"},\"cabin\":\"J\",\"cabinClass\":\"C\",\"departure\":\"PVG\",\"departureCityCode\":\"SHA\",\"departureDate\":\"2022-08-19T10:50:00\",\"equipType\":\"77W\",\"flightNumber\":\"5041\",\"flightShare\":false,\"isMainSegment\":true,\"operatingFlightNumber\":\"MU5041\",\"orgiDestSeqID\":1,\"segmentSeqID\":1,\"stopTime\":0}],\"iataNo\":\"08318995\",\"isCHDChanged\":\"false\",\"isFareChanged\":\"false\",\"isINFChanged\":\"false\",\"isNoFare\":\"false\",\"isQChanged\":\"false\",\"isTaxChanged\":\"false\",\"merchantId\":\"64\",\"miss\":65792,\"msgId\":\"AC1A436100002A9F00002A343286A6B3\",\"passengerQuantityInfos\":[{\"passengerQuantity\":1,\"passengerType\":\"ADT\"}],\"pcc\":\"SZV122\",\"policyIds\":[\"010000646863174551138422784\"],\"priceInfos\":[{\"baseFare\":{\"currencyCode\":\"CNY\",\"exPrice\":\"5730\",\"nowPrice\":\"5730\"},\"fareBasisCodes\":[\"JSE0WCN7\"],\"fareBasisInfoBOList\":[{\"airline\":\"MU\",\"cat192535\":\"\",\"cat25\":\"\",\"fareBasis\":\"JSE0WCN7\",\"farePtcType\":\"ADT\",\"fareRule\":\"CKR1\",\"fareType\":\"BR \",\"gi\":\"EH\",\"orgDestSeqID\":1,\"segIndex\":0,\"tariff\":\"008\"}],\"fareRules\":[\"CKR1\"],\"fareTypes\":[\"BR \"],\"passengerType\":\"ADT\",\"priceType\":\"PUBLIC\",\"q\":{\"currencyCode\":\"CNY\",\"exQ\":\"0\",\"nowQ\":\"0\"},\"tariffs\":[\"008\"],\"tax\":{\"currencyCode\":\"CNY\",\"exTax\":\"477.00\",\"nowTax\":\"477\"},\"taxes\":[{\"amount\":\"90\",\"currencyCode\":\"CNY\",\"taxCode\":\"CN\"},{\"amount\":\"360\",\"currencyCode\":\"CNY\",\"taxCode\":\"YQ\"},{\"amount\":\"27\",\"currencyCode\":\"CNY\",\"taxCode\":\"YQ\"}],\"tourcode\":\"\"}],\"refId\":\"183638085580386304\",\"resultStatus\":\"4\",\"sourceTag\":\"Engine\",\"ticketCarrier\":\"MU\",\"ticketLocation\":\"SZV\",\"traceId\":\"AP20220622174920QXH7UDZO6DVT7BCYTNKI3QWFCHIG2UTFUSNIIP218ZGNZBP1\",\"tripType\":\"OW\",\"unitKey\":\"X1T/V3/IBE/1~H4sIAAAAAAAAACWMwQ6DMAxDP8Y95pC0UMoRukoVGyDBEOLCl/TjSbdYliwnLyzeW2YWewumPfEZlw7jhvkAc0B8b6LBMfyIC3seaE8fmg9KmfSA6o7IOGODBMt90/S2a4P0jWNj4JAy6rBaaYU1/tV2+na556PE5SrD64vqX6sqjAkRUsF7Pct6aitFHqbb9ZiyAAAA~A8458DF557E9AEEC26F0D1D41488C22C\",\"unitKeyEntityDTO\":{\"fareMetaInfo\":{\"dataVersion\":\"0166200012\"},\"fares\":[{\"carrier\":\"MU\",\"direction\":1,\"discount\":false,\"fareBasis\":\"JSE0WCN7\",\"fareGenType\":\"0\",\"fareItinerary\":\"SHASEL\",\"fareRecordId\":\"SHA,SEL,MU,EH,008,0030,,$3$2818209449275819430$$\",\"fareType\":\"BR\",\"fcIndexInJr\":0,\"fcIndexInPu\":0,\"flightSegIndexes\":[0],\"footnote\":\"6B\",\"gi\":\"EH\",\"isPub\":true,\"owrt\":3,\"puIndexInJr\":0,\"rtgNo\":\"0030\",\"ruleNo\":\"CKR1\",\"saleAmount\":5730,\"tariff\":\"008\"}],\"jrInfo\":{\"fcTotal\":1,\"jrType\":\"OW\",\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}]},\"quotationInfo\":{\"currency\":\"CNY\",\"passengerPrices\":[{\"fsdPrice\":5730,\"lvlPrice\":0,\"passengerType\":\"ADT\",\"ptcQualification\":\"ADT\",\"surcharge\":0}],\"tickingCarrier\":\"MU\"}},\"validateGds\":\"1E\"}";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        byte[] compress = Zstd.compress(bytes);
        String s = Base64.getEncoder().encodeToString(compress);
        System.out.println(bytes.length + "/" + compress.length + "/" + s.getBytes(StandardCharsets.UTF_8).length);

        T t = new T();
        Map<String, String> map = Maps.newConcurrentMap();
        t.setMap(map);

        map.put("B", "bbb");
        map.put("A", "a");
        t1(map);
        System.out.println(map);
    }

    public void t1(Map<String, String> map) {
        map.put("A", "aaa");
    }

    @Test
    public void test4() {

        Comparator<Double> comparator = Comparator.reverseOrder();
        Map<Double, Integer> distanceMap = Maps.newTreeMap(comparator);

        distanceMap.put(22D, 1);
        distanceMap.put(33D, 1);
        distanceMap.put(24D, 1);
        distanceMap.put(2434D, 1);
        distanceMap.put(233D, 1);
        distanceMap.put(2D, 1);
        distanceMap.put(1D, 1);

        System.out.println(distanceMap);

        Set<String> countryCodeSet = com.google.common.collect.Sets.newHashSet();

        countryCodeSet.add("US");

        boolean us = countryCodeSet.contains("US");
        System.out.println(us);

        System.out.println(countryCodeSet.toString());

        BitSet bs = new BitSet();
        bs.set(0, 8);
        System.out.println(bs);

        System.out.println();

        String str = "//02/0GO";
        int len = str.length();
        System.out.println(str.charAt(0));
        System.out.println(str.charAt(1));
        System.out.println(str.charAt(len - 4));
        System.out.println(str.substring(len - 3));
    }

    @Test
    public void test3() {

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));
        java.time.LocalDateTime time = java.time.LocalDateTime.parse("2021-08-22T16:00:00.000Z", formatter);
        System.out.println(time);

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTime dateTime = dateTimeFormatter
                .withOffsetParsed().parseDateTime("2021-08-22T16:00:00.000+00")
                // .toDateTime(DateTimeZone.forOffsetHours(8))
                .toDateTime(DateTimeZone.forID("+08:00"));
        System.out.println(dateTime.toString());

        DateTime dateTime1 = DateTime.parse("2021-08-22T16:00:00.000+00", dateTimeFormatter)
                .toDateTime(DateTimeZone.forOffsetHours(8));
        System.out.println(dateTime1);

        String ddMMMyy = StringUtils.upperCase(LocalDate.now().toString("ddMMMyy", Locale.ENGLISH));
        System.out.println(ddMMMyy);

        Set<String> s1 = Sets.newLinkedHashSet();
        Set<String> s2 = Sets.newHashSet();
        s1.add("s1");
        s1.add("s11");
        s1.add("s12");
        s1.add("s13");
        s1.add("s19");
        s1.add("s16");

        s2.add("s19");
        s2.add("s16");
        s2.add("s13");
        s2.add("s12");
        s2.add("s11");
        s2.add("s1");

        System.out.println(SetUtils.isEqualSet(s1, s2));

        byte b = 1;
        int i = 1;

        boolean flag = b == i;
        System.out.println("flag: " + flag);

        BitSet bs = new BitSet();
        bs.set(0, false);
        bs.set(1, true);
        bs.set(128, true);

        System.out.println("bs: " + bs);
    }

    @Test
    public void test2() {
        Map<String, Map<String, String>> cache = Maps.newConcurrentMap();
        Map<String, Map<String, String>> cache2 = Maps.newHashMap();
        String val = cache.computeIfAbsent("key1",
                k -> Maps.newConcurrentMap())
                .put("field1", "xxx");
        System.out.println("val: " + val);

        String val2 = cache2.computeIfAbsent("key2",
                k -> Maps.newHashMap())
                .put("field2", "xxxxxx");
        System.out.println("val: " + val2);

        System.out.println(cache);
        System.out.println(cache2);
        log.info("cache:{}", cache);
        log.info("cache2:{}", cache2);
    }

    @Test
    public void test() {
        Map<String, Map<String, Long>> cache = Maps.newConcurrentMap();
        cache.computeIfAbsent("OD", k -> Maps.newConcurrentMap()).put("fareId", 1L);

        System.out.println(cache.toString());
        System.out.println(ToStringBuilder.reflectionToString(cache));

        boolean yq = isYq("YQI112.50EUR");

        System.out.println("yq: " + yq);
    }

    /**
     * YQI YQF YRI YRF
     *
     * @param s
     * @return
     */
    private boolean isYq(String s) {
        char ch = s.charAt(0);
        if (ch == 'Y') {
            ch = s.charAt(1);
            if (ch == 'Q' || ch == 'R') {
                ch = s.charAt(2);
                return ch == 'I' || ch == 'F';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n1 = 1;
        int d2 = 3;

        System.out.println((double)n1 / d2);

        BigDecimal bd1 = new BigDecimal(1);
        BigDecimal bd2 = new BigDecimal(2);

        BigDecimal bd3 = bd1.add(bd2);

        System.out.println(String.format("%s, %s, %s.", bd1, bd2, bd3));

        String dictString1 = "YQI";
        String key1 = dictString1.substring(0, 2);

        String dictString2 = "001CN";
        String key2 = dictString2.substring(dictString2.length() - 2);

        System.out.println(String.format("%s, %s.", key1, key2));

        List<String> arr = Lists.newArrayList();
        arr.add(null);

        System.out.println(JSON.toJSONString(arr));

        System.exit(1);

        int len = 9;
        int sectors = (1 << len) - 1;

        System.out.println("sectors: " + Integer.toBinaryString(sectors));

        java.time.format.DateTimeFormatter dateTimeFormat0 = java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHH00");
        String date0 = java.time.LocalDateTime.now().minusHours(12).format(dateTimeFormat0);
        System.out.println(date0);

        org.joda.time.format.DateTimeFormatter dateTimeFormat1 = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMddHH00");
        String date1 = org.joda.time.DateTime.now().minusHours(12).toString(dateTimeFormat1);
        System.out.println(date1);

        BitSet b1 = new BitSet();

        b1.set(5, 8);

        System.out.println(b1.size());
        System.out.println(b1.cardinality());
        System.out.println(b1.length());
        byte[] bytes = b1.toByteArray();

        System.out.println(Integer.toBinaryString(bytes[0]));

        int i1 = b1.nextSetBit(0);

        int i2 = b1.previousClearBit(b1.length());

        System.out.println(b1);
        System.out.println(String.format("i1:%d,i2:%d.", i1, i2));

        StringUtils.trimToEmpty(null);
    }

    @Test
    public void test1() {
        int i0 = 0;
        i0 |= 1 << 0;
        i0 |= 1 << 1;
        i0 |= 1 << 2;
        i0 |= 1 << 3;
        i0 |= 1 << 4;
        i0 |= 1 << 5;

        int i1 = 0;
        i1 |= 1 << 0;
        i1 |= 1 << 2;
        i1 |= 1 << 3;

        int i2 = 0;
        i2 |= 1 << 4;
        i2 |= 1 << 5;

        System.out.println(Integer.toBinaryString(i0));
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i2));

        System.out.println(minFltIdx(i0));
        System.out.println(minFltIdx(i1));
        System.out.println(minFltIdx(i2));

        System.out.println("~~~~~~~~~");

        System.out.println(maxFltIdx(i0));
        System.out.println(maxFltIdx(i1));
        System.out.println(maxFltIdx(i2));

        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(-i1));
        System.out.println(Integer.toBinaryString(~i1));

        System.out.println(Integer.toBinaryString(i2));
        System.out.println(Integer.toBinaryString(-i2));
        System.out.println(Integer.toBinaryString(~i2));

    }

    private int minFltIdx(int set) {
        if (set <= 0) {
            return -1;
        }
        int t = set;
        int idx = 1;
        while (true) {
            if ((t & 1) == 1) {
                break;
            }
            t = t >> 1;
            idx++;
        }
        return idx;
    }

    private int maxFltIdx(int set) {
        if (set <= 0) {
            return -1;
        }
        int t = set;
        int idx = 1;
        while (true) {
            System.out.println("t: " + t);
            if (t >> idx == 0) {
                break;
            }
            idx++;
        }
        return idx;
    }

}
