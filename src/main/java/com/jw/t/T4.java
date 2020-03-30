package com.jw.t;

import com.jw.BaggageReturn;
import com.jw.BaggageReturnUnion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: test com.jw.t.T4
 * @Package: com.jw
 * @ClassName: T4
 * @Author: james.guo
 * @Date: 2019/3/18 22:48
 * @Version: 1.0
 */
public class T4 {

    public static void main(String[] args) {

        List<BaggageReturnUnion> returnList0 = new ArrayList<>();

        BaggageReturnUnion baggageReturnUnion0 = new BaggageReturnUnion();
        BaggageReturnUnion baggageReturnUnion1 = new BaggageReturnUnion();

        returnList0.add(baggageReturnUnion0);
        returnList0.add(baggageReturnUnion1);

        List<BaggageReturn> baggageReturnList0 = new ArrayList<>();
        baggageReturnUnion0.setBaggageReturnList(baggageReturnList0);



        List<BaggageReturn> baggageReturnList1 = new ArrayList<>();
        baggageReturnUnion1.setBaggageReturnList(baggageReturnList1);

        BaggageReturn baggageReturn0=new BaggageReturn();
        baggageReturn0.setFareRelated(false);
        BaggageReturn baggageReturn1=new BaggageReturn();
        baggageReturn1.setFareRelated(true);
        BaggageReturn baggageReturn2=new BaggageReturn();
        baggageReturn2.setFareRelated(true);

        baggageReturnList0.add(baggageReturn0);
        baggageReturnList0.add(baggageReturn1);
        baggageReturnList1.add(baggageReturn1);
        baggageReturnList1.add(baggageReturn2);

        if (returnList0 != null && !returnList0.isEmpty()) {
            long count = returnList0.stream().filter(baggageReturnUnion -> {
                List<BaggageReturn> baggageReturnList = baggageReturnUnion.getBaggageReturnList();
                if (baggageReturnList != null && !baggageReturnList.isEmpty()) {
                    return baggageReturnList.stream().anyMatch(baggageReturn -> !baggageReturn.isFareRelated());
                }
                return true;
            }).count();
            System.out.println(String.format("count:%s, size:%s.", count, returnList0.size()));
        }
    }

}



