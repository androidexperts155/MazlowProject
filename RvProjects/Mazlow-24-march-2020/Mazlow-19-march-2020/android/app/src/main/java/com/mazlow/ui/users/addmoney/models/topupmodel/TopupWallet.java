
package com.mazlow.ui.users.addmoney.models.topupmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopupWallet {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("response")
    @Expose
    private Response response;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
