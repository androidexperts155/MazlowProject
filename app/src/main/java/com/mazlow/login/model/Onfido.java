
package com.mazlow.login.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Onfido {

    @SerializedName("applicantId")
    private String mApplicantId;
    @SerializedName("checkId")
    private String mCheckId;

    public String getApplicantId() {
        return mApplicantId;
    }

    public void setApplicantId(String applicantId) {
        mApplicantId = applicantId;
    }

    public String getCheckId() {
        return mCheckId;
    }

    public void setCheckId(String checkId) {
        mCheckId = checkId;
    }

}
