
package com.mazlow.login.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserInfo {

    @SerializedName("accountNumber")
    private String mAccountNumber;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("addressline2")
    private String mAddressline2;
    @SerializedName("bic")
    private String mBic;
    @SerializedName("budgetNotiication")
    private Boolean mBudgetNotiication;
    @SerializedName("cardHolderId")
    private String mCardHolderId;
    @SerializedName("cardNumber")
    private String mCardNumber;
    @SerializedName("cardStatus")
    private String mCardStatus;
    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("countryCode")
    private String mCountryCode;
    @SerializedName("countryCode1")
    private String mCountryCode1;
    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("currencyCode")
    private String mCurrencyCode;
    @SerializedName("cvv")
    private String mCvv;
    @SerializedName("deviceToken")
    private String mDeviceToken;
    @SerializedName("deviceType")
    private String mDeviceType;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("EUR_balance")
    private Long mEURBalance;
    @SerializedName("EUR_wallet")
    private Boolean mEURWallet;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("emailVerification")
    private Boolean mEmailVerification;
    @SerializedName("enableAccount")
    private Boolean mEnableAccount;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("GBP_balance")
    private Long mGBPBalance;
    @SerializedName("GBP_wallet")
    private Boolean mGBPWallet;
    @SerializedName("iban")
    private String mIban;
    @SerializedName("kycVerification")
    private String mKycVerification;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("onfido")
    private Onfido mOnfido;
    @SerializedName("otpCode")
    private Long mOtpCode;
    @SerializedName("passCode")
    private String mPassCode;
    @SerializedName("pfsToken")
    private List<Object> mPfsToken;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("phoneVerification")
    private Boolean mPhoneVerification;
    @SerializedName("points")
    private Long mPoints;
    @SerializedName("postalCode")
    private String mPostalCode;
    @SerializedName("potData")
    private List<Object> mPotData;
    @SerializedName("profileImage")
    private String mProfileImage;
    @SerializedName("resetPassCodeExpires")
    private Object mResetPassCodeExpires;
    @SerializedName("resetPassCodeToken")
    private String mResetPassCodeToken;
    @SerializedName("role")
    private Long mRole;
    @SerializedName("sortCode")
    private String mSortCode;
    @SerializedName("startCardDate")
    private String mStartCardDate;
    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("street")
    private String mStreet;
    @SerializedName("subscriptionExp")
    private Boolean mSubscriptionExp;
    @SerializedName("subscriptionExpDate")
    private String mSubscriptionExpDate;
    @SerializedName("subscriptionId")
    private String mSubscriptionId;
    @SerializedName("subscriptionPendingBalance")
    private String mSubscriptionPendingBalance;
    @SerializedName("subscriptionPlan")
    private String mSubscriptionPlan;
    @SerializedName("taxResident")
    private String mTaxResident;
    @SerializedName("USD_balance")
    private Long mUSDBalance;
    @SerializedName("USD_wallet")
    private Boolean mUSDWallet;
    @SerializedName("updatedAt")
    private String mUpdatedAt;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAddressline2() {
        return mAddressline2;
    }

    public void setAddressline2(String addressline2) {
        mAddressline2 = addressline2;
    }

    public String getBic() {
        return mBic;
    }

    public void setBic(String bic) {
        mBic = bic;
    }

    public Boolean getBudgetNotiication() {
        return mBudgetNotiication;
    }

    public void setBudgetNotiication(Boolean budgetNotiication) {
        mBudgetNotiication = budgetNotiication;
    }

    public String getCardHolderId() {
        return mCardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        mCardHolderId = cardHolderId;
    }

    public String getCardNumber() {
        return mCardNumber;
    }

    public void setCardNumber(String cardNumber) {
        mCardNumber = cardNumber;
    }

    public String getCardStatus() {
        return mCardStatus;
    }

    public void setCardStatus(String cardStatus) {
        mCardStatus = cardStatus;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCountryCode1() {
        return mCountryCode1;
    }

    public void setCountryCode1(String countryCode1) {
        mCountryCode1 = countryCode1;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public String getCvv() {
        return mCvv;
    }

    public void setCvv(String cvv) {
        mCvv = cvv;
    }

    public String getDeviceToken() {
        return mDeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        mDeviceToken = deviceToken;
    }

    public String getDeviceType() {
        return mDeviceType;
    }

    public void setDeviceType(String deviceType) {
        mDeviceType = deviceType;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public Long getEURBalance() {
        return mEURBalance;
    }

    public void setEURBalance(Long eURBalance) {
        mEURBalance = eURBalance;
    }

    public Boolean getEURWallet() {
        return mEURWallet;
    }

    public void setEURWallet(Boolean eURWallet) {
        mEURWallet = eURWallet;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Boolean getEmailVerification() {
        return mEmailVerification;
    }

    public void setEmailVerification(Boolean emailVerification) {
        mEmailVerification = emailVerification;
    }

    public Boolean getEnableAccount() {
        return mEnableAccount;
    }

    public void setEnableAccount(Boolean enableAccount) {
        mEnableAccount = enableAccount;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getGBPBalance() {
        return mGBPBalance;
    }

    public void setGBPBalance(Long gBPBalance) {
        mGBPBalance = gBPBalance;
    }

    public Boolean getGBPWallet() {
        return mGBPWallet;
    }

    public void setGBPWallet(Boolean gBPWallet) {
        mGBPWallet = gBPWallet;
    }

    public String getIban() {
        return mIban;
    }

    public void setIban(String iban) {
        mIban = iban;
    }

    public String getKycVerification() {
        return mKycVerification;
    }

    public void setKycVerification(String kycVerification) {
        mKycVerification = kycVerification;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Onfido getOnfido() {
        return mOnfido;
    }

    public void setOnfido(Onfido onfido) {
        mOnfido = onfido;
    }

    public Long getOtpCode() {
        return mOtpCode;
    }

    public void setOtpCode(Long otpCode) {
        mOtpCode = otpCode;
    }

    public String getPassCode() {
        return mPassCode;
    }

    public void setPassCode(String passCode) {
        mPassCode = passCode;
    }

    public List<Object> getPfsToken() {
        return mPfsToken;
    }

    public void setPfsToken(List<Object> pfsToken) {
        mPfsToken = pfsToken;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public Boolean getPhoneVerification() {
        return mPhoneVerification;
    }

    public void setPhoneVerification(Boolean phoneVerification) {
        mPhoneVerification = phoneVerification;
    }

    public Long getPoints() {
        return mPoints;
    }

    public void setPoints(Long points) {
        mPoints = points;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        mPostalCode = postalCode;
    }

    public List<Object> getPotData() {
        return mPotData;
    }

    public void setPotData(List<Object> potData) {
        mPotData = potData;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

    public Object getResetPassCodeExpires() {
        return mResetPassCodeExpires;
    }

    public void setResetPassCodeExpires(Object resetPassCodeExpires) {
        mResetPassCodeExpires = resetPassCodeExpires;
    }

    public String getResetPassCodeToken() {
        return mResetPassCodeToken;
    }

    public void setResetPassCodeToken(String resetPassCodeToken) {
        mResetPassCodeToken = resetPassCodeToken;
    }

    public Long getRole() {
        return mRole;
    }

    public void setRole(Long role) {
        mRole = role;
    }

    public String getSortCode() {
        return mSortCode;
    }

    public void setSortCode(String sortCode) {
        mSortCode = sortCode;
    }

    public String getStartCardDate() {
        return mStartCardDate;
    }

    public void setStartCardDate(String startCardDate) {
        mStartCardDate = startCardDate;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        mStreet = street;
    }

    public Boolean getSubscriptionExp() {
        return mSubscriptionExp;
    }

    public void setSubscriptionExp(Boolean subscriptionExp) {
        mSubscriptionExp = subscriptionExp;
    }

    public String getSubscriptionExpDate() {
        return mSubscriptionExpDate;
    }

    public void setSubscriptionExpDate(String subscriptionExpDate) {
        mSubscriptionExpDate = subscriptionExpDate;
    }

    public String getSubscriptionId() {
        return mSubscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        mSubscriptionId = subscriptionId;
    }

    public String getSubscriptionPendingBalance() {
        return mSubscriptionPendingBalance;
    }

    public void setSubscriptionPendingBalance(String subscriptionPendingBalance) {
        mSubscriptionPendingBalance = subscriptionPendingBalance;
    }

    public String getSubscriptionPlan() {
        return mSubscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        mSubscriptionPlan = subscriptionPlan;
    }

    public String getTaxResident() {
        return mTaxResident;
    }

    public void setTaxResident(String taxResident) {
        mTaxResident = taxResident;
    }

    public Long getUSDBalance() {
        return mUSDBalance;
    }

    public void setUSDBalance(Long uSDBalance) {
        mUSDBalance = uSDBalance;
    }

    public Boolean getUSDWallet() {
        return mUSDWallet;
    }

    public void setUSDWallet(Boolean uSDWallet) {
        mUSDWallet = uSDWallet;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Long get_V() {
        return m_V;
    }

    public void set_V(Long _V) {
        m_V = _V;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
