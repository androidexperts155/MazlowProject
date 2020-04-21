
package com.mazlow.search_address.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class AddressZipcodeResponse {

    @SerializedName("addressInfo")
    private List<String> mAddressInfo;
    @SerializedName("success")
    private Boolean mSuccess;

    public List<String> getAddressInfo() {
        return mAddressInfo;
    }

    public void setAddressInfo(List<String> addressInfo) {
        mAddressInfo = addressInfo;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
