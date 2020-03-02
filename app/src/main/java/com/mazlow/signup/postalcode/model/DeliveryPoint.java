
package com.mazlow.signup.postalcode.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DeliveryPoint {

    @SerializedName("building_name")
    private String mBuildingName;
    @SerializedName("building_number")
    private String mBuildingNumber;
    @SerializedName("department_name")
    private String mDepartmentName;
    @SerializedName("dps")
    private String mDps;
    @SerializedName("organisation_name")
    private String mOrganisationName;
    @SerializedName("po_box_number")
    private String mPoBoxNumber;
    @SerializedName("sub_building_name")
    private String mSubBuildingName;
    @SerializedName("udprn")
    private String mUdprn;

    public String getBuildingName() {
        return mBuildingName;
    }

    public void setBuildingName(String buildingName) {
        mBuildingName = buildingName;
    }

    public String getBuildingNumber() {
        return mBuildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        mBuildingNumber = buildingNumber;
    }

    public String getDepartmentName() {
        return mDepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        mDepartmentName = departmentName;
    }

    public String getDps() {
        return mDps;
    }

    public void setDps(String dps) {
        mDps = dps;
    }

    public String getOrganisationName() {
        return mOrganisationName;
    }

    public void setOrganisationName(String organisationName) {
        mOrganisationName = organisationName;
    }

    public String getPoBoxNumber() {
        return mPoBoxNumber;
    }

    public void setPoBoxNumber(String poBoxNumber) {
        mPoBoxNumber = poBoxNumber;
    }

    public String getSubBuildingName() {
        return mSubBuildingName;
    }

    public void setSubBuildingName(String subBuildingName) {
        mSubBuildingName = subBuildingName;
    }

    public String getUdprn() {
        return mUdprn;
    }

    public void setUdprn(String udprn) {
        mUdprn = udprn;
    }

}
