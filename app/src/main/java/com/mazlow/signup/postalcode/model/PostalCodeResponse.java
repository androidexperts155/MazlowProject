
package com.mazlow.signup.postalcode.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostalCodeResponse {

    @SerializedName("dependent_locality")
    private String mDependentLocality;
    @SerializedName("double_dependent_locality")
    private String mDoubleDependentLocality;
    @SerializedName("postal_county")
    private String mPostalCounty;
    @SerializedName("postcode")
    private String mPostcode;
    @SerializedName("thoroughfare_count")
    private Long mThoroughfareCount;
    @SerializedName("thoroughfares")
    private List<Thoroughfare> mThoroughfares;
    @SerializedName("town")
    private String mTown;
    @SerializedName("traditional_county")
    private String mTraditionalCounty;

    public String getDependentLocality() {
        return mDependentLocality;
    }

    public void setDependentLocality(String dependentLocality) {
        mDependentLocality = dependentLocality;
    }

    public String getDoubleDependentLocality() {
        return mDoubleDependentLocality;
    }

    public void setDoubleDependentLocality(String doubleDependentLocality) {
        mDoubleDependentLocality = doubleDependentLocality;
    }

    public String getPostalCounty() {
        return mPostalCounty;
    }

    public void setPostalCounty(String postalCounty) {
        mPostalCounty = postalCounty;
    }

    public String getPostcode() {
        return mPostcode;
    }

    public void setPostcode(String postcode) {
        mPostcode = postcode;
    }

    public Long getThoroughfareCount() {
        return mThoroughfareCount;
    }

    public void setThoroughfareCount(Long thoroughfareCount) {
        mThoroughfareCount = thoroughfareCount;
    }

    public List<Thoroughfare> getThoroughfares() {
        return mThoroughfares;
    }

    public void setThoroughfares(List<Thoroughfare> thoroughfares) {
        mThoroughfares = thoroughfares;
    }

    public String getTown() {
        return mTown;
    }

    public void setTown(String town) {
        mTown = town;
    }

    public String getTraditionalCounty() {
        return mTraditionalCounty;
    }

    public void setTraditionalCounty(String traditionalCounty) {
        mTraditionalCounty = traditionalCounty;
    }

}
