package com.mazlow.customclasses;

public interface OtpReceivedInterface {
  void onOtpReceived(String otp);
  void onOtpTimeout();
}