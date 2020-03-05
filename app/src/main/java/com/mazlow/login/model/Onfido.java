
package com.mazlow.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Onfido {

    @SerializedName("applicantId")
    @Expose
    private String applicantId;
    @SerializedName("checkId")
    @Expose
    private String checkId;

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

}
