package com.jw;

import java.util.List;

/**
 * @Description: test com.jw.BaggageReturn
 * @Package: com.jw
 * @ClassName: BaggageReturn
 * @Author: james.guo
 * @Date: 2019/3/18 22:49
 * @Version: 1.0
 */
public class BaggageReturn {
    private boolean fareRelated;
    private boolean successFlag;
    private List<BaggagesInBT> baggagesInBTList;

    public boolean isFareRelated() {
        return fareRelated;
    }

    public void setFareRelated(boolean fareRelated) {
        this.fareRelated = fareRelated;
    }

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public List<BaggagesInBT> getBaggagesInBTList() {
        return baggagesInBTList;
    }

    public void setBaggagesInBTList(List<BaggagesInBT> baggagesInBTList) {
        this.baggagesInBTList = baggagesInBTList;
    }
}
