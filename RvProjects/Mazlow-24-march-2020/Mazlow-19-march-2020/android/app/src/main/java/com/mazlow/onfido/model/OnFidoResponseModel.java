
package com.mazlow.onfido.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OnFidoResponseModel {

    @SerializedName("addresses")
    private List<Object> mAddresses;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("country_of_birth")
    private Object mCountryOfBirth;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("delete_at")
    private Object mDeleteAt;
    @SerializedName("dob")
    private Object mDob;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("gender")
    private Object mGender;
    @SerializedName("href")
    private String mHref;
    @SerializedName("id")
    private String mId;
    @SerializedName("id_numbers")
    private List<Object> mIdNumbers;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("middle_name")
    private Object mMiddleName;
    @SerializedName("mobile")
    private Object mMobile;
    @SerializedName("mothers_maiden_name")
    private Object mMothersMaidenName;
    @SerializedName("nationality")
    private Object mNationality;
    @SerializedName("previous_last_name")
    private Object mPreviousLastName;
    @SerializedName("sandbox")
    private Boolean mSandbox;
    @SerializedName("telephone")
    private Object mTelephone;
    @SerializedName("title")
    private Object mTitle;
    @SerializedName("town_of_birth")
    private Object mTownOfBirth;

    public List<Object> getAddresses() {
        return mAddresses;
    }

    public void setAddresses(List<Object> addresses) {
        mAddresses = addresses;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Object getCountryOfBirth() {
        return mCountryOfBirth;
    }

    public void setCountryOfBirth(Object countryOfBirth) {
        mCountryOfBirth = countryOfBirth;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Object getDeleteAt() {
        return mDeleteAt;
    }

    public void setDeleteAt(Object deleteAt) {
        mDeleteAt = deleteAt;
    }

    public Object getDob() {
        return mDob;
    }

    public void setDob(Object dob) {
        mDob = dob;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Object getGender() {
        return mGender;
    }

    public void setGender(Object gender) {
        mGender = gender;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<Object> getIdNumbers() {
        return mIdNumbers;
    }

    public void setIdNumbers(List<Object> idNumbers) {
        mIdNumbers = idNumbers;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Object getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(Object middleName) {
        mMiddleName = middleName;
    }

    public Object getMobile() {
        return mMobile;
    }

    public void setMobile(Object mobile) {
        mMobile = mobile;
    }

    public Object getMothersMaidenName() {
        return mMothersMaidenName;
    }

    public void setMothersMaidenName(Object mothersMaidenName) {
        mMothersMaidenName = mothersMaidenName;
    }

    public Object getNationality() {
        return mNationality;
    }

    public void setNationality(Object nationality) {
        mNationality = nationality;
    }

    public Object getPreviousLastName() {
        return mPreviousLastName;
    }

    public void setPreviousLastName(Object previousLastName) {
        mPreviousLastName = previousLastName;
    }

    public Boolean getSandbox() {
        return mSandbox;
    }

    public void setSandbox(Boolean sandbox) {
        mSandbox = sandbox;
    }

    public Object getTelephone() {
        return mTelephone;
    }

    public void setTelephone(Object telephone) {
        mTelephone = telephone;
    }

    public Object getTitle() {
        return mTitle;
    }

    public void setTitle(Object title) {
        mTitle = title;
    }

    public Object getTownOfBirth() {
        return mTownOfBirth;
    }

    public void setTownOfBirth(Object townOfBirth) {
        mTownOfBirth = townOfBirth;
    }

}
