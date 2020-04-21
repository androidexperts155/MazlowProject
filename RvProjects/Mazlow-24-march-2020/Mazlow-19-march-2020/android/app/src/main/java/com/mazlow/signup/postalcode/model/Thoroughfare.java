
package com.mazlow.signup.postalcode.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Thoroughfare {

    @SerializedName("delivery_point_count")
    private Long mDeliveryPointCount;
    @SerializedName("delivery_points")
    private List<DeliveryPoint> mDeliveryPoints;
    @SerializedName("dependent_thoroughfare_descriptor")
    private String mDependentThoroughfareDescriptor;
    @SerializedName("dependent_thoroughfare_name")
    private String mDependentThoroughfareName;
    @SerializedName("thoroughfare_descriptor")
    private String mThoroughfareDescriptor;
    @SerializedName("thoroughfare_name")
    private String mThoroughfareName;

    public Long getDeliveryPointCount() {
        return mDeliveryPointCount;
    }

    public void setDeliveryPointCount(Long deliveryPointCount) {
        mDeliveryPointCount = deliveryPointCount;
    }

    public List<DeliveryPoint> getDeliveryPoints() {
        return mDeliveryPoints;
    }

    public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
        mDeliveryPoints = deliveryPoints;
    }

    public String getDependentThoroughfareDescriptor() {
        return mDependentThoroughfareDescriptor;
    }

    public void setDependentThoroughfareDescriptor(String dependentThoroughfareDescriptor) {
        mDependentThoroughfareDescriptor = dependentThoroughfareDescriptor;
    }

    public String getDependentThoroughfareName() {
        return mDependentThoroughfareName;
    }

    public void setDependentThoroughfareName(String dependentThoroughfareName) {
        mDependentThoroughfareName = dependentThoroughfareName;
    }

    public String getThoroughfareDescriptor() {
        return mThoroughfareDescriptor;
    }

    public void setThoroughfareDescriptor(String thoroughfareDescriptor) {
        mThoroughfareDescriptor = thoroughfareDescriptor;
    }

    public String getThoroughfareName() {
        return mThoroughfareName;
    }

    public void setThoroughfareName(String thoroughfareName) {
        mThoroughfareName = thoroughfareName;
    }

}
