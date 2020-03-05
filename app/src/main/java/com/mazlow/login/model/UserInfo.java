
package com.mazlow.login.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("onfido")
    @Expose
    private Onfido onfido;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("passCode")
    @Expose
    private String passCode;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("countryCode1")
    @Expose
    private String countryCode1;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addressline2")
    @Expose
    private String addressline2;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("enableAccount")
    @Expose
    private Boolean enableAccount;
    @SerializedName("role")
    @Expose
    private Integer role;
    @SerializedName("resetPassCodeToken")
    @Expose
    private String resetPassCodeToken;
    @SerializedName("resetPassCodeExpires")
    @Expose
    private Object resetPassCodeExpires;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("otpCode")
    @Expose
    private Integer otpCode;
    @SerializedName("phoneVerification")
    @Expose
    private Boolean phoneVerification;
    @SerializedName("emailVerification")
    @Expose
    private Boolean emailVerification;
    @SerializedName("profileImage")
    @Expose
    private String profileImage;
    @SerializedName("taxResident")
    @Expose
    private String taxResident;
    @SerializedName("cardHolderId")
    @Expose
    private String cardHolderId;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("cardStatus")
    @Expose
    private String cardStatus;
    @SerializedName("GBP_balance")
    @Expose
    private Integer gBPBalance;
    @SerializedName("USD_balance")
    @Expose
    private Integer uSDBalance;
    @SerializedName("EUR_balance")
    @Expose
    private Integer eURBalance;
    @SerializedName("GBP_wallet")
    @Expose
    private Boolean gBPWallet;
    @SerializedName("USD_wallet")
    @Expose
    private Boolean uSDWallet;
    @SerializedName("EUR_wallet")
    @Expose
    private Boolean eURWallet;
    @SerializedName("kycVerification")
    @Expose
    private String kycVerification;
    @SerializedName("startCardDate")
    @Expose
    private String startCardDate;
    @SerializedName("subscriptionExp")
    @Expose
    private Boolean subscriptionExp;
    @SerializedName("subscriptionPlan")
    @Expose
    private String subscriptionPlan;
    @SerializedName("subscriptionId")
    @Expose
    private String subscriptionId;
    @SerializedName("subscriptionExpDate")
    @Expose
    private String subscriptionExpDate;
    @SerializedName("subscriptionPendingBalance")
    @Expose
    private String subscriptionPendingBalance;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("budgetNotiication")
    @Expose
    private Boolean budgetNotiication;
    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("sortCode")
    @Expose
    private String sortCode;
    @SerializedName("bic")
    @Expose
    private String bic;
    @SerializedName("iban")
    @Expose
    private String iban;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("pfsToken")
    @Expose
    private List<PfsToken> pfsToken = null;
    @SerializedName("potData")
    @Expose
    private List<Object> potData = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Onfido getOnfido() {
        return onfido;
    }

    public void setOnfido(Onfido onfido) {
        this.onfido = onfido;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode1() {
        return countryCode1;
    }

    public void setCountryCode1(String countryCode1) {
        this.countryCode1 = countryCode1;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEnableAccount() {
        return enableAccount;
    }

    public void setEnableAccount(Boolean enableAccount) {
        this.enableAccount = enableAccount;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getResetPassCodeToken() {
        return resetPassCodeToken;
    }

    public void setResetPassCodeToken(String resetPassCodeToken) {
        this.resetPassCodeToken = resetPassCodeToken;
    }

    public Object getResetPassCodeExpires() {
        return resetPassCodeExpires;
    }

    public void setResetPassCodeExpires(Object resetPassCodeExpires) {
        this.resetPassCodeExpires = resetPassCodeExpires;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(Integer otpCode) {
        this.otpCode = otpCode;
    }

    public Boolean getPhoneVerification() {
        return phoneVerification;
    }

    public void setPhoneVerification(Boolean phoneVerification) {
        this.phoneVerification = phoneVerification;
    }

    public Boolean getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(Boolean emailVerification) {
        this.emailVerification = emailVerification;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getTaxResident() {
        return taxResident;
    }

    public void setTaxResident(String taxResident) {
        this.taxResident = taxResident;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getGBPBalance() {
        return gBPBalance;
    }

    public void setGBPBalance(Integer gBPBalance) {
        this.gBPBalance = gBPBalance;
    }

    public Integer getUSDBalance() {
        return uSDBalance;
    }

    public void setUSDBalance(Integer uSDBalance) {
        this.uSDBalance = uSDBalance;
    }

    public Integer getEURBalance() {
        return eURBalance;
    }

    public void setEURBalance(Integer eURBalance) {
        this.eURBalance = eURBalance;
    }

    public Boolean getGBPWallet() {
        return gBPWallet;
    }

    public void setGBPWallet(Boolean gBPWallet) {
        this.gBPWallet = gBPWallet;
    }

    public Boolean getUSDWallet() {
        return uSDWallet;
    }

    public void setUSDWallet(Boolean uSDWallet) {
        this.uSDWallet = uSDWallet;
    }

    public Boolean getEURWallet() {
        return eURWallet;
    }

    public void setEURWallet(Boolean eURWallet) {
        this.eURWallet = eURWallet;
    }

    public String getKycVerification() {
        return kycVerification;
    }

    public void setKycVerification(String kycVerification) {
        this.kycVerification = kycVerification;
    }

    public String getStartCardDate() {
        return startCardDate;
    }

    public void setStartCardDate(String startCardDate) {
        this.startCardDate = startCardDate;
    }

    public Boolean getSubscriptionExp() {
        return subscriptionExp;
    }

    public void setSubscriptionExp(Boolean subscriptionExp) {
        this.subscriptionExp = subscriptionExp;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionExpDate() {
        return subscriptionExpDate;
    }

    public void setSubscriptionExpDate(String subscriptionExpDate) {
        this.subscriptionExpDate = subscriptionExpDate;
    }

    public String getSubscriptionPendingBalance() {
        return subscriptionPendingBalance;
    }

    public void setSubscriptionPendingBalance(String subscriptionPendingBalance) {
        this.subscriptionPendingBalance = subscriptionPendingBalance;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getBudgetNotiication() {
        return budgetNotiication;
    }

    public void setBudgetNotiication(Boolean budgetNotiication) {
        this.budgetNotiication = budgetNotiication;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PfsToken> getPfsToken() {
        return pfsToken;
    }

    public void setPfsToken(List<PfsToken> pfsToken) {
        this.pfsToken = pfsToken;
    }

    public List<Object> getPotData() {
        return potData;
    }

    public void setPotData(List<Object> potData) {
        this.potData = potData;
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
