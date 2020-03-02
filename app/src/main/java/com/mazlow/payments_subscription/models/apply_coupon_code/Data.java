package com.mazlow.payments_subscription.models.apply_coupon_code;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("subscriptionId")
@Expose
private String subscriptionId;
@SerializedName("code")
@Expose
private String code;
@SerializedName("percentage")
@Expose
private String percentage;
@SerializedName("_id")
@Expose
private String id;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;

public String getSubscriptionId() {
return subscriptionId;
}

public void setSubscriptionId(String subscriptionId) {
this.subscriptionId = subscriptionId;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getPercentage() {
return percentage;
}

public void setPercentage(String percentage) {
this.percentage = percentage;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

}